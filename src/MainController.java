import NeuralNetwork.Dataset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private TabPane rootNode;

    @FXML
    private Label filePathLabel;

    @FXML
    private TextField learningRateTextField;

    @FXML
    private TextField neuronCountTextField;

    @FXML
    private ListView hiddenLayerListView;

    public Stage stage;
    protected List<Integer> hiddenLayerNeuronCounts= new ArrayList<Integer>();

    @FXML
    protected void openCSVPopup() {
        System.out.println("OPEN");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV file");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.txt)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(rootNode.getScene().getWindow());
        if (file != null) {
            filePathLabel.setTextFill(Color.web("#000000"));
            filePathLabel.setText(file.getPath());
        }
        else {
            filePathLabel.setTextFill(Color.web("#000000"));
            filePathLabel.setText("No dataset loaded");
        }

        try{
            NetworkUi.currentUI.setDataset(Dataset.readDatasetFromCSV(file.getPath()));
        }
        catch (Exception e) {
            filePathLabel.setTextFill(Color.web("#ff6347"));
            filePathLabel.setText("Error");
        }

    }

    @FXML
    protected void validateLearningRateNumberInput() {

        String currentInput = learningRateTextField.getText();
        try{
            Double.parseDouble(currentInput);
        }
        catch (Exception e) {
            learningRateTextField.setText("");
        }

    }

    @FXML
    protected void validateNeuronCountNumberInput() {

        String currentInput = neuronCountTextField.getText();
        try{
            Integer.parseInt(currentInput);
        }
        catch (Exception e) {
            neuronCountTextField.setText("");
        }

    }

    @FXML
    protected void addHiddenLayerToList() {

        if(neuronCountTextField.getText() != "") {
            hiddenLayerNeuronCounts.add(Integer.parseInt(neuronCountTextField.getText()));
        }


        updateHiddenLayerList();
    }

    @FXML
    protected void removeHiddenLayerFromList() {

        int selectedIndex = hiddenLayerListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            hiddenLayerNeuronCounts.remove(selectedIndex);
            updateHiddenLayerList();
        }
    }

    private void updateHiddenLayerList() {

        ObservableList<String> hiddenLayerNeuronCountStringList =  FXCollections.observableList(new ArrayList<String>());

        int currentLayer = 0;
        for(int neuronCount : hiddenLayerNeuronCounts) {
            hiddenLayerNeuronCountStringList.add("Hidden Layer #" + currentLayer + " - " + neuronCount + " Neurons");
            currentLayer++;
        }

        hiddenLayerListView.setItems(hiddenLayerNeuronCountStringList);
    }
}
