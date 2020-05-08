import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    public void button1Handler(ActionEvent event) throws IOException {
        Parent worldSummary = FXMLLoader.load(getClass().getResource("fxml/WorldSummary.fxml"));
        Scene charScene = new Scene(worldSummary);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(charScene);
        window.show();

    }

    @FXML
    public void button2Handler(ActionEvent event) throws IOException {
        Parent chart = FXMLLoader.load(getClass().getResource("fxml/BarChart.fxml"));
        Scene charScene = new Scene(chart);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(charScene);
        window.show();
    }

}
