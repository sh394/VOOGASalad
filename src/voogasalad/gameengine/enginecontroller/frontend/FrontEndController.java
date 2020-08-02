package voogasalad.gameengine.enginecontroller.frontend;

import voogasalad.gameengine.enginecontroller.EngineController;
import voogasalad.gameengine.frontend.EngineFrontEnd;

/**
 * FrontEndController class, for FrontEndCommand
 */
public abstract class FrontEndController implements EngineController {
    private EngineFrontEnd myFrontEnd;

    public FrontEndController(EngineFrontEnd engineFrontEnd) {
        myFrontEnd = engineFrontEnd;
    }

    public EngineFrontEnd getMyFrontEnd() {
        return myFrontEnd;
    }
}
