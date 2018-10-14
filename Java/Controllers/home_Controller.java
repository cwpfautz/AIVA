package Controllers;

// java libraries
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

// javafx libraries
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

// proprietary libraries
import AIVA.Animation;
import AIVA.Information;
import AIVA.Search;
import AIVA.Windows;

/**
 * Manages functionality of the Home window
 */
public class home_Controller implements Initializable {

    @FXML
    private CheckMenuItem rememberItem; // Calls menu content
    @FXML
    private RadioMenuItem lightTheme;
    @FXML
    private RadioMenuItem darkTheme;

    @FXML
    private Button searchButton; // Calls window content
    @FXML
    private TextField searchBar;

    private Information user = new Information(); // Calls necessary files
    private Windows window = new Windows();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try // Sets selection of Remember menu item
        {
            BufferedReader reader = new BufferedReader(new FileReader("database/userSave.csv"));
            if (Boolean.valueOf(reader.readLine()).equals(true))
            {
                rememberItem.setSelected(true);
            } else if (Boolean.valueOf(reader.readLine()).equals(false)) {
                rememberItem.setSelected(false);
            }
        } catch (Exception ex) { ex.printStackTrace(); }

        try // Sets selection of theme menu items
        {
            BufferedReader reader = new BufferedReader(new FileReader("database/theme.csv"));
            if (reader.readLine().equals("light"))
            {
                lightTheme.setSelected(true);
            } else {
                darkTheme.setSelected(true);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    /**
     * File -> User action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleFileUser() throws IOException { window.show("/FXML/user_Window.fxml", "  User Settings",
            242, 182); }

    /**
     * File -> Settings action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleFileSettings() throws IOException { window.show("/FXML/settings_Window.fxml",
            "  Settings", 215, 222); }

    /**
     * File -> CMD Prompt action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleFileCMD() throws IOException { Runtime.getRuntime().exec("cmd.exe /c start"); }

    /**
     * File -> Remember action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleFileRemember() throws IOException
    {
        if (rememberItem.isSelected())
        {
            user.setUserSave(true);
        } else if (!rememberItem.isSelected()) {
            user.setUserSave(false);
        }
    }

    /**
     * File -> Exit action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleFileExit() throws IOException { System.exit(0); }

    /**
     * File -> Calibrate action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleEditCalibrate() throws IOException { window.notify("Calibrating for ambient noise..."); }

    /*
    @FXML
    public void handleEditTest() throws IOException { window.show("/FXML/test_Window.fxml", "  Test",
            310, 310); }
    */

    /**
     * Edit -> Preferences action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleEditPreferences() throws IOException { window.show("/FXML/preferences_Window.fxml",
            "  Preferences", 310, 310); }

    /**
     * Edit -> Personalize action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleEditPersonalize() throws IOException { window.show("/FXML/personalize_Window.fxml",
            "  Personalize", 310, 310); }

    /**
     * Edit -> Layout action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleEditLayout() throws IOException { window.show("/FXML/layout_Window.fxml", "  Layout",
            310, 310); }

    /**
     * Edit -> Theme -> Light action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleViewThemeLight() throws IOException, InterruptedException
    {
        user.setData("Theme", "database/theme.csv", "light");
        changeTheme();
    }

    /**
     * Edit -> Theme -> Dark action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleViewThemeDark() throws IOException, InterruptedException
    {
        user.setData("Theme", "database/theme.csv", "dark");
        changeTheme();
    }

    /**
     * Theme transition
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    private void changeTheme() throws IOException, InterruptedException
    {
        Stage oldStage = (Stage) searchButton.getScene().getWindow(); // Replaces old window stage with a new one
        oldStage.close();
        Stage stage = (Stage) searchButton.getScene().getWindow();
        window.showMain(stage, false, false);
    }

    /**
     * Edit -> Theme -> Add action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleViewThemeAdd() throws IOException { window.show("/FXML/addTheme_Window.fxml",
            "  Add Theme", 310, 310); }

    /**
     * View -> Tools action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleViewTools() throws IOException { window.show("/FXML/tools_Window.fxml", "  Tools",
            310, 310); }

    /**
     * View -> Contacts action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleViewContacts() throws IOException { window.show("/FXML/contacts_Window.fxml",
            "  Contacts", 310, 310); }

    /**
     * View -> Logs action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleViewLogs() throws IOException { window.show("/FXML/logs_Window.fxml", "  Logs", 310,
            310); }

    /**
     * Help -> Find action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleHelpFind() throws IOException { window.show("/FXML/find_Window.fxml", "  Find", 310,
            310); }

    /**
     * Help -> Commands action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleHelpCommands() throws IOException { window.show("/FXML/commands_Window.fxml",
            "  Commands", 310, 310); }

    /**
     * Help -> About action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleHelpAbout() throws IOException { window.show("/FXML/about_Window.fxml",
            "  About", 310, 310); }

    /**
     * Search button action
     *
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    @FXML
    public void handleSearchButton() throws IOException, InterruptedException
    {
        Search query = new Search(); // Handles search bar input
        if (searchBar.getText().startsWith("move") || searchBar.getText().startsWith("go"))
        {
            Stage stage = (Stage) searchButton.getScene().getWindow();
            if (searchBar.getText().equalsIgnoreCase("move left"))
            {
                window.moveLeft(stage);
            } else if (searchBar.getText().equalsIgnoreCase("move right")) {
                window.moveRight(stage);
            } else if (searchBar.getText().equalsIgnoreCase("move down")) {
                window.moveDown(stage);
            } else if (searchBar.getText().equalsIgnoreCase("move up")) {
                window.moveUp(stage);
            } else if (searchBar.getText().equalsIgnoreCase("go home")) {
                window.goHome(stage);
            } else {
                query.routeText(searchBar.getText(), searchButton);
            }
        } else {
            query.routeText(searchBar.getText(), searchButton);
        }
        searchBar.setText("");
    }
}
