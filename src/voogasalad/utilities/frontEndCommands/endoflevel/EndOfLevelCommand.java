package voogasalad.utilities.frontEndCommands.endoflevel;

import voogasalad.player.PlayerException;
import voogasalad.utilities.frontEndCommands.FrontEndCommand;

import java.lang.reflect.Method;

/**
 * Should be called when the engine decides that the player wins the level.
 */
public class EndOfLevelCommand extends FrontEndCommand {
    private static String METHOD_NAME = "nextLevel";
    private static String EXCEPTION_MESSAGE = "Error in EndOfLevelCommand";


    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(METHOD_NAME);
            m.invoke(getEngineController());
        } catch (Exception e) {
            throw new PlayerException(e, EXCEPTION_MESSAGE);
        }
    }
}
