package voogasalad.utilities.frontEndCommands.backendcommandresponse;

import javafx.scene.paint.Color;
import voogasalad.gameengine.enginecontroller.EngineController;
import voogasalad.utilities.frontEndCommands.FrontEndCommand;
import java.lang.reflect.Method;

/**
 * This frontEndCommand should be called when the backend decides that the defender can be
 * placed in the given location. It tells the frontend to draw a green circle.
 */
public class AbleToPlaceCommand extends FrontEndCommand {
    private static final String METHOD_NAME = "drawCircle";

    private double radius;

    /**
     * @param radius = put the radius of the circle to be drawn.
     */
    public AbleToPlaceCommand(double radius) {
        this.radius = radius;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(METHOD_NAME, Color.class, Double.TYPE);
            m.invoke(getEngineController(), Color.GREEN, radius);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
