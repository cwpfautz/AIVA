package AIVA;

// java libraries
import java.io.IOException;

// javafx libraries
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException
    {
        Windows window = new Windows(); // Calls necessary classes

        window.showMain(primaryStage, true, false); // Displays startup window
    }

    public static void main(String[] args) {
        launch(args);
    }
}
