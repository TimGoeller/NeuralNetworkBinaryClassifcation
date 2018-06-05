import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;


public class NetworkUi extends Application
{
    public static void main( String[] args )
    {
        launch( args );
        /*
        Dataset d = Dataset.readDatasetFromCSV("Admission_Dataset_Getrennt.csv");
        NeuralNetwork network = new NeuralNetwork(2, d, 0.05);

        network.addHiddenLayer(4);
        network.addHiddenLayer(5);
        network.addHiddenLayer(4);

        network.initializeLayers();
        network.trainNetwork(4000);

        network.testNetwork();
        */
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
