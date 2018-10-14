package AIVA;

// java libraries
import java.io.IOException;

// javafx libraries
import javafx.concurrent.Service;
import javafx.concurrent.Task;

// sphinx libraries
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

/**
 * Contains functions that create new threads for vocal input configuration and analysis
 */
public class Listener extends Service<Void>
{
    /**
     * New thread for vocal input
     *
     * @return
     * New task
     */
    public Task<Void> createTask()
    {
        return new Task<Void>()
        {
            /**
             * Vocal input setup
             *
             * @return
             * Null
             * @throws IOException
             * General detection of exceptions produced by failed or interrupted I/O operations
             */
            public Void call() throws IOException
            {
                Search search = new Search(); // Calls necessary classes

                Configuration configuration = new Configuration(); // Creates vocal recognition configuration
                configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
                configuration.setDictionaryPath("resource:/Commands/2449.dic");
                configuration.setLanguageModelPath("resource:/Commands/2449.lm");

                LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
                recognize.startRecognition(true); // Starts vocal recognizer

                SpeechResult result; // Repeatedly listens for new vocal input
                while (!(result=recognize.getResult()).getHypothesis().equalsIgnoreCase(null))
                {
                    System.out.println(result.getHypothesis());
                    search.routeVoice(result); // Routes vocal input based on content
                }
                return null;
            }
        };
    }
}
