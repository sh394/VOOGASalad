package voogasalad.gameengine.frontend;

import javafx.scene.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that holds all the added sprites(FrontEndObject), such as attackers, defenders, and obstacles
 */
public class SpriteHolder {
    private Map<Integer, FrontEndObject> mySprites = new HashMap<>();
    private Map<Integer, Node> myVisions = new HashMap<>();

    /**
     * This method is called to add a new FrontEndObject as a sprite.
     * @param object: Adds the FrontEndObject object to mySprites
     */
    public void addObject(FrontEndObject object) {
        mySprites.put(object.getID(), object);
    }

    /**
     * This method is called when the FrontEndObject corresponding to the ID has a radius of vision
     * @param ID
     */
    public void addVision(int ID) {
        myVisions.put(ID, mySprites.get(ID).getVisibleArea());
    }

    /**
     * returns a list of sprites of specific type
     * @param type: type of sprite (ex. "Defender")
     * @return
     */
    public List<FrontEndObject> getSpecificType(String type) {
        List<FrontEndObject> list = new ArrayList<>();
        for(FrontEndObject myObject : mySprites.values()) {
            if(myObject.getType().equals(type)) {
                list.add(myObject);
            }
        }
        return list;
    }

    /**
     * returns all the 'radius of visions'
     * @return
     */
    public Map<Integer, Node> getVisions() {
        Map<Integer, Node> map = new HashMap<>();
        map.putAll(myVisions);
        return map;
    }

    /**
     * clears the sprites
     */
    public void clearAll() {
        mySprites.clear();
        myVisions.clear();
    }

    /**
     * returns the sprite with the corresponding ID
     * @param ID: the ID
     * @return
     */
    public FrontEndObject get(int ID) {
        return mySprites.get(ID);
    }

    /**
     * removes the sprite with the corresponding ID
     * @param ID: the ID
     */
    public void remove(int ID) {
        mySprites.remove(ID);
        myVisions.remove(ID);
    }
}
