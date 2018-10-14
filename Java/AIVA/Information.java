package AIVA;

// java libraries
import java.io.*;

// javafx libraries
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// annotations libraries
import org.jetbrains.annotations.NotNull;

/**
 * Contains functions for managing and checking local information
 */
public class Information
{
    /**
     * File reader
     *
     * @param database
     * Target file
     * @return
     * Buffered File reader
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @NotNull
    private BufferedReader initializeReader(String database) throws IOException { return new BufferedReader(new
            FileReader(database)); } // Initializes file reader

    /**
     * File writer
     *
     * @param database
     * Target file
     * @return
     * Print File writer
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @NotNull
    private PrintWriter initializeWriter(String database) throws IOException { return new PrintWriter(database,
            "UTF-8"); } // Initializes file writer

    /**
     * Information getter
     *
     * @param file
     * Target file
     * @return
     * Information
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    public String getData(String file) throws IOException { return initializeReader(
            file).readLine(); } // Retrieves information from specified file

    /**
     * Information setter
     *
     * @param info
     * Information name
     * @param file
     * Target file
     * @param newData
     * New information
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void setData(String info, String file, String newData) throws IOException
    {
        Windows window = new Windows(); // Calls necessary classes

        PrintWriter writer = initializeWriter(file);
        writer.println(newData); // Inputs new information into specified file
        writer.close();

        window.notify(info+" set to "+newData); // Notifies user of data change
    }

    /**
     * Save mode status getter
     *
     * @return
     * Boolean status
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    Boolean getUserSave() throws IOException { return Boolean.valueOf(initializeReader(
            "database/userSave.csv").readLine()); } // Retrieves status of user save mode

    /**
     * Save mode status setter
     *
     * @param setSave
     * Boolean status
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void setUserSave(Boolean setSave) throws IOException
    {
        Windows window = new Windows(); // Calls necessary classes

        PrintWriter writer = initializeWriter("database/userSave.csv");
        writer.println(setSave); // Inputs new save mode status
        writer.close();

        if (setSave.equals(true)) // Notifies user of save mode status change
        {
            window.notify("User save mode activated");
        } else {
            window.notify("User save mode deactivated");
        }
    }

    /**
     * Login information checker
     *
     * @param unameIn
     * Username
     * @param passIn
     * Password
     * @param reference
     * Stage reference
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void checkLogin(String unameIn, String passIn, Label reference, CheckBox remember)
            throws IOException, InterruptedException
    {
        Windows window = new Windows();

        if (unameIn.equals(getData("database/userUName.csv")) && passIn.equals(getData(
                "database/userPassword.csv"))) // Assesses username and password input for expected values
        {
            if (remember.isSelected())
            {
                PrintWriter writer = initializeWriter("database/userSave.csv");
                writer.println(true); // Inputs new save mode status
                writer.close();
            }

            Stage oldStage = (Stage) reference.getScene().getWindow();
            oldStage.close(); // Closes login window

            Stage stage = new Stage();
            window.showMain(stage, true, true); // Displays home window
        } else { window.denied(); } // Notifies user that username and password input did not match expected values
    }
}
