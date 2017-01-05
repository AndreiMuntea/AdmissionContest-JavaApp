package GUI;

import Controller.CandidateController;
import Controller.OptionController;
import Controller.SectionController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by andrei on 2017-01-04.
 */
public class GUI {
    private Stage mainStage;
    private Parent mainScene;
    private FXMLLoader GUILoader;
    private GUIController GUIController;

    private CandidateController candidateController;
    private SectionController sectionController;
    private OptionController optionController;

    public GUI(Stage mainStage, CandidateController candidateController, SectionController sectionController, OptionController optionController) throws IOException {
        this.mainStage = mainStage;
        this.candidateController = candidateController;
        this.sectionController = sectionController;
        this.optionController = optionController;

        GUILoader = new FXMLLoader(getClass().getResource("/GUI/gui.fxml"));
        mainScene = GUILoader.load();
        GUIController = GUILoader.getController();
        GUIController.initialiseComponents();
    }

    public void start(){
        mainStage.setTitle("Application");
        mainStage.setScene(new Scene(mainScene, 800, 600));
        mainStage.setResizable(false);
        mainStage.show();
        mainStage.setOnCloseRequest(e -> Platform.exit());

    }
}
