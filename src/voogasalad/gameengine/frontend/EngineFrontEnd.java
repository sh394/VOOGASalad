package voogasalad.gameengine.frontend;

import javafx.scene.Node;
import voogasalad.utilities.BackEndCommand;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

/**
 * FrontEnd class that is used to update the screen at each frame.
 */
public interface EngineFrontEnd {

    /**
     * Returns a list of user inputs
     * @return
     */
    List<BackEndCommand> getUserInputs();

    /**
     * Moves a node with a given ID to a location
     * @param ID
     * @param location
     * @param orientation
     */
    void moveNode(int ID, Point2D.Double location, double orientation);

    /**
     * Creates a node with a given ID, by making an image with the filePath
     * @param ID
     * @param filePath
     * @param type
     * @param location
     * @param orientation
     * @param size
     * @param radius
     */
    void createNode(int ID, String filePath, String type, Point2D.Double location, double orientation, double size, double radius);

    /**
     * Remove a node with given ID.
     * @param ID
     */
    void removeNode(int ID);

    /**
     * Sets the level
     * @param level
     */
    void setLevel(int level);

    /**
     * Returns the map that represents the collision that occurred
     * @return
     */
    Map<Integer, List<Integer>> getCollision();

    /**
     * Draw a node(circle, etc).
     * @param node
     * @param ID
     * @param isMapTile: is this a map tile?
     * @param isMouseEvent: did this happen from mouse event?
     */
    void drawNode(Node node, int ID, boolean isMapTile, boolean isMouseEvent);

    /**
     * Adds available defender/obstacle to the defender tab
     * @param filePath
     * @param defenderType
     * @param cost
     */
    void addToDefenderTab(String filePath, String defenderType, int cost);

    /**
     * Updates the status
     * @param statusType
     * @param amount
     */
    void setStatus(String statusType, int amount);

    /**
     * Called when end of level / winGame / loseGame has occurred
     * @param directory
     */
    void endOfLevel(String directory);

    /**
     * Shows an effect
     * @param ID
     * @param radius
     * @param effect
     */
    void showEffect(int ID, double radius, String effect);

    /**
     * When the score needs to be saved
     */
    void sendScore();

    /**
     * Show the setting popup screen.
     */
    void showSettingPopUp();
}
