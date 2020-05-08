import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.jar.JarOutputStream;

public class WorldController implements Initializable {

    GraphData gd = new GraphData();

    @FXML
    private Label lb1;

    @FXML
    private Label lb4;

    @FXML
    private Label lb2;

    @FXML
    private Label lb3;

    String[] worldData;

    String totalCases;
    String totalDeaths;
    String newCase;
    String newDeaths;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            worldData = gd.getWorldData();
        } catch (Exception e) {
            System.out.println("URL Error");
        }

        newCase = String.format("%,d", Integer.parseInt(worldData[2]));
        newDeaths = String.format("%,d", Integer.parseInt(worldData[3]));
        totalCases = String.format("%,d", Integer.parseInt(worldData[4]));
        totalDeaths = String.format("%,d", Integer.parseInt(worldData[5]));

        System.out.println(totalCases);

        lb1.setText(totalCases);
        lb2.setText(newCase);
        lb3.setText(totalDeaths);
        lb4.setText(newDeaths);

    }
}
