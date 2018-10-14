package Controllers;

// java libraries
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// javafx libraries
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// proprietary libraries
import AIVA.Information;
import AIVA.Scenes;

/**
 * Manages functionality of the non-editable User window
 */
public class user_Controller implements Initializable
{
    @FXML
    private Label username; // Calls window content
    @FXML
    private Label name;
    @FXML
    private Label password;
    @FXML
    private Label phone;
    @FXML
    private Label email;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Information user = new Information(); // Calls necessary classes

        try // Displays current user information
        {
            username.setText(user.getData("database/userUName.csv"));
            name.setText(user.getData("database/userName.csv"));
            password.setText(user.getData("database/userPassword.csv"));
            phone.setText(user.getData("database/userPhone.csv"));
            email.setText(user.getData("database/userEmail.csv"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Edit button action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleEditButton() throws IOException
    {
        Scenes scene = new Scenes(); // Switches to editable scene
        scene.show(username, "/FXML/user_EditWindow.fxml", 280, 192);
    }

    /**
     * Done button action
     */
    @FXML
    public void handleDoneButton()
    {
        Stage oldStage = (Stage) username.getScene().getWindow();
        oldStage.close();
    }
}
