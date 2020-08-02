package voogasalad.utilities.frontEndCommands.updateelements;

import voogasalad.utilities.frontEndCommands.FrontEndCommand;

import java.lang.reflect.Method;

/**
 * Should be called when an element has been removed.
 */
public class RemoveCommand extends FrontEndCommand {

    private static String methodName = "removeNode";

    private int ID;

    /**
     * ID of the element that has been removed.
     * @param ID
     */
    public RemoveCommand(int ID) {
        this.ID = ID;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(methodName, Integer.TYPE);
            m.invoke(getEngineController(), ID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
