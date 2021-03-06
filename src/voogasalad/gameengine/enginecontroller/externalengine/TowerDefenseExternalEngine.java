package voogasalad.gameengine.enginecontroller.externalengine;

import voogasalad.gameengine.GameEngine;
import voogasalad.gameengine.enginecontroller.frontend.FrontEndController;
import voogasalad.gameengine.enginecontroller.frontend.TowerDefenseFrontEndController;
import voogasalad.gameengine.frontend.EngineFrontEnd;
import voogasalad.gameengine.frontend.TowerDefenseFrontEnd;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import voogasalad.gameengine.game.Game;
import voogasalad.player.GameMainWindow;
import voogasalad.utilities.frontEndCommands.FrontEndCommand;
import java.util.List;

public class TowerDefenseExternalEngine implements ExternalEngineInterface {

    private static final int FRAMES_PER_SECOND = 60;
    private static final double DURATION_MILLIS = 1000 / FRAMES_PER_SECOND;

    private GameEngine myEngineBackEnd;
    private EngineFrontEnd myEngineFrontEnd;
    private FrontEndController myFrontEndController;
    private Timeline myAnimation;

    public TowerDefenseExternalEngine(GameMainWindow gameMainWindow, String filePath) {
        myEngineFrontEnd = new TowerDefenseFrontEnd(gameMainWindow);
        myFrontEndController = new TowerDefenseFrontEndController(myEngineFrontEnd);
        myEngineBackEnd = new GameEngine(filePath);
        loadGame(filePath);
        setTimeLine();
        myEngineFrontEnd.showSettingPopUp();
    }

    /**
     * @see ExternalEngineInterface#play()
     */
    @Override
    public void play() {
        myAnimation.play();
    }

    /**
     * @see ExternalEngineInterface#stop()
     */
    @Override
    public void stop() {
        myAnimation.stop();
    }

    /**
     * @see ExternalEngineInterface#loadGame(String)
     */
    @Override
    public void loadGame(String filePath) {
        myEngineBackEnd = new GameEngine(filePath);
        nextLevel();
        play();
    }

    /**
     * @see ExternalEngineInterface#saveGame()
     */
    @Override
    public Game saveGame() {
        return myEngineBackEnd.saveGame();
    }

    /**
     * @see ExternalEngineInterface#nextLevel()
     */
    @Override
    public void nextLevel() {
        for(FrontEndCommand command : myEngineBackEnd.initializeGame()) {
            command.setController(myFrontEndController);
            command.execute();
        }
    }

    /**
     * @see ExternalEngineInterface#newGame(boolean)
     */
    @Override
    public void newGame(boolean level) {
        if(level) {
            myEngineBackEnd.restartLevel();
        } else {
            myEngineBackEnd.restartGame();
        }
        nextLevel();
        play();
    }

    private void step() {
        List<FrontEndCommand> commands = myEngineBackEnd.updateGame(myEngineFrontEnd.getUserInputs(), myEngineFrontEnd.getCollision());
        for(FrontEndCommand command : commands) {
            command.setController(myFrontEndController);
            command.execute();
        }
    }

    private void setTimeLine() {
        KeyFrame myKeyFrame = new KeyFrame(Duration.millis(DURATION_MILLIS), e -> step());
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(myKeyFrame);
    }
}
