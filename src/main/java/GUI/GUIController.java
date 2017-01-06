package GUI;

import Controller.CandidateController;
import Controller.OptionController;
import Controller.SectionController;
import GUI.CandidatesGUI.CandidatesGUIController;
import GUI.OptionsGUI.OptionGUIController;
import GUI.SectionsGUI.SectionsGUIController;
import Utils.Exceptions.MyException;
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
    private SectionsGUIController sectionsGUIController;
    private OptionGUIController optionGUIController;


    public GUIController()
    {

    }

    public void initialiseComponents(CandidateController candidateController,
                                     SectionController sectionController,
                                     OptionController optionController,
                                     int pageSize) throws Exception
    {
        candidatesLoader = new FXMLLoader(getClass().getResource("/GUI/CandidatesGUI/candidatesGUI.fxml"));
        candidateScene = candidatesLoader.load();
        candidatesGUIController = candidatesLoader.getController();
        candidatesGUIController.initComponents(candidateController, pageSize);

        sectionsLoader = new FXMLLoader(getClass().getResource("/GUI/SectionsGUI/sectionsGUI.fxml"));
        sectionScene = sectionsLoader.load();
        sectionsGUIController = sectionsLoader.getController();
        sectionsGUIController.initComponents(sectionController,pageSize);

        optionsLoader = new FXMLLoader(getClass().getResource("/GUI/OptionsGUI/optionsGUI.fxml"));
        optionScene = optionsLoader.load();
        optionGUIController = optionsLoader.getController();
        optionGUIController.initComponents(optionController,pageSize);

        candidatesButton.setDisable(true);
        sectionsButton.setDisable(false);
        optionsButton.setDisable(false);

        candidateController.addObserver(candidatesGUIController);
        candidateController.addObserver(optionGUIController);

        sectionController.addObserver(sectionsGUIController);
        sectionController.addObserver(optionGUIController);

        optionController.addObserver(optionGUIController);

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
        mainPane.getChildren().add(sectionScene);
    }

    public void handleOptionsButton()
    {
        candidatesButton.setDisable(false);
        sectionsButton.setDisable(false);
        optionsButton.setDisable(true);

        mainPane.getChildren().clear();
        mainPane.getChildren().add(optionScene);
    }
}
