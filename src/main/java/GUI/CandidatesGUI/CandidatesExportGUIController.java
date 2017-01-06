package GUI.CandidatesGUI;

import Controller.CandidateController;
import Utils.Exceptions.MyException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by andrei on 2017-01-05.
 */
public class CandidatesExportGUIController {

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


    private CandidateController candidateController;
    private Stage mainStage;


    public CandidatesExportGUIController() {

    }

    public void initialiseComponents(CandidateController candidateController, Stage mainStage) {
        this.candidateController = candidateController;
        this.mainStage = mainStage;
    }

    public void setDetails(String directoryPath, String exportOption) {
        directoryPathTextField.setText(directoryPath);
        exportTextField.setText(exportOption);
    }

    public void proceedButtonHandler() {
        String option = exportTextField.getText();
        String path = directoryPathTextField.getText();
        String fileName = fileNameTextField.getText();
        try {
            candidateController.Export(path,fileName,option);
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
