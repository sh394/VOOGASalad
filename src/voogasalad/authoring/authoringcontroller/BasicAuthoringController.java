package voogasalad.authoring.authoringcontroller;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import voogasalad.authoring.controller.Controller;
import voogasalad.authoring.model.BasicModel;
import voogasalad.authoring.model.Model;
import voogasalad.authoring.view.AuthoringView;
import voogasalad.authoring.view.BasicAuthoringView;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Overall controller for the game authoring environment
 *
 * @author Goh Khian Wei
 */
public class BasicAuthoringController implements AuthoringController {
    private static final String RESOURCES_PATH = "voogasalad/authoring/authoringcontroller/resources/";
    private static final String CONTROLLERS_RESOURCE = "controllers";
    private static final String ERROR_MESSAGES_RESOURCE = "errorMessages";
    private static final String ERROR_INITIALISATION_KEY = "ControllerInitialisationError";

    private AuthoringView myView;
    private Map<String, Controller> myControllers;
    private Model model;
    private ResourceBundle errorMessages;

    public BasicAuthoringController() {
        errorMessages = ResourceBundle.getBundle(RESOURCES_PATH + ERROR_MESSAGES_RESOURCE);
        myView = new BasicAuthoringView();
        myControllers = new HashMap<>();
        model = new BasicModel();
        ResourceBundle controllers = ResourceBundle.getBundle(RESOURCES_PATH + CONTROLLERS_RESOURCE);
        initControllers(controllers);
    }

    private void initControllers(ResourceBundle controllers) {
        loadControllers(controllers);
        attachObservers(controllers);
    }

    private void loadControllers(ResourceBundle controllers) {
        controllers.getKeys().asIterator().forEachRemaining(key -> {
            loadController(key, controllers.getString(key).split(",")[0]);
        });
    }

    private void loadController(String controllerName, String classPath) {
        try {
            Class<?> clazz = Class.forName(classPath);
            Controller controller = (Controller) clazz.getDeclaredConstructor(Model.class).newInstance(model);
            myView.addNode(controller.getName(), controller.getNode());
            myControllers.put(controllerName, controller);
        } catch (Exception e) {
            // ERROR HANDLING COMPLETE
            new Alert(Alert.AlertType.ERROR, errorMessages.getString(ERROR_INITIALISATION_KEY)).showAndWait();
        }
    }

    private void attachObservers(ResourceBundle controllers) {
        myControllers.keySet().forEach(controller -> {
            String allObservers = controllers.getString(controller).split(",")[1];
            if (!allObservers.isBlank()) {
                String[] observerList = allObservers.split(";");
                for (String observer : observerList) {
                    if (myControllers.containsKey(observer)) {
                        myControllers.get(controller).addObserver(myControllers.get(observer));
                    }
                }
            }
        });
    }

    @Override
    public Node getView() {
        return myView.getNode();
    }
}
