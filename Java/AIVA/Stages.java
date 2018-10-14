package AIVA;

// javafx libraries
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Contains functions for setting window stage aspects
 */
class Stages
{
    /**
     * Window stage setup
     *
     * @param stage
     * Window stage
     */
    void setup(Stage stage, String title)
    {
        stage.setTitle(title);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/favicon.png")));
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
    }
}
