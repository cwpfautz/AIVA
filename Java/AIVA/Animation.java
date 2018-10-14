package AIVA;

// java libraries
import java.io.IOException;

// javafx libraries
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Contains functions for executing various window animations
 */
public class Animation
{
    /**
     * Home window animation
     *
     * @param stage
     * Home window stage
     * @param startPosX
     * Initial X-position
     * @param endPosX
     * Final X-position
     * @param startPosY
     * Initial Y-position
     * @param endPosY
     * Final Y-position
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    void homeAnimation(Stage stage, double startPosX, double endPosX, double startPosY, double endPosY)
            throws IOException
    {

        Timeline widthTimeline = widthAnimation(stage, 212.5, 1.5); // Establishes dimension timelines
        Timeline heightTimeline = heightAnimation(stage, 83.0, 1.5);

        Timeline xPosTimeline = xPosAnimation(stage, startPosX, endPosX); // Establishes position timelines
        Timeline yPosTimeline = yPosAnimation(stage, startPosY, endPosY);


        widthTimeline.play(); // Plays dimension timelines
        heightTimeline.play();
        heightTimeline.setOnFinished(e->{ // Plays position timelines after dimension timelines are complete
            xPosTimeline.play();
            yPosTimeline.play();
        });
    }

    /**
     * Login window animation
     *
     * @param stage
     * Login window stage
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
     void loginAnimation(Stage stage) throws IOException
     {
         /*
         Timeline widthTimeline = widthAnimation(stage, 231.0, 1.25); // Establishes dimension timeline
         Timeline heightTimeline = heightAnimation(stage, 129.0, 1.25);

         widthTimeline.play(); // Pla ys dimension timelines
         heightTimeline.play();
         */
    }

    /**
     * Window movement animation
     *
     * @param stage
     * Window stage
     * @param startPosX
     * Initial X-position
     * @param endPosX
     * Final X-position
     * @param startPosY
     * Initial Y-position
     * @param endPosY
     * Final Y-position
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    void travelAnimation(Stage stage, double startPosX, double endPosX, double startPosY, double endPosY)
            throws IOException
    {
        Timeline xPosTimeline = xPosAnimation(stage, startPosX, endPosX); // Establishes position timelines
        Timeline yPosTimeline = yPosAnimation(stage, startPosY, endPosY);

        xPosTimeline.play(); // Plays position timelines
        yPosTimeline.play();
    }

    /**
     * Window width resize animation
     *
     * @param stage
     * Window stage
     * @param endWidth
     * Final width
     * @param duration
     * Animation duration
     * @return
     * Width animation timeline
     */
    private Timeline widthAnimation(Stage stage, double endWidth, double duration)
    {
        DoubleProperty width = new SimpleDoubleProperty(0.0); // Sets initial window width
        Timeline widthTime = new Timeline(new KeyFrame(Duration.seconds(duration), new KeyValue(width, endWidth)));
        width.addListener((obs, oldValue, newValue)->stage.setWidth(newValue.doubleValue())); // Defines animation

        return widthTime;
    }

    /**
     * Window height resize animation
     *
     * @param stage
     * Window stage
     * @param endHeight
     * Final height
     * @param duration
     * Animation duration
     * @return
     * Height animation timeline
     */
    private Timeline heightAnimation(Stage stage, double endHeight, double duration)
    {
        DoubleProperty height = new SimpleDoubleProperty(0.0); // Sets initial window height
        Timeline heightTime = new Timeline(new KeyFrame(Duration.seconds(duration), new KeyValue(height, endHeight)));
        height.addListener((obs, oldValue, newValue)->stage.setHeight(newValue.doubleValue())); // Defines animation

        return heightTime;
    }

    /**
     * Horizontal window movement animation
     *
     * @param stage
     * Window stage
     * @param startPosX
     * Initial X-position
     * @param endPosX
     * Final X-position
     * @return
     * X-position animation timeline
     */
    private Timeline xPosAnimation(Stage stage, double startPosX, double endPosX)
    {
        DoubleProperty xPos = new SimpleDoubleProperty(startPosX); // Sets initial window X-position
        Timeline xPosTime = new Timeline(new KeyFrame(Duration.seconds(0.25), new KeyValue(xPos, endPosX)));
        xPos.addListener((obs, oldValue, newValue)->stage.setX(newValue.doubleValue())); // Defines animation

        return xPosTime;
    }

    /**
     * Vertical window movement animation
     *
     * @param stage
     * Window stage
     * @param startPosY
     * Initial Y-position
     * @param endPosY
     * Final Y-position
     * @return
     * Y-position animation timeline
     */
    private Timeline yPosAnimation(Stage stage, double startPosY, double endPosY)
    {
        DoubleProperty yPos = new SimpleDoubleProperty(startPosY); // Sets initial window Y-position
        Timeline yPosTime = new Timeline(new KeyFrame(Duration.seconds(0.25), new KeyValue(yPos, endPosY)));
        yPos.addListener((obs, oldValue, newValue)->stage.setY(newValue.doubleValue())); // Defines animation

        return yPosTime;
    }
}
