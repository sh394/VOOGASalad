package voogasalad.authoring.controller.gameobjectsbuilder.attacker;

import voogasalad.authoring.authoringfeature.AbstractFeature;
import voogasalad.authoring.authoringfeature.Attacker;
import voogasalad.authoring.controller.AttackerObserver;
import voogasalad.authoring.controller.gameobjectsbuilder.AbstractAttributePaneManager;
import voogasalad.authoring.controller.gameobjectsbuilder.AbstractGameObjectController;
import voogasalad.authoring.model.Model;
import voogasalad.authoring.util.VisConstant;
import voogasalad.authoring.util.VisualizationUtil;


/**
 * Controller for attackers
 */
public class AttackerController extends AbstractGameObjectController {
    private static final String DEFAULT_ATT_NAME = VisConstant.gameObjectBundle.getString("attackerDefaultName");
    private static final String DEFAULT_ATT_IMG = "defaultAttacker.png";
    private static final String type = "Attacker";
    private static final String title = VisConstant.gameObjectBundle.getString("attackerControllerTitle");

    /**
     * Default constructor
     *
     * @param model model for all objects
     */
    public AttackerController(Model model) {
        super(model);
        this.setTitle(title);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public AbstractFeature createNewObj() {
        return new Attacker(VisualizationUtil.GAMEOBJECT_IMAGES_FOLDER + DEFAULT_ATT_IMG, DEFAULT_ATT_NAME, 1, 1, 1);
    }

    @Override
    public String getDefaultName() {
        return DEFAULT_ATT_NAME;
    }

    @Override
    public AbstractAttributePaneManager createCentralPane() {
        return new AttackerAttributePaneManager(this);
    }


    @Override
    public void updateObservers() {
        for (Object obs : getObservers()) {
            AttackerObserver observer = (AttackerObserver) obs;
            observer.updateAttackers();
        }
    }


}
