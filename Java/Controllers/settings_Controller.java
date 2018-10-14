package Controllers;

// java libraries
import java.net.URL;
import java.util.ResourceBundle;

// javafx libraries
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Manages functionality of the Settings window
 */
public class settings_Controller implements Initializable
{
    @FXML
    private TreeView<String> settingsTree; // Calls tree menu content

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        TreeItem<String> settingsTrees = new TreeItem<>("Settings");
        settingsTrees.setExpanded(true);
        TreeItem<String> generalTree = new TreeItem<>("General");
        generalTree.setExpanded(true);
        TreeItem<String> userTree = new TreeItem<>("User");
        userTree.setExpanded(true);
        TreeItem<String> appearanceTree = new TreeItem<>("Appearance");
        appearanceTree.setExpanded(true);
        settingsTrees.getChildren().add(generalTree);
        settingsTrees.getChildren().add(userTree);
        settingsTrees.getChildren().add(appearanceTree);
        TreeItem<String> preferences = new TreeItem<>("Preferences");
        TreeItem<String> personalization = new TreeItem<>("Personalization");
        TreeItem<String> calibration = new TreeItem<>("Calibration");
        calibration.setExpanded(true);
        generalTree.getChildren().add(preferences);
        generalTree.getChildren().add(personalization);
        generalTree.getChildren().add(calibration);
        TreeItem<String> parameters = new TreeItem<>("Parameters");
        calibration.getChildren().add(parameters);
        TreeItem<String> settings = new TreeItem<>("User settings");
        userTree.getChildren().add(settings);
        TreeItem<String> layout = new TreeItem<>("Layout");
        appearanceTree.getChildren().add(layout);
        settingsTree.setRoot(settingsTrees);
    }
}
