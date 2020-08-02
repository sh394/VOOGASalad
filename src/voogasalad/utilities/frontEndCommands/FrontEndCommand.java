package voogasalad.utilities.frontEndCommands;

import voogasalad.gameengine.enginecontroller.EngineController;
import voogasalad.utilities.GameEngineCommand;

public abstract class FrontEndCommand implements GameEngineCommand {
    private EngineController engineController;

    @Override
    public void setController(EngineController controller) {
        this.engineController = engineController;
    }

    public EngineController getEngineController() {
        return engineController;
    }
}
