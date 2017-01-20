import Controller.CandidateController;
import Controller.OptionController;
import Controller.SectionController;
import GUI.GUI;
import Helper.ConfigLoader.ConfigLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URLDecoder;

/**
 * Created by andrei on 2017-01-03.
 */
public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext(URLDecoder.decode(getClass().getResource("/config/classes.xml").toString(), "UTF-8"));

        ConfigLoader config = (ConfigLoader) context.getBean("configLoader");

        CandidateController candidateController = (CandidateController) context.getBean("CandidateController");
        SectionController sectionController = (SectionController) context.getBean("SectionController");
        OptionController optionController = (OptionController) context.getBean("OptionController");

        GUI gui = new GUI(primaryStage, candidateController, sectionController, optionController, config);
        gui.start();
    }
}
