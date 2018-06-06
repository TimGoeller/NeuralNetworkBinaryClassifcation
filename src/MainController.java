import NeuralNetwork.Dataset;
import NeuralNetwork.NeuralNetwork;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TextField epochsTestField;

    @FXML
    private ListView hiddenLayerListView;

    @FXML
    private TitledPane testingPane;

    @FXML
    private Button datasetTestingButton;

    @FXML
    private Label successPercentageLabel;

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
    protected void validateEpochsIntInput() {

        String currentInput = epochsTestField.getText();
        try{
            Integer.parseInt(currentInput);
        }
        catch (Exception e) {
            epochsTestField.setText("");
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

    @FXML
    protected void trainNetwork() {

        Dataset dataset = NetworkUi.currentUI.getDataset();

        if(dataset == null) {
            showTrainingErrorPopup("No dataset was initialized!");
        }
        else if(learningRateTextField.getText().equals("")) {
            showTrainingErrorPopup("No learning rate was specified!");
        }
        else if(epochsTestField.getText().equals("")) {
            showTrainingErrorPopup("No epochs were specified!");
        }
        else {
            double learningRate = Double.parseDouble((learningRateTextField.getText()));
            NeuralNetwork network = new NeuralNetwork(dataset.getInputColumnCount(), dataset, learningRate);
            NetworkUi.currentUI.setNetwork(network);

            for(int neuronCount : hiddenLayerNeuronCounts) {
                network.addHiddenLayer(neuronCount);
            }
            network.trainNetwork(Integer.parseInt(epochsTestField.getText()));
            testingPane.setDisable(false);
            datasetTestingButton.setText("Train with " + dataset.getTestSet().size() + " items");

        }

        //else if()

        //NeuralNetwork network = new NeuralNetwork();
    }

    @FXML
    protected void testNetwork() {

        //System.out.println(NetworkUi.currentUI);
        //System.out.println(NetworkUi.currentUI.getNetwork());
        NetworkUi.currentUI.getNetwork().testNetwork();

        successPercentageLabel.setText("Success percentage: " + NetworkUi.currentUI.getNetwork().getLastTestSuccessRatio() * 100);
    }

    private void showTrainingErrorPopup(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Training error");
        alert.setContentText(error);
        alert.showAndWait();
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
