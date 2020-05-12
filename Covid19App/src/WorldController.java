import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Controller class for World.fxml.
 *
 * @author Bhatara Chaemchan SKE17
 */
public class WorldController implements Initializable {

    //initialize FXML attributes.
    @FXML
    private Button mainMenu;

    @FXML
    private Label lb1;

    @FXML
    private Label lb4;

    @FXML
    private Label lb2;

    @FXML
    private Label lb3;

    @FXML
    private Text text;

    @FXML
    private ComboBox<String> cb1;

    @FXML
    private Text date;

    @FXML
    private Text t1;

    @FXML
    private Text t4;

    @FXML
    private Text t3;

    @FXML
    private Text t2;

    //initialize class attributes.
    String[] worldDataToday;
    String[] getWorldDataYesterday;

    String totalCases;
    String totalDeaths;
    String newCase;
    String newDeaths;
    String today;
    String yesterday;
    String diff1;
    String diff2;
    String diff3;
    String diff4;

    double percent1 = 0;
    double percent2 = 0;
    double percent3 = 0;
    double percent4 = 0;

    int indexOfTotalCaseIndex = 4;
    int indexOfNewCase = 2;
    int indexOfNewDeath = 3;
    int indexOfTotalDeath = 5;

    double divide1;
    double divide2;
    double divide3;
    double divide4;

    GraphData gd = new GraphData();

    /**
     * Method for display a WorldSummary window.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cb1.getItems().addAll(gd.getCountry());
            today = gd.getDate().get(gd.getDate().size() - 1);
            yesterday = gd.getDate().get(gd.getDate().size() - 2);
            worldDataToday = gd.getWorldData("World", today);
            getWorldDataYesterday = gd.getWorldData("World", yesterday);
            cb1.getItems().remove("International");
            setAll();
        } catch (Exception e) {
            noUpdateAlert();
        }
    }

    /**
     * Method for convert String to Integer.
     * @param s String for convert.
     * @return val the Integer value of String.
     */
    public int convertInt(String s) {
        int val = Integer.parseInt(s);
        return val;
    }

    /**
     * Method for convert String to Integer.
     * @param s String for convert.
     * @return val the double value of String.
     */
    public double convertDouble(String s) {
        double val = Double.parseDouble(s);
        return val;
    }

    /**
     * Method for set the new value for display in World.fxml.
     * @throws Exception when URL not found.
     */
    public void cb1Handler() throws Exception {
        try {
            GraphData gd = new GraphData();
            worldDataToday = gd.getWorldData(cb1.getValue(), today);
            getWorldDataYesterday = gd.getWorldData(cb1.getValue(), yesterday);
            newCase = String.format("%,d", convertInt(worldDataToday[2]));
            newDeaths = String.format("%,d", convertInt(worldDataToday[3]));
            totalCases = String.format("%,d", convertInt(worldDataToday[4]));
            totalDeaths = String.format("%,d", convertInt(worldDataToday[5]));

            setAll();
            text.setText(cb1.getValue());
        }catch (Exception e){
            noUpdateAlert();
        }
        text.setStyle("-fx-font-size: 50");
        if (cb1.getValue().length() > 18) {
            text.setStyle("-fx-font-size: 35");
        }

    }

    /**
     * Method for MainMenu button.
     * @param event action of MainMenu button.
     * @throws IOException when fxml file not found.
     */
    @FXML
    public void mainMenuHandler(ActionEvent event) throws IOException {
        Parent chart = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        Scene charScene = new Scene(chart);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(charScene);
        window.setResizable(false);
        window.show();
    }

    /**
     * Method that set all data for display onscreen.
     */
    public void setAll(){

        //set percentage fill color.
        t1.setStyle("-fx-fill: forestgreen");
        t2.setStyle("-fx-fill: forestgreen");
        t3.setStyle("-fx-fill: forestgreen");
        t4.setStyle("-fx-fill: forestgreen");

        // formatted String of the values for display.
        newCase = String.format("%,d", Integer.parseInt(worldDataToday[2]));
        newDeaths = String.format("%,d", Integer.parseInt(worldDataToday[3]));
        totalCases = String.format("%,d", Integer.parseInt(worldDataToday[4]));
        totalDeaths = String.format("%,d", Integer.parseInt(worldDataToday[5]));

        // set a value of divide attributes.
        divide1 = convertDouble(getWorldDataYesterday[indexOfTotalCaseIndex]);
        divide2 = convertDouble(getWorldDataYesterday[indexOfNewCase]);
        divide3 = convertDouble(getWorldDataYesterday[indexOfNewDeath]);
        divide4 = convertDouble(getWorldDataYesterday[indexOfTotalDeath]);

        //fix a value of divide attributes when it equals zero.
        if (divide1 == 0) divide1 = 1;
        if (divide2 == 0) divide2 = 1;
        if (divide3 == 0) divide3 = 1;
        if (divide4 == 0) divide4 = 1;

        //calculate a percentage different.
        percent1 = (convertDouble(worldDataToday[indexOfTotalCaseIndex]) - convertDouble(getWorldDataYesterday[indexOfTotalCaseIndex])) * 100 / divide1;
        percent2 = (convertDouble(worldDataToday[indexOfNewCase]) - convertDouble(getWorldDataYesterday[indexOfNewCase])) * 100 / divide2;
        percent3 = (convertDouble(worldDataToday[indexOfNewDeath]) - convertDouble(getWorldDataYesterday[indexOfNewDeath])) * 100 / divide3;
        percent4 = (convertDouble(worldDataToday[indexOfTotalDeath]) - convertDouble(getWorldDataYesterday[indexOfTotalDeath])) * 100 / divide4;

        // formatted String of the percentage different value for display.
        diff1 = String.format("( %.2f", percent1);
        diff2 = String.format("( %.2f", percent2);
        diff3 = String.format("( %.2f", percent3);
        diff4 = String.format("( %.2f", percent4);

        if (percent1 > 0) {
            diff1 = String.format("( +%.2f", percent1);
            t1.setStyle("-fx-fill: red");
        }
        if (percent2 > 0) {
            diff2 = String.format("( +%.2f", percent2);
            t2.setStyle("-fx-fill: red");
        }
        if (percent3 > 0) {
            diff3 = String.format("( +%.2f", percent3);
            t3.setStyle("-fx-fill: red");
        }
        if (percent4 > 0) {
            diff4 = String.format("( +%.2f", percent4);
            t4.setStyle("-fx-fill: red");
        }

        //set text for display.
        lb1.setText(totalCases);
        lb2.setText(newCase);
        lb3.setText(totalDeaths);
        lb4.setText(newDeaths);
        text.setText("World");
        date.setText("Date: " + worldDataToday[0]);
        t1.setText(diff1 + "% )");
        t2.setText(diff2 + "% )");
        t3.setText(diff3 + "% )");
        t4.setText(diff4 + "% )");
    }

    /**
     * Method for set Alert massage when we don't have a today data for display
     */
    public void noUpdateAlert() {
        date.setText("No update data for today.");
        text.setText(cb1.getValue());
        lb1.setText("-");
        lb2.setText("-");
        lb3.setText("-");
        lb4.setText("-");
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
    }
}
