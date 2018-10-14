package AIVA;

// java libraries
import java.io.IOException;

// javafx libraries
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contains functions for setting new scenes within stages
 */
public class Scenes
{
    /**
     * Scene change
     *
     * @param reference
     * Window stage reference
     * @param fxml
     * FXML window source
     * @param width
     * Window width
     * @param height
     * Window height
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void show(Label reference, String fxml, int width, int height) throws IOException
    {
        Information user = new Information(); // Calls necessary classes

        Stage stage = (Stage) reference.getScene().getWindow(); // Sets new scene in specified stage
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add("/CSS/"+user.getData("database/theme.csv")+"_Theme.css");
        stage.setScene(scene);
        stage.show();
    }
}
