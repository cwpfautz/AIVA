package Controllers;

// java libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// javafx libraries
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * Manages functionality of the Output window
 */
public class output_Controller implements Initializable
{
    @FXML
    private Label output; // Calls window content

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try // Displays the content of Output.csv
        {
            BufferedReader reader = new BufferedReader(new FileReader("database/Output.csv"));
            output.setText(reader.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
