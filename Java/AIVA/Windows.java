package AIVA;

// java libraries
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// javafx libraries
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Contains functions for displaying new windows
 */
public class Windows {
    /**
     * Main window setup and initialization
     *
     * @param stage   Home window stage
     * @param startup Startup indication
     * @param login   Login indication
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void showMain(Stage stage, Boolean startup, Boolean login) throws IOException, InterruptedException
    {
        Animation animate = new Animation(); // Calls necessary classes
        Listener listen = new Listener();
        Information user = new Information();
        Scene scene = null;
        Stages window = new Stages();

        window.setup(stage, "  AIVA"); // Sets aspects of window stage and displays
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        if (startup.equals(true))
        {
            Boolean save = user.getUserSave();
            if (save.equals(true) || login.equals(true))
            {
                double startPosX = primaryScreenBounds.getMinX() + 690; // Sets window start position
                double startPosY = primaryScreenBounds.getMaxY() - 690;
                // setAndAnimate(primaryScreenBounds, stage, startPosX, startPosY); // Displays home window animation
                if (user.getData("database/Position.csv").equals("topRight"))
                {
                    stage.setX(primaryScreenBounds.getMaxX() - 230);
                    stage.setY(primaryScreenBounds.getMinY() + 15);
                } else if (user.getData("database/Position.csv").equals("topLeft")) {
                    stage.setX(primaryScreenBounds.getMaxX() - 1520);
                    stage.setY(primaryScreenBounds.getMinY() + 15);
                } else if (user.getData("database/Position.csv").equals("bottomRight")) {
                    stage.setX(primaryScreenBounds.getMaxX() - 230);
                    stage.setY(primaryScreenBounds.getMinY() + 770);
                } else if (user.getData("database/Position.csv").equals("bottomLeft")) {
                    stage.setX(primaryScreenBounds.getMaxX() - 1520);
                    stage.setY(primaryScreenBounds.getMinY() + 770);
                }
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/home_Window.fxml"));
                scene = new Scene(root, 200, 48);
                // listen.start(); // Starts listening for vocal input
            } else if (save.equals(false)) {
                animate.loginAnimation(stage); // Displays login window animation
                stage.setX(primaryScreenBounds.getMaxX()/2 - 115);
                stage.setY(primaryScreenBounds.getMinY() + 225);
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/login_Window.fxml"));
                scene = new Scene(root, 230, 115);
            }
        } else if (startup.equals(false)) {
            setAndShow(primaryScreenBounds, stage, 15.0, 770.0);
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/home_Window.fxml"));
            scene = new Scene(root, 200, 48);

        }
        assert scene != null;
        scene.getStylesheets().add("/CSS/" + user.getData("database/theme.csv") + "_Theme.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * General window setup and initialization
     *
     * @param fxml   FXML window source
     * @param width  Window width
     * @param height Window height
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void show(String fxml, String title, double width, double height) throws IOException
    {
        Information user = new Information(); // Calls necessary classes
        Stages window = new Stages();

        Stage stage = new Stage(); // Sets aspects of window stage and displays
        window.setup(stage, title);
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add("/CSS/" + user.getData("database/theme.csv") + "_Theme.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Output response window
     *
     * @param output Desired output
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    void respond(String output) throws IOException
    {
        Information user = new Information(); // Calls necessary classes
        PrintWriter writer = new PrintWriter("database/Output.csv");
        Stages window = new Stages();

        writer.println(output); // Writes response to Output.csv
        writer.close();

        Stage stage = new Stage(); // Sets aspects of window stage and displays
        window.setup(stage, "  AIVA");
        stage.focusedProperty().addListener((obs, wasFocus, isFocus) ->
        {
            if (!isFocus) // Closes window if it is no longer the focus
            {
                stage.close();
            }
        });
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/output_Window.fxml"));
        Scene scene = new Scene(root, 200.0, 48.0);
        scene.getStylesheets().add("/CSS/" + user.getData("database/theme.csv") + "_Theme.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Output notification window
     *
     * @param notification Desired notification output
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void notify(String notification) throws IOException
    {
        Information user = new Information(); // Calls necessary classes
        PrintWriter writer = new PrintWriter("database/Output.csv");
        Stages window = new Stages();

        writer.println(notification); // Writes notification to Output.csv
        writer.close();

        Stage stage = new Stage(); // Sets aspects of window stage and displays
        window.setup(stage, "");
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> stage.close()); // Closes window after the delay timer completes
        delay.play();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        setAndShow(primaryScreenBounds, stage, 115.0, 700.0);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/output_Window.fxml"));
        Scene scene = new Scene(root, 200.0, 24.0);
        scene.getStylesheets().add("/CSS/" + user.getData("database/theme.csv") + "_Theme.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * General window travel animation
     *
     * @param primaryScreenBounds Screen dimensions access
     * @param stage               Window stage
     * @param startPosX           Window starting X-position
     * @param startPosY           Window starting Y-position
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void setAndAnimate(Rectangle2D primaryScreenBounds, Stage stage, double startPosX, double startPosY)
            throws IOException, InterruptedException
    {
        Information user = new Information(); // Calls necessary classes
        Animation animate = new Animation();

        if (user.getData("database/Position.csv").equals("topRight"))
        {
            double endPosX = primaryScreenBounds.getMaxX() - 230;
            double endPosY = primaryScreenBounds.getMinY() + 15;
            animate.homeAnimation(stage, startPosX, endPosX, startPosY, endPosY);
        } else if (user.getData("database/Position.csv").equals("topLeft")) {
            double endPosX = primaryScreenBounds.getMaxX() - 1520;
            double endPosY = primaryScreenBounds.getMinY() + 15;
            animate.homeAnimation(stage, startPosX, endPosX, startPosY, endPosY);
        } else if (user.getData("database/Position.csv").equals("bottomRight")) {
            double endPosX = primaryScreenBounds.getMaxX() - 230;
            double endPosY = primaryScreenBounds.getMinY() + 770;
            animate.homeAnimation(stage, startPosX, endPosX, startPosY, endPosY);
        } else if (user.getData("database/Position.csv").equals("bottomLeft")) {
            double endPosX = primaryScreenBounds.getMaxX() - 1520;
            double endPosY = primaryScreenBounds.getMinY() + 770;
            animate.homeAnimation(stage, startPosX, endPosX, startPosY, endPosY);
        }
    }

    /**
     * General window placement
     *
     * @param primaryScreenBounds Screen dimensions access
     * @param stage               Window stage
     * @param minYTop             Window top Y-position
     * @param minYBottom          Window bottom Y-position
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void setAndShow(Rectangle2D primaryScreenBounds, Stage stage, Double minYTop, Double minYBottom)
            throws IOException
    {
        Information user = new Information();// Calls necessary classes

        if (user.getData("database/Position.csv").equals("topRight"))
        {
            stage.setX(primaryScreenBounds.getMaxX() - 230);
            stage.setY(primaryScreenBounds.getMinY() + minYTop);
        } else if (user.getData("database/Position.csv").equals("topLeft")) {
            stage.setX(primaryScreenBounds.getMaxX() - 1520);
            stage.setY(primaryScreenBounds.getMinY() + minYTop);
        } else if (user.getData("database/Position.csv").equals("bottomRight")) {
            stage.setX(primaryScreenBounds.getMaxX() - 230);
            stage.setY(primaryScreenBounds.getMinY() + minYBottom);
        } else if (user.getData("database/Position.csv").equals("bottomLeft")) {
            stage.setX(primaryScreenBounds.getMaxX() - 1520);
            stage.setY(primaryScreenBounds.getMinY() + minYBottom);
        }
    }

    /**
     * Leftward window travel action
     *
     * @param stage Window stage
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void moveLeft(Stage stage) throws IOException
    {
        Animation animate = new Animation(); // Calls necessary classes
        Information user = new Information();
        PrintWriter writer = new PrintWriter(new FileWriter("database/Position.csv"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Windows window = new Windows();

        double startPosX = stage.getX(); // Sets window start position
        double startPosY = stage.getY();

        if (stage.getY() == primaryScreenBounds.getMinY() + 15.199999809265137) // Records window location
        {
            writer.println("topLeft");
            writer.close();
        } else if (stage.getY() == primaryScreenBounds.getMinY() + 770) {
            writer.println("bottomLeft");
            writer.close();
        } else {
            System.err.println("Terminal: Internal traveling error");
        }

        if (stage.getX() != primaryScreenBounds.getMaxX() - 1520)
        {
            double endPosX = primaryScreenBounds.getMaxX() - 1520;
            animate.travelAnimation(stage, startPosX, endPosX, startPosY, startPosY);
        } else {
            window.notify(user.getData("database/aivaName.csv") + ": This is as far left as I can go");
        }
    }

    /**
     * Rightward window travel action
     *
     * @param stage Window stage
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void moveRight(Stage stage) throws IOException
    {
        Animation animate = new Animation(); // Calls necessary classes
        Information user = new Information();
        PrintWriter writer = new PrintWriter(new FileWriter("database/Position.csv"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Windows window = new Windows();

        double startPosX = stage.getX(); // Sets window start position
        double startPosY = stage.getY();

        if (stage.getY() == primaryScreenBounds.getMinY() + 15.199999809265137) // Records window location
        {
            writer.println("topRight");
            writer.close();
        } else if (stage.getY() == primaryScreenBounds.getMinY() + 770) {
            writer.println("bottomRight");
            writer.close();
        } else {
            System.err.println("Terminal: Internal travel error");
        }

        if (stage.getX() != primaryScreenBounds.getMaxX() - 230)
        {
            double endPosX = primaryScreenBounds.getMaxX() - 230;
            animate.travelAnimation(stage, startPosX, endPosX, startPosY, startPosY);
        } else {
            window.notify(user.getData("database/aivaName.csv") + ": This is as far right as I can go");
        }
    }

    /**
     * Downward window travel action
     *
     * @param stage Window stage
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void moveDown(Stage stage) throws IOException
    {
        Animation animate = new Animation(); // Calls necessary classes
        Information user = new Information();
        PrintWriter writer = new PrintWriter(new FileWriter("database/Position.csv"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Windows window = new Windows();

        double startPosX = stage.getX(); // Sets window start position
        double startPosY = stage.getY();

        if (stage.getX() == primaryScreenBounds.getMaxX() - 230) // Records window location
        {
            writer.println("bottomRight");
            writer.close();
        } else if (stage.getX() == primaryScreenBounds.getMaxX() - 1520) {
            writer.println("bottomLeft");
            writer.close();
        } else {
            System.err.println("Terminal: Internal travel error");
        }

        if (stage.getY() != primaryScreenBounds.getMinY() + 770)
        {
            double endPosY = primaryScreenBounds.getMinY() + 770;
            animate.travelAnimation(stage, startPosX, startPosX, startPosY, endPosY);
        } else {
            window.notify(user.getData("database/aivaName.csv") + ": This is as far down as I can go");
        }
    }

    /**
     * Upward window travel action
     *
     * @param stage Window stage
     * @throws IOException General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void moveUp(Stage stage) throws IOException
    {
        Animation animate = new Animation(); // Calls necessary classes
        Information user = new Information();
        PrintWriter writer = new PrintWriter(new FileWriter("database/Position.csv"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Windows window = new Windows();

        double startPosX = stage.getX(); // Sets window start position
        double startPosY = stage.getY();

        if (stage.getX() == primaryScreenBounds.getMaxX() - 230) // Records window location
        {
            writer.println("topRight");
            writer.close();
        } else if (stage.getX() == primaryScreenBounds.getMaxX() - 1520) {
            writer.println("topLeft");
            writer.close();
        } else {
            System.err.println("Terminal: Internal travel error");
        }

        if (stage.getY() != primaryScreenBounds.getMinY() + 15.199999809265137)
        {
            double endPosY = primaryScreenBounds.getMinY() + 15;
            animate.travelAnimation(stage, startPosX, startPosX, startPosY, endPosY);
        } else {
            window.notify(user.getData("database/aivaName.csv") + ": This is as far up as I can go");
        }
    }

    public void goHome(Stage stage) throws IOException
    {
        Animation animate = new Animation(); // Calls necessary classes
        Information user = new Information();
        PrintWriter writer = new PrintWriter(new FileWriter("database/Position.csv"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Windows window = new Windows();

        double startPosX = stage.getX(); // Sets window start position
        double startPosY = stage.getY();

        writer.println("topRight"); // Records window location
        writer.close();

        if (stage.getX() == primaryScreenBounds.getMaxX()-230 && stage.getY() == primaryScreenBounds.getMinY() +
                15.199999809265137)
        {
            window.notify(user.getData("database/aivaName.csv")+": I'm already home");
        } else {
            double endPosX = primaryScreenBounds.getMaxX()-230;
            double endPosY = primaryScreenBounds.getMinY()+15;
            animate.travelAnimation(stage, startPosX, endPosX, startPosY, endPosY);
        }
    }

    /**
     * Failed login notification
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    void denied() throws IOException
    {
        Information user = new Information();
        PrintWriter writer = new PrintWriter("database/Output.csv");
        Stages window = new Stages();

        writer.println("ACCESS DENIED"); // Writes 'ACCESS DENIED" to Output.csv
        writer.close();

        Stage stage = new Stage(); // Sets aspects of window stage and displays
        window.setup(stage, "  Try Again");
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> stage.close());
        delay.play();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMaxX()/2 - 115);
        stage.setY(primaryScreenBounds.getMinY() + 380);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/output_Window.fxml"));
        Scene scene = new Scene(root, 230.0, 24.0);
        scene.getStylesheets().add("/CSS/" + user.getData("database/theme.csv") + "_Theme.css");
        stage.setScene(scene);
        stage.show();
    }
}
