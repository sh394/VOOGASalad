package voogasalad.gameengine.enginecontroller.frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import voogasalad.gameengine.frontend.EngineFrontEnd;
import voogasalad.player.util.ImageMaker;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of FrontEndController, which has public methods that FrontEndCommand objects can call by using reflection
 */
public class TowerDefenseFrontEndController extends FrontEndController {

    private static final double HEIGHT = 740;
    private static final double WIDTH = 800;
    private static final double INITIAL_GRID_WIDTH = 37;
    private static final double INITIAL_GRID_HEIGHT = 40;
    private static final int CIRCLE_ID = -10;
    private static final String LOSE_GAME_DIRECTORY = "voogasalad.player.resources.losegame.";
    private static final String NEXT_LEVEL_DIRECTORY = "voogasalad.player.resources.nextlevel.";
    private static final String WIN_GAME_DIRECTORY = "voogasalad.player.resources.wingame.";

    private double gridWidth = INITIAL_GRID_WIDTH;
    private double gridHeight = INITIAL_GRID_HEIGHT;
    private Map<String, Image> filePathMap = new HashMap<>();

    public TowerDefenseFrontEndController(EngineFrontEnd engineFrontEnd) {
        super(engineFrontEnd);
    }

    /**
     * Sets the level of the frontEnd, and clears the screen.
     * @param level
     */
    public void setLevel(int level) {
        getMyFrontEnd().setLevel(level);
    }

    /**
     * Move a node with a given ID to a position, with given orientation
     * @param ID
     * @param location
     * @param orientation
     */
    public void moveNode(int ID, Point2D.Double location, double orientation) {
        getMyFrontEnd().moveNode(ID, gridToLocation(location), orientation);
    }

    /**
     * Create a node with a given ID, nodeType. The image will be made from the filePath, and the size is how large it will be.
     * If the radius>0, the sprite will have radius of vision
     * @param ID
     * @param filePath=filePath for the image
     * @param nodeType=type of sprite (ex. "Attacker")
     * @param location
     * @param orientation
     * @param size
     * @param radius
     */
    public void createNode(int ID, String filePath, String nodeType, Point2D.Double location, double orientation, double size, double radius) {
        getMyFrontEnd().createNode(ID, filePath, nodeType, gridToLocation(location), orientation, size, radius);
    }

    /**
     * Remove node with a given ID.
     * @param ID
     */
    public void removeNode(int ID) {
        getMyFrontEnd().removeNode(ID);
    }

    /**
     * Draw a circle with a given color, and radius
     * @param color
     * @param radius
     */
    public void drawCircle(Color color, double radius) {
        Circle circle = new Circle(radius * gridHeight, color);
        circle.setOpacity(0.2);
        getMyFrontEnd().drawNode(circle, CIRCLE_ID, false, true);
    }

    /**
     * Add a map tile at a given grid loction, from the filePath
     * @param filePath
     * @param row
     * @param col
     */
    public void addMapTile(String filePath, int row, int col) {
        if(!filePathMap.containsKey(filePath)) {
            filePathMap.put(filePath, ImageMaker.getImage(filePath));
        }
        ImageView mapTile = new ImageView(filePathMap.get(filePath));
        mapTile.setFitHeight(gridHeight);
        mapTile.setFitWidth(gridWidth);
        mapTile.setTranslateX(gridWidth * col);
        mapTile.setTranslateY(gridHeight * row);
        getMyFrontEnd().drawNode(mapTile, mapTile.hashCode(), true, false);

    }

    /**
     * Sets the numbrer, row and column, which will be used to construct the map.
     * @param row
     * @param col
     */
    public void setRowAndCol(int row, int col) {
        gridHeight = HEIGHT/row;
        gridWidth = WIDTH/col;
    }

    /**
     * Add available defender/obstacle to the defenderTab
     * @param filePath
     * @param defenderType
     * @param cost
     */
    public void addToDefenderTab(String filePath, String defenderType, int cost) {
        getMyFrontEnd().addToDefenderTab(filePath, defenderType, cost);
    }

    /**
     * Update the status
     * @param statusType: "Health", etc
     * @param amount
     */
    public void setStatus(String statusType, int amount) {
        getMyFrontEnd().setStatus(statusType, amount);
    }

    /**
     * Called when the user lost
     */
    public void loseGame() {
        getMyFrontEnd().sendScore();
        getMyFrontEnd().endOfLevel(LOSE_GAME_DIRECTORY);
    }

    /**
     * Called when the user won
     */
    public void winGame() {
        getMyFrontEnd().sendScore();
        getMyFrontEnd().endOfLevel(WIN_GAME_DIRECTORY);
    }

    /**
     * Called when the user proceeds to the next level
     */
    public void nextLevel() {
        getMyFrontEnd().endOfLevel(NEXT_LEVEL_DIRECTORY);
    }

    /**
     * Called when an effect has to be shown at the location of a sprite with a given ID.
     * @param ID
     * @param radius
     * @param type
     */
    public void showEffect(int ID, double radius, String type) {
        getMyFrontEnd().showEffect(ID, radius, type);
    }

    private Point2D.Double gridToLocation(Point2D.Double gridPoint) {
        return new Point2D.Double(gridPoint.getY() * gridWidth, gridPoint.getX() * gridHeight);
    }
}
