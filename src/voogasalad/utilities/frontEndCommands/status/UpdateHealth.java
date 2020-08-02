package voogasalad.utilities.frontEndCommands.status;

import voogasalad.gameengine.enginecontroller.EngineController;
import voogasalad.utilities.frontEndCommands.FrontEndCommand;

import java.lang.reflect.Method;

/**
 * Should be called to update the health
 */
public class UpdateHealth extends FrontEndCommand {
    private static final String METHOD_NAME = "setStatus";

    private int health;

    public UpdateHealth(int health) {
        this.health = health;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(METHOD_NAME, String.class, Integer.TYPE);
            m.invoke(getEngineController(), "Health", health);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
