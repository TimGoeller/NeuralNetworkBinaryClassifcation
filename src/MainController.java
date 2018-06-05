import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainController {

    @FXML
    private TabPane rootNode;

    @FXML
    private Label filePathLabel;

    public Stage stage;

    @FXML
    protected void openCSVPopup() {
        System.out.println("OPEN");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV file");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.txt)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(rootNode.getScene().getWindow());
        if (file != null) {
            filePathLabel.setText(file.getPath());
        }


    }

}
