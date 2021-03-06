package voogasalad.utilities.frontEndCommands.updateelements;

import voogasalad.utilities.frontEndCommands.FrontEndCommand;
import java.awt.geom.Point2D;
import java.lang.reflect.Method;

/**
 * Should be called when an element moves during the frame.
 */
public class MoveCommand extends FrontEndCommand {

    private static String methodName = "moveNode";

    private int ID;
    private Point2D.Double location;
    private double orientation;

    public MoveCommand(int ID, Point2D.Double location, double orientation) {
        this.ID = ID;
        this.location = location;
        this.orientation = orientation;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(methodName, Integer.TYPE, Point2D.Double.class, Double.TYPE);
            m.invoke(getEngineController(), ID, location, orientation);
        } catch (Exception e) {
            System.out.println(ID + " cannot move");
        }
    }
}
