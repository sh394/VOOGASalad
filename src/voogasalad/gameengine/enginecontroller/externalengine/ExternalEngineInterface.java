package voogasalad.gameengine.enginecontroller.externalengine;

import voogasalad.gameengine.game.Game;

/**
 * External Engine that holds the game loop, and holds the backend and frontend objects.
 **/
public interface ExternalEngineInterface {
    /**
     * Called to start the game loop
     */
    void play();

    /**
     * Called to stop the game loop
     */
    void stop();

    /**
     * Loads the game from filePath
     * @param filePath: FilePath
     */
    void loadGame(String filePath);

    /**
     * Called to save the game
     * @return the Game object in the Backend
     */
    Game saveGame();

    void nextLevel();

    void newGame(boolean level);
}
