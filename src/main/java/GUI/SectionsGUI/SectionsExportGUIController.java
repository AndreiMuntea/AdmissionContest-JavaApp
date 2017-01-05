package GUI.SectionsGUI;

import Controller.SectionController;
import Utils.Exceptions.MyException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by andrei on 2017-01-05.
 */
public class SectionsExportGUIController {

    @FXML
    private TextField directoryPathTextField;

    @FXML
    private TextField fileNameTextField;

    @FXML
    private TextField exportTextField;

    @FXML
    private Button proceedButton;

    @FXML
    private Button cancelButton;


    private SectionController sectionController;
    private Stage mainStage;


    public SectionsExportGUIController() {

    }

    public void initialiseComponents(SectionController sectionController, Stage mainStage) {
        this.sectionController = sectionController;
        this.mainStage = mainStage;
    }

    public void setDetails(String directoryPath, String exportOption) {
        directoryPathTextField.setText(directoryPath);
        exportTextField.setText(exportOption);
    }

    public void proceedButtonHandler() {
        String path = directoryPathTextField.getText();
        String fileName = fileNameTextField.getText();
        String option = exportTextField.getText();
        try {
            if (option.equals("CSV")) sectionController.ExportAsCSV(path, fileName);
            else {
                if (option.equals("TXT")) sectionController.ExportAsTXT(path, fileName);
            }
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        closeWindow();
    }

    public void cancelButtonHandler() {
        closeWindow();
    }


    private void closeWindow() {
        directoryPathTextField.clear();
        fileNameTextField.clear();
        exportTextField.clear();
        this.mainStage.close();
    }
}
