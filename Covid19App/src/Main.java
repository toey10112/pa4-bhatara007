import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * initialize class for rum the application.
 *
 * @author Bhatara Chaemchan
 */
public class Main extends Application {

    @Override
    /**
     * Method to initialize scene and stage.
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        primaryStage.setTitle("Covid19 Tracker");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Mathod for run Application.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
