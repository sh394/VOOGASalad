package voogasalad.utilities;

import voogasalad.gameengine.enginecontroller.EngineController;

/**
 * GameEngineCommand used for communication between frontend and backend of the game engine
 */
public interface GameEngineCommand {
    /**
     * Sets the controller
     * @param controller
     */
    void setController(EngineController controller);

    /**
     * Executes and call the appropriate method in the controller
     */
    void execute();
}
