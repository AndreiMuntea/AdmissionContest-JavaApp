package GUI;

import Controller.CandidateController;
import Controller.OptionController;
import Controller.SectionController;
import GUI.CandidatesGUI.CandidatesGUIController;
import GUI.OptionsGUI.OptionGUIController;
import GUI.SectionsGUI.SectionsGUIController;
import GUI.UsersController.UserType;
import GUI.UsersController.UsersController;
import Helper.ConfigLoader.ConfigLoader;
import Utils.ObserverFramework.IObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by andrei on 2017-01-04.
 */
public class GUIController implements IObserver<UsersController>{

    @FXML
    private Button candidatesButton;

    @FXML
    private Button sectionsButton;

    @FXML
    private Button optionsButton;

    @FXML
    private Button LogIn;

    @FXML
    private VBox mainPane;

    @FXML
    private Image superUserImage;

    @FXML
    private Image userImage;

    @FXML
    private ImageView logInImage;

    private Parent sectionScene;
    private Parent candidateScene;
    private Parent optionScene;

    private FXMLLoader candidatesLoader;
    private FXMLLoader sectionsLoader;
    private FXMLLoader optionsLoader;

    private CandidatesGUIController candidatesGUIController;
    private SectionsGUIController sectionsGUIController;
    private OptionGUIController optionGUIController;


    private Stage usersStage;
    private Parent usersScene;
    private FXMLLoader usersLoader;
    private UsersController usersController;
    private UserType userType;

    private ConfigLoader config;

    public GUIController()
    {

    }

    public void initialiseComponents(CandidateController candidateController,
                                     SectionController sectionController,
                                     OptionController optionController,
                                     ConfigLoader config) throws Exception
    {
        Integer pageSize = config.getPageSize();

        candidatesLoader = new FXMLLoader(getClass().getResource("/GUI/CandidatesGUI/candidatesGUI.fxml"));
        candidateScene = candidatesLoader.load();
        candidatesGUIController = candidatesLoader.getController();
        candidatesGUIController.initComponents(candidateController, pageSize);
        candidateScene.getStylesheets().add(getClass().getResource("/GUI/CandidatesGUI/candidatesGUI.css").toString());


        sectionsLoader = new FXMLLoader(getClass().getResource("/GUI/SectionsGUI/sectionsGUI.fxml"));
        sectionScene = sectionsLoader.load();
        sectionsGUIController = sectionsLoader.getController();
        sectionsGUIController.initComponents(sectionController,pageSize);
        sectionScene.getStylesheets().add(getClass().getResource("/GUI/SectionsGUI/sectionsGUI.css").toString());


        optionsLoader = new FXMLLoader(getClass().getResource("/GUI/OptionsGUI/optionsGUI.fxml"));
        optionScene = optionsLoader.load();
        optionGUIController = optionsLoader.getController();
        optionGUIController.initComponents(optionController,pageSize);
        optionScene.getStylesheets().add(getClass().getResource("/GUI/OptionsGUI/optionsGUI.css").toString());

        userType = UserType.NORMAL_USER;
        usersStage = new Stage();
        usersStage.initModality(Modality.APPLICATION_MODAL);
        usersStage.setResizable(false);
        usersStage.setTitle("Logged in as Restricted User");
        usersLoader = new FXMLLoader(getClass().getResource("/GUI/users.fxml"));
        usersScene = usersLoader.load();
        usersController = usersLoader.getController();
        usersStage.centerOnScreen();
        usersStage.setScene(new Scene(usersScene, 300,300));
        usersController.initComponents(userType,usersStage, config);
        usersController.addObserver(this);

        setImage();
        updateRestrictions();

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

    public void handleLogInButton()
    {
        usersStage.show();
    }

    private void setImage()
    {
        if(userType == UserType.SUPER_USER)
            logInImage.setImage(superUserImage);
        if(userType == UserType.NORMAL_USER)
            logInImage.setImage(userImage);
    }

    private void updateRestrictions()
    {
        candidatesGUIController.setRestrictions(userType);
        optionGUIController.setRestrictions(userType);
        sectionsGUIController.setRestrictions(userType);
    }

    @Override
    public void update() {
        userType = usersController.getUserType();

        String title = "";

        if (userType == UserType.NORMAL_USER){
            title = "Restricted user";
        }
        if (userType == UserType.SUPER_USER){
            title = "Super user";
        }

        usersStage.setTitle("Logged in as " + title);
        setImage();
        updateRestrictions();
    }
}
