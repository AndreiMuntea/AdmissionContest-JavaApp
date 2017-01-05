package GUI;

import GUI.CandidatesGUI.CandidatesGUIController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by andrei on 2017-01-04.
 */
public class GUIController {

    @FXML
    private Button candidatesButton;

    @FXML
    private Button sectionsButton;

    @FXML
    private Button optionsButton;

    @FXML
    private VBox mainPane;

    private Parent sectionScene;
    private Parent candidateScene;
    private Parent optionScene;

    private FXMLLoader candidatesLoader;
    private FXMLLoader sectionsLoader;
    private FXMLLoader optionsLoader;

    private CandidatesGUIController candidatesGUIController;


    public GUIController()
    {

    }

    public void initialiseComponents() throws IOException
    {
        candidatesLoader = new FXMLLoader(getClass().getResource("/GUI/CandidatesGUI/candidatesGUI.fxml"));
        candidateScene = candidatesLoader.load();
        candidatesGUIController = candidatesLoader.getController();

        candidatesButton.setDisable(true);
        sectionsButton.setDisable(false);
        optionsButton.setDisable(false);

        mainPane.getChildren().clear();
        mainPane.getChildren().add(candidateScene);
    }

    public void handleCandidatesButton()
    {
        candidatesButton.setDisable(true);
        sectionsButton.setDisable(false);
        optionsButton.setDisable(false);

        mainPane.getChildren().clear();
        mainPane.getChildren().add(candidateScene);
    }


    public void handleSectionsButton()
    {
        candidatesButton.setDisable(false);
        sectionsButton.setDisable(true);
        optionsButton.setDisable(false);

        mainPane.getChildren().clear();
        //mainPane.getChildren().add(sectionScene);
    }

    public void handleOptionsButton()
    {
        candidatesButton.setDisable(false);
        sectionsButton.setDisable(false);
        optionsButton.setDisable(true);

        mainPane.getChildren().clear();
        //mainPane.getChildren().add(optionScene);
    }
}
