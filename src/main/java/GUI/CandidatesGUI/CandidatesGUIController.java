package GUI.CandidatesGUI;

import Controller.CandidateController;
import Domain.Candidate;
import Utils.Exceptions.MyException;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * Created by andrei on 2017-01-04.
 */
public class CandidatesGUIController {

    @FXML
    private TableView<Candidate> candidatesTable;

    @FXML
    private TableColumn<Candidate, String> candidateName;

    @FXML
    private TableColumn<Candidate, Double> candidateGrade;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField gradeTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField filterByNameTextField;

    @FXML
    private TextField filterByGradeTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button exportButton;

    @FXML
    private ComboBox<String> saveOptionComboBox;


    private CandidateController candidateController;
    private int pageSize;
    private ObservableList<Candidate> model;

    public CandidatesGUIController() {

    }

    public void initComponents(CandidateController candidateController, int pageSize) throws MyException {
        this.candidateController = candidateController;
        this.pageSize = pageSize;

        candidateName.setCellValueFactory(new PropertyValueFactory<>("name"));
        candidateGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        updateModel();
    }

    public void loadCandidate() {
        Candidate candidate = candidatesTable.getSelectionModel().getSelectedItem();
        if (candidate == null) return;

        IDTextField.setText(candidate.getID().toString());
        nameTextField.setText(candidate.getName());
        addressTextField.setText(candidate.getAddress());
        gradeTextField.setText(String.format("%.2f", candidate.getGrade()));
        phoneNumberTextField.setText(candidate.getPhoneNumber());
    }

    public void addButtonHandler() {
        try {
            candidateController.Add(getCandidateFields());
            updateModel();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void updateButtonHandler(){
        try {
            candidateController.Update(getCandidateFields());
            updateModel();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteButtonHandler(){
        try {
            candidateController.Remove(getCandidateID());
            updateModel();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    private void updateModel() throws MyException {
        model = new SimpleListProperty<>(FXCollections.observableArrayList(candidateController.GetPage(0)));
        candidatesTable.setItems(model);
    }

    private String[] getCandidateFields()
    {
        String candidate[] = new String[5];
        candidate[0] = IDTextField.getText();
        candidate[1] = nameTextField.getText();
        candidate[2] = addressTextField.getText();
        candidate[3] = gradeTextField.getText();
        candidate[4] = phoneNumberTextField.getText();

        return candidate;
    }

    private String[] getCandidateID()
    {
        String candidateID[] = new String[1];
        candidateID[0] = IDTextField.getText();
        return candidateID;
    }

}
