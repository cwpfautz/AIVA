package AIVA;

// java libraries
import java.io.IOException;

// javafx libraries
import javafx.scene.control.Button;
import javafx.stage.Stage;

// python libraries

// sphinx libraries
import edu.cmu.sphinx.api.SpeechResult;

/**
 * Contains functions for managing text and vocal input
 */
public class Search
{


    /**
     * Text search route
     *
     * @param query
     * Text input
     * @param reference
     * Window stage reference
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    public void routeText(String query, Button reference) throws IOException, InterruptedException
    {
        Information user = new Information(); // Calls necessary classes
        Windows window = new Windows();

        String search = query.toLowerCase(); // Routes query based on context
        if (search.equals("hello") || search.equals("hey") || search.equals("heyo") || search.equals("hi"))
        {
            window.respond(user.getData("database/aivaName.csv")+": Hey there!");
        } else if ((search.startsWith("who") || search.startsWith("what")) && search.contains("are") &&
                search.contains("you")) {
            window.respond(user.getData("database/aivaName.csv")+": I'm your AI virtual "+ "assistant!");
        } else if (search.startsWith("thank")) {
            window.respond(user.getData("database/aivaName.csv")+": You're welcome!");
        } else if (search.startsWith("open")) {
            open(search); // Opens specific computer applications
        } else if (search.startsWith("change") || search.startsWith("set")) {
            change(search, query, user, window, reference); // Changes information in database files
        } else if (search.startsWith("what ") || search.startsWith("how ") || search.startsWith("who ") ||
                search.contains("my ") || search.contains(" i ")) {
            get(search, query, user, window); // Gets information from database files
        } else if (search.equals("save mode")) {
            window.respond("Save mode: "+user.getData("database/userAge.csv"));
        } else if (search.contains(".com") || search.contains(".org") || search.contains(".net") ||
                search.contains(".edu")) {
            web(search); // Searches specific web addresses
        } else if (search.startsWith("say")) {
            window.notify(user.getData("database/aivaName.csv")+": "+query.substring(3));
            if (query.substring(3).equalsIgnoreCase("") ||
                    query.substring(3).equalsIgnoreCase(" "))
            {
                window.notify(user.getData("database/aivaName.csv")+": Could you be more specific?");
            }
        } else if (search.equalsIgnoreCase("close")) {
            System.exit(0);
        } else {
            String web = search.replaceAll(" ", "+"); // Searches Google as default procedure
            Runtime.getRuntime().exec("cmd /c start www.google.com/search?q="+web);
        }
    }

    /**
     * Computer applications access
     *
     * @param search
     * Refined text input specifying application name
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void open(String search) throws IOException
    {
        if (search.contains("cmd") || search.contains("command prompt") || search.contains("terminal"))
        {
            Runtime.getRuntime().exec("cmd /c start cmd.exe");
        } else if (search.contains("chrome")) {
            Runtime.getRuntime().exec("cmd /c start chrome.exe");
        } else if (search.contains("file")) {
            Runtime.getRuntime().exec("cmd /c start explorer.exe");
        } else if (search.contains("user settings")) {
            Windows window = new Windows();
            window.show("/FXML/user_Window.fxml", "  User Settings", 242, 182);
        } else if (search.contains("settings")) {
            Runtime.getRuntime().exec("cmd /c start ms-settings:");
        } else if (search.contains("photo") || search.contains("pic")) {
            Runtime.getRuntime().exec("cmd /c start ms-photos:");
        } else {
            String web = search.replaceAll(" ", "+");
            Runtime.getRuntime().exec("cmd /c start www.google.com/search?q="+web);
        }
    }

    /**
     * User information change
     *
     * @param search
     * Refined text input specifying information change
     * @param query
     * Unedited text input
     * @param user
     * User information access
     * @param window
     * Notification window
     * @param reference
     * Window stage reference
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void change(String search, String query, Information user, Windows window, Button reference)
            throws IOException, InterruptedException
    {
        if (search.contains("username"))
        {
            if (search.contains("to"))
                {
                    user.setData("Username", "database/userUName.csv",
                            query.substring(query.indexOf("to")+3));
            } else { window.notify("Could you be more specific?"); }
        } else if ((search.contains("your") || search.contains("aiva")) && search.contains("name")) {
            if (search.contains("to"))
            {
                user.setData("AIVA name", "database/aivaName.csv",
                        query.substring(search.indexOf("to")+3));
            } else { window.notify("Could you be more specific?"); }
        } else if (search.contains("name")) {
            if (search.contains("to"))
            {
                user.setData("Name", "database/userName.csv",
                        query.substring(search.indexOf("to")+3));
            } else { window.notify("Could you be more specific?"); }
        } else if (search.contains("password")) {
            if (search.contains("to"))
            {
                user.setData("Password", "database/userPassword.csv",
                        query.substring(search.indexOf("to")+3));
            } else { window.notify("Could you be more specific?"); }
        } else if (search.contains("phone")) {
            if (search.contains("to"))
            {
                user.setData("Phone number", "database/userPhone.csv",
                        query.substring(search.indexOf("to")+3));
            } else { window.notify("Could you be more specific?"); }
        } else if (search.contains("email")) {
            if (search.contains("to"))
            {
                user.setData("Email", "database/userEmail.csv",
                        query.substring(search.indexOf("to")+3));
            } else { window.notify("Could you be more specific?"); }
        } else if (search.contains("age")) {
            if (search.contains("to"))
            {
                user.setData("Age", "database/userAge.csv",
                        query.substring(search.indexOf("to")+3));
            } else { window.notify("Could you be more specific?"); }
        } else if (search.contains("save")) {
            if (search.contains("to"))
            {
                user.setData("Save mode", "database/userSave.csv",
                        query.substring(search.indexOf("to")+3));
            } else { window.notify("Could you be more specific?"); }
        } else if (search.contains("theme")) {
            changeTheme(search, user, window, reference);
        } else {
            String web = search.replaceAll(" ", "+");
            Runtime.getRuntime().exec("cmd /c start www.google.com/search?q="+web);
        }
    }

    /**
     * Window theme change
     *
     * @param search
     * Refined text input specifying desired theme
     * @param user
     * User information access
     * @param window
     * Notification window
     * @param reference
     * Window stage reference
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void changeTheme(String search, Information user, Windows window, Button reference)
            throws IOException, InterruptedException
    {
        if (search.contains("to"))
        {
            if (search.contains("light") || search.contains("dark"))
            {
                if (search.contains("light"))
                {
                    user.setData("Theme", "database/theme.csv", "light");
                } else if (search.contains("dark")) {
                    user.setData("Theme", "database/theme.csv", "dark");
                }

                Stage oldStage = (Stage) reference.getScene().getWindow();
                oldStage.close(); // Closes login window

                Stage stage = new Stage(); // Restarts the home window with specified theme
                window.showMain(stage, false, false);
            } else { window.notify("That theme does not exist"); }
        } else { window.notify("Could you be more specific?"); }
    }

    /**
     * User information viewer
     *
     * @param search
     * Refined text input specifying desired information
     * @param query
     * Unedited text input
     * @param user
     * User information access
     * @param window
     * Output window
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void get(String search, String query, Information user, Windows window) throws IOException
    {
        if (search.contains("username"))
        {
            window.respond("Username: "+user.getData("database/userUName.csv"));
        } else if (search.contains("your") && search.contains("name")) {
            window.respond(user.getData("database/aivaName.csv")+": Take a guess");
        } else if (search.contains("name")) {
            window.respond("Name: "+user.getData("database/userName.csv"));
        } else if (search.contains("password")) {
            window.respond("Password: "+user.getData("database/userPassword.csv"));
        } else if (search.contains("phone")) {
            window.respond("Phone number: "+user.getData("database/userPhone.csv"));
        } else if (search.contains("email")) {
            window.respond("Email: "+user.getData("database/userEmail.csv"));
        } else if (search.contains("age")) {
            window.respond("Age: "+user.getData("database/userAge.csv"));
        } else if (search.contains("what") && query.contains("you") && query.contains("do")) {
            window.respond(user.getData("database/aivaName.csv")+": Whatever you tell me to do");
        } else if (((search.contains("what") || search.contains("who")) && search.contains("you"))) {
            window.respond(user.getData("database/aivaName.csv")+": Your AI virtual assistant!");
        } else if (search.contains("how") && search.contains("are") && search.contains("you")) {
            window.respond(user.getData("database/aivaName.csv")+": As well as an AI can be!");
        } else if (search.contains("how") && search.contains("you") && search.contains("work")) {
            window.respond(user.getData("database/aivaName.csv")+": That's a secret ;)");
        } else if (search.contains("old") && search.contains("i")) {
            window.respond("Age: "+user.getData("database/userAge.csv"));
        } else if (search.contains("am") && search.contains(" i")) {
            window.respond(user.getData("database/aivaName.csv")+": You're "+
                    user.getData("database/userName.csv")+"!");
        } else {
            String web = search.replaceAll(" ", "+");
            Runtime.getRuntime().exec("cmd /c start www.google.com/search?q="+web);
        }
    }

    /**
     * Textual web search
     *
     * @param search
     * Refined text input specifying web address
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void web(String search) throws IOException
    {
        if (!(search.contains("http://") || search.contains("https://") || search.contains("www"))) {
            Runtime.getRuntime().exec("cmd /c start www."+search);
        } else {
            Runtime.getRuntime().exec("cmd /c start "+search);
        }
    }

    /**
     * Vocal search route
     *
     * @param result
     * Vocal input
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    void routeVoice(SpeechResult result) throws IOException {

        String search = result.getHypothesis(); // Routes query based on context
        if (search.contains("AIVA")) // Listens for keyword "AIVA"
        {
            if (search.contains("OPEN") || search.contains("PULL") || search.contains("GO TO"))
            {
                openByVoice(search);
            } else if (search.contains("CLOSE") || search.contains("EXIT")){
                closeByVoice(search);
            } else if (search.equalsIgnoreCase("AIVA TERMINATE")) {
                Runtime.getRuntime().exec("TASKKILL /IM chrome.exe /F");
                Runtime.getRuntime().exec("TASKKILL /IM explorer.exe /F");
                Runtime.getRuntime().exec("TASKKILL /IM cmd.exe /F");
                Runtime.getRuntime().exec("cmd /c start explorer.exe");
            }
        }
    }

    /**
     * Computer applications voice activation
     *
     * @param search
     * Vocal input
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void openByVoice(String search) throws IOException
    {
        if (search.contains("CHROME"))
        {
            Runtime.getRuntime().exec("cmd /c start chrome.exe");
        } else if (search.contains("FILE") || search.contains("FILES") || search.contains("EXPLORER")) {
            Runtime.getRuntime().exec("cmd /c start explorer.exe");
        } else if (search.contains("COMMAND PROMPT") || search.contains("TERMINAL")) {
            Runtime.getRuntime().exec("cmd /c start cmd.exe");
        } else if (search.contains("MAIL")) {
            Runtime.getRuntime().exec("cmd /c start www.gmail.com");
        } else if (search.contains("YOUTUBE")) {
            Runtime.getRuntime().exec("cmd /c start www.youtube.com");
        } else if (search.contains("DRIVE")) {
            Runtime.getRuntime().exec("cmd /c start www.drive.google.com");
        } else if (search.contains("GOOGLE")) {
            Runtime.getRuntime().exec("cmd /c start www.google.com");
        } else if (search.contains("NETFLIX")) {
            Runtime.getRuntime().exec("cmd /c start www.netflix.com");
        }
    }

    /**
     * Computer applications voice termination
     *
     * @param search
     * Vocal input
     * @throws IOException
     * General detection of exceptions produced by failed or interrupted I/O operations
     */
    private void closeByVoice(String search) throws IOException
    {
        if (search.contains("CHROME"))
        {
            Runtime.getRuntime().exec("TASKKILL /IM chrome.exe /F");
        } else if (search.contains("FILE") || search.contains("FILES") || search.contains("EXPLORER")) {
            Runtime.getRuntime().exec("TASKKILL /IM explorer.exe /F");
        } else if (search.contains("COMMAND") || search.contains("TERMINAL")) {
            Runtime.getRuntime().exec("TASKKILL /IM cmd.exe /F");
        } else if (search.contains("ALL")) {
            Runtime.getRuntime().exec("TASKKILL /IM chrome.exe /F");
            Runtime.getRuntime().exec("TASKKILL /IM explorer.exe /F");
            Runtime.getRuntime().exec("TASKKILL /IM cmd.exe /F");
        }
    }

    private void parse(String search)
    {

    }
}
