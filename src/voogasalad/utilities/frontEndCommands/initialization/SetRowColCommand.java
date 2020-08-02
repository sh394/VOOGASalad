package voogasalad.utilities.frontEndCommands.initialization;

import voogasalad.player.PlayerException;
import voogasalad.utilities.frontEndCommands.FrontEndCommand;

import java.lang.reflect.Method;

/**
 * Called during the initialization stage of the level. Informs frontend the current row number and column number.
 */
public class SetRowColCommand extends FrontEndCommand {
    private static final String METHOD_NAME = "setRowAndCol";
    private static String EXCEPTION_MESSAGE = "Error in EndOfLevelCommand";

    private int row;
    private int col;

    /**
     * @param row = number of rows
     * @param col = number of columns
     */
    public SetRowColCommand(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(METHOD_NAME, Integer.TYPE, Integer.TYPE);
            m.invoke(getEngineController(), row, col);
        } catch (Exception e) {
            throw new PlayerException(e, EXCEPTION_MESSAGE);
        }
    }
}
