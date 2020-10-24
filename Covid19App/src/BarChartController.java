import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The Controller class for BarChart.fxml.
 *
 * @author Bhatara Chaemchan SKE17
 */
public class BarChartController implements Initializable {

    //initialize FXML attributes.
    @FXML
    private Button mainMenu;

    @FXML
    private ComboBox<String> cb1;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private ComboBox<String> cb2;

    @FXML
    private ComboBox<String> showType;

    @FXML
    private Label lb1;

    @FXML
    private ComboBox<String> view;

    @FXML
    private Label lb2;

    @FXML
    private Label alert;

    //initialize class attributes.
    String[] nameOfCountry;
    String[] typesOfView = new String[]{"Total confirmed cases", "Total deaths", "New confirmed cases", "New deaths"};
    String[] typesOfChart = new String[]{"BarChart", "LineChart"};

    ArrayList<String> datee = new ArrayList<>();
    ArrayList<String> confirmCase = new ArrayList<>();

    String countryName = "World";
    String graphType = "Total confirmed cases";
    String casee;

    GraphData gd = new GraphData(); //Create Graphdata Object for get data.
    XYChart.Series series = new XYChart.Series();

    /**
     * Method for display a BarChart from FXML files.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //get data from GraphData class
        alert.setText("");
        try {
            nameOfCountry = gd.getCountry();
            datee = gd.getDate();
            confirmCase = gd.getCountryConfirmCase(graphType, countryName);
        } catch (Exception e) {
            alert.setText("Please select the area.");
        }

        mainMenu.setText("Main Menu");
        lb2.setText("last update: " + datee.get(datee.size() - 1));
        casee = confirmCase.get(confirmCase.size() - 1);

        //add all component to all ComboBox;
        ObservableList<String> ob1 = FXCollections.observableArrayList(nameOfCountry);
        ObservableList<String> ob2 = FXCollections.observableArrayList(datee);
        ObservableList<String> ob3 = FXCollections.observableArrayList(typesOfView);
        ObservableList<String> ob4 = FXCollections.observableArrayList(typesOfChart);

        cb1.getItems().addAll(ob1);
        cb2.getItems().addAll(ob2);
        showType.getItems().addAll(ob3);
        view.getItems().addAll(ob4);

        //set all data for Chart
        showType.setValue(graphType);
        series.setName("Covid19 confirm cases");
        lb1.setText(String.format("%s : %,d cases", graphType, Integer.parseInt(casee)));
        cb2.setValue(datee.get(datee.size() - 1));

        xAxis.setLabel("Year-Month-Date");
        yAxis.setLabel("Total confirmed cases");

        barChart.setTitle(countryName);
        barChart.getData().add(series);
        barChart.setAnimated(false);

        view.setValue("BarChart");

        drawBarChart();

        showType.setOnAction(new EventHandler<ActionEvent>() {
         /**
          * The anonymous class for set any component on window
          * and the data of chart.
          *
          * @param actionEvent action of showType ComboBox.
          */
         @Override
         public void handle(ActionEvent actionEvent) {
             try {
                 series.setName(showType.getValue());
                 yAxis.setLabel(showType.getValue());
                 graphType = showType.getValue();
                 series.getData().clear();
                 setAll();
             } catch (Exception e) {
                 alert.setText("Please select the area.");
             }
         }
     });

        cb1.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * The anonymous class for set any component on window
             * and the data of chart.
             * @param actionEvent action of cb1 ComboBox.
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                alert.setText("");
                series.getData().clear();
                countryName = cb1.getValue();
                setAll();
            }
        });

        cb2.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * The anonymous class for set a value of inflected case
             * @param actionEvent action of cb2 ComboBox.
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                for (int i = 0; i < confirmCase.size(); i++) {
                    if (cb2.getValue() == datee.get(i)) {
                        casee = confirmCase.get(i);
                    }
                    lb1.setText(String.format("%s : %,d cases", graphType, Integer.parseInt(casee)));
                }
            }
        });
    }

    /***
     * Method for draw a BarChart
     */
    private void drawBarChart() {
        for (int i = 1; i < datee.size(); i++) {
            XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(String.valueOf(datee.get(i)), Integer.parseInt(confirmCase.get(i)));
            series.getData().add(data);
        }
    }

    /**
     * Method that set all data for display.
     */
    public void setAll() {
        try {
            alert.setText("");
            barChart.setTitle(countryName);

            confirmCase = gd.getCountryConfirmCase(graphType, countryName);
            showType.setValue(graphType);

            cb2.setValue(datee.get(datee.size() - 1));
            casee = confirmCase.get(confirmCase.size() - 1);

            drawBarChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        lb1.setText(String.format("%s : %,d cases", graphType, Integer.parseInt(casee)));

    }

    /**
     * Method for switching Chart scene.
     *
     * @param event action of view ComboBox.
     * @throws IOException when FXMLLoader can't find .fxml file.
     */
    public void setScene(ActionEvent event) throws IOException {
        String fxml;
        fxml = view.getValue() + ".fxml";
        view.setValue(view.getValue());
        Parent chart = FXMLLoader.load(getClass().getResource("fxml/" + fxml));
        Scene charScene = new Scene(chart);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(charScene);
        window.setResizable(false);
        window.show();

    }

    /**
     * Method for MainMenu button.
     *
     * @param event action of MainMenu Button.
     * @throws IOException when FXMLLoader can't find the fxml file.
     */
    public void mainMenuHandler(ActionEvent event) throws IOException {
        Parent chart = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        Scene charScene = new Scene(chart);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(charScene);
        window.show();
    }


}