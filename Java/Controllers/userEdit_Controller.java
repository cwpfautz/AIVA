package Controllers;

// java libraries
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

// javafx libraries
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

// proprietary libraries
import AIVA.Information;
import AIVA.Scenes;
import AIVA.Windows;

/**
 * Manages functionality of the editable User window
 */
public class userEdit_Controller implements Initializable
{
    @FXML
    private Label reference; // Calls window content
    @FXML
    private TextField useruname;
    @FXML
    private TextField username;
    @FXML
    private TextField userpass;
    @FXML
    private TextField userphone;
    @FXML
    private TextField useremail;
    @FXML
    private Button save;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Information user = new Information(); // Calls necessary files

        try // Displays current user information as default text
        {
            useruname.setText(user.getData("database/userUName.csv"));
            username.setText(user.getData("database/userName.csv"));
            userpass.setText(user.getData("database/userPassword.csv"));
            userphone.setText(user.getData("database/userPhone.csv"));
            useremail.setText(user.getData("database/userEmail.csv"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        save.setDefaultButton(true); // Binds save button to keyboard enter key
    }

    /**
     * Cancel button action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleCancelButton() throws IOException
    {
        Scenes scene = new Scenes(); // Switches to non-editable scene
        scene.show(reference, "/FXML/user_Window.fxml", 252, 192);
    }

    /**
     * Save button action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleSaveButton() throws IOException
    {
        Dictionary<String, String> userInfo = new Hashtable<>(); // Sets up user information dictionary
        userInfo.put("database/userUName.csv", useruname.getText());
        userInfo.put("database/userName.csv", username.getText());
        userInfo.put("database/userPassword.csv", userpass.getText());
        userInfo.put("database/userPhone.csv", userphone.getText());
        userInfo.put("database/userEmail.csv", useremail.getText());
        Enumeration<String> key = userInfo.keys();
        Enumeration<String> element = userInfo.elements();

        while(key.hasMoreElements() || element.hasMoreElements()) // Saves user information in respective files
        {
            PrintWriter writer = new PrintWriter(key.nextElement());
            writer.println(element.nextElement());
            writer.close();
        }

        Scenes scene = new Scenes(); // Switches to non-editable scene
        scene.show(reference, "/FXML/user_Window.fxml", 252, 192);

        Windows window = new Windows(); // Notifies user that user information has been saved
        window.notify("New credentials saved");
    }
}
