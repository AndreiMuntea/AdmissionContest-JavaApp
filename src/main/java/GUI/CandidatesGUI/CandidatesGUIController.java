package GUI.CandidatesGUI;

import Domain.Candidate;
import javafx.fxml.FXML;
import javafx.scene.control.*;


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
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    public CandidatesGUIController() {

    }

    public void initComponents()
    {
    }
}
