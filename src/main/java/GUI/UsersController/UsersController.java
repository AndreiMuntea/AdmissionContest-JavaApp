package GUI.UsersController;

import Helper.ConfigLoader.ConfigLoader;
import Utils.ObserverFramework.AbstractObservable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by andrei on 2017-01-20.
 */
public class UsersController extends AbstractObservable<UsersController> {
    @FXML
    Image superUserImage;

    @FXML
    Image restrictedUserImage;

    @FXML
    ImageView userImage;

    @FXML
    TextField userNameTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    ImageView logButtonImageView;

    @FXML
    Image logInImage;

    @FXML
    Image logOutImage;

    @FXML
    Button logButton;

    @FXML
    Label userLabel;

    @FXML
    Label passLabel;

    private UserType userType;
    private Stage mainStage;
    private ConfigLoader config;

    private String userName;

    public UsersController() {
    }

    public void initComponents(UserType userType, Stage mainStage, ConfigLoader config) {
        this.userType = userType;
        this.mainStage = mainStage;
        this.config = config;
        this.userName = "";

        this.mainStage.setOnCloseRequest(e->{clearData();});

        setImage();
    }

    public void handleButton() {
        if (userType == UserType.SUPER_USER) {
            userType = UserType.NORMAL_USER;
            update();
        } else {
            String user = userNameTextField.getText();
            String pass = passwordTextField.getText();
            if(!config.exists(user,pass))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid User/Password combination!");
                alert.showAndWait();
            }
            else {
                this.userName = user;
                userType = UserType.SUPER_USER;
                update();
            }
        }
    }

    public UserType getUserType() {
        return this.userType;
    }

    private void update() {
        clearData();
        setImage();
        notifyObservers();
        mainStage.hide();
    }

    private void setImage() {
        if (userType == UserType.NORMAL_USER) {
            userImage.setImage(restrictedUserImage);
            logButtonImageView.setImage(logInImage);
            logButton.setTooltip(null);
            passwordTextField.setVisible(true);
            passLabel.setVisible(true);
            userLabel.setText("Username");
            userNameTextField.setEditable(true);
            logButton.setTooltip(new Tooltip("Log in as Super User"));
        }
        if (userType == UserType.SUPER_USER) {
            passwordTextField.setVisible(false);
            passLabel.setVisible(false);
            userLabel.setText("Logged in as: ");
            userNameTextField.setText(this.userName);
            userNameTextField.setEditable(false);
            userImage.setImage(superUserImage);
            logButtonImageView.setImage(logOutImage);
            logButton.setTooltip(null);
            logButton.setTooltip(new Tooltip("Log out"));
        }
    }

    private void clearData() {
        passwordTextField.setText("");
        userNameTextField.setText("");
    }
}
