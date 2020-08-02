package voogasalad.utilities.frontEndCommands.status;

import voogasalad.gameengine.enginecontroller.EngineController;
import voogasalad.utilities.frontEndCommands.FrontEndCommand;

import java.lang.reflect.Method;

/**
 * Should be called when the score is updated
 */
public class UpdateScore extends FrontEndCommand {
    private static final String METHOD_NAME = "setStatus";

    private int score;

    /**
     * @param score = score
     */
    public UpdateScore(int score) {
        this.score = score;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(METHOD_NAME, String.class, Integer.TYPE);
            m.invoke(getEngineController(), "Score", score);
        } catch (Exception e) {
            System.out.println("Update score not right");
        }
    }
}
