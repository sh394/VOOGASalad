package voogasalad.utilities.frontEndCommands.effect;

import voogasalad.gameengine.enginecontroller.EngineController;
import voogasalad.player.PlayerException;
import voogasalad.utilities.frontEndCommands.FrontEndCommand;

import java.lang.reflect.Method;

/**
 * Should be called when explosion happened.
 */
public class ExplodeCommand extends FrontEndCommand {
    private static final String METHOD_NAME = "showEffect";
    private static final String EXPLOSION = "Explosion";

    private int ID;
    private double radius;

    public ExplodeCommand(int id, double radius) {
        this.ID = id;
        this.radius = radius;
    }

    @Override
    public void execute() {
        try {
            Method m = getEngineController().getClass().getDeclaredMethod(METHOD_NAME, Integer.TYPE, Double.TYPE, String.class);
            m.invoke(getEngineController(), ID, radius, EXPLOSION);
        } catch (Exception e) {
            throw new PlayerException(e, "ExplodeCommand is not working well");
        }
    }
}
