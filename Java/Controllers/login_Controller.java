package Controllers;

// java libraries */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// javafx libraries
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

// proprietary libraries
import AIVA.Information;

/**
 * Manages functionality of the Login window
 */
public class login_Controller implements Initializable
{
    @FXML
    private Label loginRef; // Calls window content
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox remember;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //TEMPCODE
    }

    /**
     * Submit button action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleSubmitButton() throws IOException, InterruptedException
    {
        Information user = new Information(); // Calls necessary classes

        user.checkLogin(username.getText(), password.getText(), loginRef, remember); // Assesses login information
        username.setText("");
        password.setText("");
    }
}
