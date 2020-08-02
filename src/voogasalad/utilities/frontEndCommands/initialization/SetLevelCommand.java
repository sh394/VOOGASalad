package voogasalad.utilities.frontEndCommands.initialization;

import voogasalad.gameengine.enginecontroller.EngineController;
import voogasalad.player.PlayerException;
import voogasalad.utilities.frontEndCommands.FrontEndCommand;

import java.lang.reflect.Method;

/**
 * Called during the initialization stage of the level. Informs frontend the current level.
 */
public class SetLevelCommand extends FrontEndCommand {
    private static final String METHOD_NAME = "setLevel";
    private static String EXCEPTION_MESSAGE = "Error in SetLevelCommand";

    private int level;

    public SetLevelCommand(int level) {
        this.level = level;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(METHOD_NAME, Integer.TYPE);
            m.invoke(getEngineController(), level);
        } catch (Exception e) {
            throw new PlayerException(e, EXCEPTION_MESSAGE);
        }
    }
}
