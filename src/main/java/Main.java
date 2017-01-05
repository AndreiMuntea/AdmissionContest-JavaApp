import Controller.CandidateController;
import Controller.OptionController;
import Controller.SectionController;
import DatabaseManager.DatabaseManager;
import DatabaseManager.TableManager.AbstractTableManager;
import DatabaseManager.TableManager.CandidateTableManager;
import DatabaseManager.TableManager.OptionTableManager;
import DatabaseManager.TableManager.SectionTableManager;
import Domain.Candidate;
import Domain.Option;
import Domain.Section;
import GUI.GUI;
import Repository.DatabaseRepository;
import Repository.IRepository;
import Utils.Pair.Pair;
import Validator.CandidateValidator;
import Validator.IValidator;
import Validator.OptionValidator;
import Validator.SectionValidator;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by andrei on 2017-01-03.
 */
public class Main extends Application {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/app";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "test";

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseManager dbManager = DatabaseManager.newInstance(JDBC_DRIVER, DB_URL, USER, PASS);

        AbstractTableManager<Integer, Candidate> candidateTableManager = new CandidateTableManager("candidates");
        IRepository<Integer, Candidate> candidateRepository = new DatabaseRepository<Integer, Candidate>(dbManager, candidateTableManager);
        IValidator<Candidate> candidateValidator = new CandidateValidator();
        CandidateController candidateController = new CandidateController(candidateRepository, candidateValidator);

        AbstractTableManager<Integer, Section> sectionTableManager = new SectionTableManager("sections");
        IRepository<Integer, Section> sectionRepository = new DatabaseRepository<Integer, Section>(dbManager, sectionTableManager);
        IValidator<Section> sectionValidator = new SectionValidator();
        SectionController sectionController = new SectionController(sectionRepository, sectionValidator);


        AbstractTableManager<Pair<Integer, Integer>, Option> optionTableManager = new OptionTableManager("options");
        IRepository<Pair<Integer, Integer>, Option> optionRepository = new DatabaseRepository<Pair<Integer, Integer>, Option>(dbManager, optionTableManager);
        IValidator<Option> optionValidator = new OptionValidator();
        OptionController optionController = new OptionController(optionRepository, optionValidator);

        GUI gui = new GUI(primaryStage, candidateController, sectionController, optionController, 5);
        gui.start();
    }
}
