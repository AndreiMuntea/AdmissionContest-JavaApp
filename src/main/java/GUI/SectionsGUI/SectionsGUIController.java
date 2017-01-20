package GUI.SectionsGUI;

import Controller.SectionController;
import Domain.Section;
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
 * Created by andrei on 2017-01-05.
 */
public class SectionsGUIController implements IObserver<Section> {

    @FXML
    private TableView<Section> sectionsTable;

    @FXML
    private TableColumn<Section, String> nameColumn;

    @FXML
    private TableColumn<Section, Integer> slotsColumn;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField availableSlotsTextField;

    @FXML
    private TextField filterByNameTextField;

    @FXML
    private TextField filterByAvailableSlotsTextField;

    @FXML
    private TextField pageNumberTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button previousPageButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button warningButton;

    @FXML
    private ComboBox<String> optionsComboBox;

    @FXML
    private ComboBox<String> filterByNameComboBox;

    @FXML
    private ComboBox<String> filterBySlotsComboBox;

    private SectionController sectionController;
    private Integer pageSize;
    private Integer currentPage;
    private ObservableList<Section> model;

    private SectionsExportGUIController sectionsExportGUIController;
    private FXMLLoader sectionsExportGUILoader;
    private Stage sectionsExportStage;
    private Parent sectionsExportScene;


    public SectionsGUIController() {
    }

    public void initComponents(SectionController sectionController, Integer pageSize) throws Exception {
        this.sectionController = sectionController;
        this.pageSize = pageSize;
        this.currentPage = 0;

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        slotsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSlots"));

        sectionsExportStage = new Stage();
        sectionsExportStage.initModality(Modality.APPLICATION_MODAL);
        sectionsExportStage.setResizable(false);
        sectionsExportStage.setTitle("export");
        sectionsExportGUILoader = new FXMLLoader(getClass().getResource("/GUI/sectionsGUI/exportSectionsGUI.fxml"));
        sectionsExportScene = sectionsExportGUILoader.load();
        sectionsExportGUIController = sectionsExportGUILoader.getController();
        sectionsExportStage.setScene(new Scene(sectionsExportScene, 600, 230));
        sectionsExportGUIController.initialiseComponents(this.sectionController, sectionsExportStage);

        filterByNameComboBox.valueProperty().addListener((v,oldValue,newValue)->updateModel());
        filterByNameTextField.textProperty().addListener((v,oldValue,newValue)->updateModel());

        filterByAvailableSlotsTextField.textProperty().addListener((v,oldValue,newValue)->updateModel());
        filterBySlotsComboBox.valueProperty().addListener((v,oldValue,newValue)->updateModel());

        updateModel();
    }

    private void updateModel() {
        try {
            HashMap<String, String> filters = loadFilters();
            model = new SimpleListProperty<>(FXCollections.observableArrayList(sectionController.ApplyFilters(pageSize, currentPage, filters)));
            sectionsTable.setItems(model);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public void loadSection() {
        Section section = sectionsTable.getSelectionModel().getSelectedItem();
        if (section == null) return;

        IDTextField.setText(section.getID().toString());
        nameTextField.setText(section.getName());
        availableSlotsTextField.setText(section.getAvailableSlots().toString());
    }

    public void addButtonHandler() {
        try {
            sectionController.Add(getSectionsFields());
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void updateButtonHandler() {
        try {
            sectionController.Update(getSectionsFields());
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteButtonHandler() {
        try {
            sectionController.Remove(getSectionID());
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    public void exportButtonHandler() {
        DirectoryChooser d = new DirectoryChooser();
        try {
            String directoryPath = d.showDialog(sectionsExportStage).getPath();
            String exportOption = optionsComboBox.getValue();
            sectionsExportGUIController.setDetails(directoryPath, exportOption);
            sectionsExportStage.show();
        }catch (Exception ignored){}
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


    private String[] getSectionsFields() {
        String section[] = new String[3];
        section[0] = IDTextField.getText();
        section[1] = nameTextField.getText();
        section[2] = availableSlotsTextField.getText();
        return section;
    }

    private String[] getSectionID() {
        String sectionID[] = new String[1];
        sectionID[0] = IDTextField.getText();
        return sectionID;
    }

    public HashMap<String, String> loadFilters() {
        HashMap<String, String> filters = new HashMap<>();

        loadNameFilter(filters);
        loadSlotsFilter(filters);

        return filters;
    }

    private void loadNameFilter(HashMap<String, String> allFilters) {
        if (filterByNameComboBox.getValue().equals("No filter"))
            return;
        if (filterByNameTextField.getText().isEmpty())
            return;
        allFilters.put(filterByNameComboBox.getValue(), filterByNameTextField.getText());
    }

    private void loadSlotsFilter(HashMap<String, String> allFilters) {
        filterByAvailableSlotsTextField.getStyleClass().remove("error");
        filterByAvailableSlotsTextField.setTooltip(null);

        if (filterBySlotsComboBox.getValue().equals("No filter"))
            return;
        if(filterByAvailableSlotsTextField.getText().equals(""))
            return;
        try{
            Integer.parseInt(filterByAvailableSlotsTextField.getText());
            allFilters.put(filterBySlotsComboBox.getValue(), filterByAvailableSlotsTextField.getText());

        }catch(NumberFormatException e)
        {
            filterByAvailableSlotsTextField.setTooltip(new Tooltip("Argument should be a valid number!"));
            filterByAvailableSlotsTextField.getStyleClass().add("error");
        }
    }

    public void setRestrictions(UserType userType)
    {
        addButton.setDisable(userType == UserType.NORMAL_USER);
        updateButton.setDisable(userType == UserType.NORMAL_USER);
        deleteButton.setDisable(userType == UserType.NORMAL_USER);
        warningButton.setVisible(userType == UserType.NORMAL_USER);
    }

    public void warningButtonHandler()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Some features might not be available!\nPlease log in as super user for full access!");
        alert.showAndWait();
    }

    @Override
    public void update() {
        updateModel();
    }
}
