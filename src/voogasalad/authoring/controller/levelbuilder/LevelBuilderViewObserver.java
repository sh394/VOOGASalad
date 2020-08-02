package voogasalad.authoring.controller.levelbuilder;

/**
 * Observer for the LevelBuilderView
 *
 * @author Goh Khian Wei
 */
public interface LevelBuilderViewObserver {

    void storeLevel();

    void loadLevel(String levelName);
}
