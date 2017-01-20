package GUI.CandidatesGUI;

import Controller.CandidateController;
import Domain.Candidate;
import GUI.UsersController.UserType;
import Utils.Exceptions.MyException;
import Utils.ObserverFramework.IObserver;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;


/**
 * Created by andrei on 2017-01-04.
 */
public class CandidatesGUIController implements IObserver<Candidate> {

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
    private TextField pageNumberTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button warningButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button previousPageButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private ComboBox<String> saveOptionComboBox;

    @FXML
    private ComboBox<String> filterByNameComboBox;

    @FXML
    private ComboBox<String> filterByGradeComboBox;


    private CandidateController candidateController;
    private Integer pageSize;
    private Integer currentPage;
    private ObservableList<Candidate> model;

    private CandidatesExportGUIController candidatesExportGUIController;
    private FXMLLoader candidatesExportGUILoader;
    private Stage candidatesExportStage;
    private Parent candidatesExportScene;

    public CandidatesGUIController() {

    }

    public void initComponents(CandidateController candidateController, Integer pageSize) throws Exception {
        this.candidateController = candidateController;
        this.pageSize = pageSize;
        this.currentPage = 0;

        candidateName.setCellValueFactory(new PropertyValueFactory<>("name"));
        candidateGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        candidatesExportStage = new Stage();
        candidatesExportStage.initModality(Modality.APPLICATION_MODAL);
        candidatesExportStage.setResizable(false);
        candidatesExportStage.setTitle("export");
        candidatesExportGUILoader = new FXMLLoader(getClass().getResource("/GUI/CandidatesGUI/exportCandidatesGUI.fxml"));
        candidatesExportScene = candidatesExportGUILoader.load();
        candidatesExportGUIController = candidatesExportGUILoader.getController();
        candidatesExportStage.setScene(new Scene(candidatesExportScene, 600, 230));
        candidatesExportGUIController.initialiseComponents(this.candidateController, candidatesExportStage);

        filterByNameTextField.textProperty().addListener((v, oldValue, newValue) -> updateModel());
        filterByNameComboBox.valueProperty().addListener((v, oldValue, newValue) -> updateModel());

        filterByGradeTextField.textProperty().addListener((v, oldValue, newValue) -> updateModel());
        filterByGradeComboBox.valueProperty().addListener((v, oldValue, newValue) -> updateModel());

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

    public HashMap<String, String> loadFilters() {
        HashMap<String, String> filters = new HashMap<>();

        loadNameFilter(filters);
        loadGradeFilter(filters);

        return filters;
    }

    public void addButtonHandler() {
        try {
            candidateController.Add(getCandidateFields());
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void updateButtonHandler() {
        try {
            candidateController.Update(getCandidateFields());
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteButtonHandler() {
        try {
            candidateController.Remove(getCandidateID());
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void warningButtonHandler()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Some features might not be available!\nPlease log in as super user for full access!");
        alert.showAndWait();
    }

    public void exportButtonHandler() {
        DirectoryChooser d = new DirectoryChooser();
        try {
            String directoryPath = d.showDialog(candidatesExportStage).getPath();
            String exportOption = saveOptionComboBox.getValue();
            candidatesExportGUIController.setDetails(directoryPath, exportOption);
            candidatesExportStage.show();
        } catch (Exception ignored) {
        }
    }

    public void pageChangedHandler() {
        Integer newPage;
        try {
            newPage = Integer.parseInt(pageNumberTextField.getText());
            if (newPage < 0) pageNumberTextField.setText(currentPage.toString());
            else {
                currentPage = newPage;
                updateModel();
            }
        } catch (NumberFormatException e) {
            pageNumberTextField.setText(currentPage.toString());
        }
    }

    public void previousPageButtonHandler() {
        if (currentPage == 0) return;
        pageNumberTextField.setText((--currentPage).toString());
        updateModel();
    }

    public void nextPageButtonHandler() {
        pageNumberTextField.setText((++currentPage).toString());
        updateModel();
    }

    private void updateModel() {
        try {
            HashMap<String, String> filters = loadFilters();
            model = new SimpleListProperty<>(FXCollections.observableArrayList(candidateController.ApplyFilters(pageSize, currentPage, filters)));
            candidatesTable.setItems(model);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    private String[] getCandidateFields() {
        String candidate[] = new String[5];
        candidate[0] = IDTextField.getText();
        candidate[1] = nameTextField.getText();
        candidate[2] = addressTextField.getText();
        candidate[3] = gradeTextField.getText();
        candidate[4] = phoneNumberTextField.getText();

        return candidate;
    }

    private String[] getCandidateID() {
        String candidateID[] = new String[1];
        candidateID[0] = IDTextField.getText();
        return candidateID;
    }

    private void loadNameFilter(HashMap<String, String> allFilters) {
        if (filterByNameComboBox.getValue().equals("No filter"))
            return;
        if (filterByNameTextField.getText().isEmpty())
            return;
        allFilters.put(filterByNameComboBox.getValue(), filterByNameTextField.getText());
    }

    private void loadGradeFilter(HashMap<String, String> allFilters) {
        filterByGradeTextField.getStyleClass().remove("error");
        filterByGradeTextField.setTooltip(null);

        if (filterByGradeComboBox.getValue().equals("No filter"))
            return;
        if(filterByGradeTextField.getText().equals(""))
            return;
        try{
            Double.parseDouble(filterByGradeTextField.getText());
            allFilters.put(filterByGradeComboBox.getValue(), filterByGradeTextField.getText());

        }catch(NumberFormatException e)
        {
            filterByGradeTextField.setTooltip(new Tooltip("Grade should be a valid number!"));
            filterByGradeTextField.getStyleClass().add("error");
        }
    }

    public void setRestrictions(UserType userType)
    {
        addButton.setDisable(userType == UserType.NORMAL_USER);
        updateButton.setDisable(userType == UserType.NORMAL_USER);
        deleteButton.setDisable(userType == UserType.NORMAL_USER);
        warningButton.setVisible(userType == UserType.NORMAL_USER);
    }


    @Override
    public void update() {
        updateModel();
    }
}
