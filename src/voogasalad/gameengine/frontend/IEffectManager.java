package voogasalad.gameengine.frontend;

import javafx.scene.Node;

/**
 * Interface that is implemented and used by FrontEnd to show visual effects
 */
public interface IEffectManager {

    /**
     * Registers an effect with a given size and an ID. The node will be made within the class and it will be returned.
     * @param ID
     * @param size
     * @return
     */
    Node registerEffect(int ID, double size);

    /**
     * Tell the IEffectManager class to store the location.
     * @param ID
     */
    void setLocation(int ID);

    /**
     * Update all the effects that happen.
     */
    void update();

    /**
     * Clear the effect.
     */
    void clear();
}
