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
import Helper.ConfigLoader.ConfigLoader;
import Helper.ConfigLoader.DatabaseConfigLoader;
import Helper.Encryptor.AESEncryptor;
import Helper.Encryptor.IEncryption;
import Repository.DatabaseRepository.DatabaseRepository;
import Repository.IRepository;
import Utils.Pair.Pair;
import Validator.CandidateValidator;
import Validator.IValidator;
import Validator.OptionValidator;
import Validator.SectionValidator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by andrei on 2017-01-03.
 */
public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        IEncryption encriptor = new AESEncryptor();

        ConfigLoader config = ConfigLoader.newInstance(URLDecoder.decode(getClass().getResource("/config/config.yaml").getFile(), "UTF-8"), encriptor);
        DatabaseConfigLoader databaseConfigLoader = DatabaseConfigLoader.newInstance(URLDecoder.decode(getClass().getResource("/config/database.yaml").getFile(), "UTF-8"));

        DatabaseManager dbManager = DatabaseManager.newInstance(
                databaseConfigLoader.getJDBCDriver(),
                databaseConfigLoader.getDBURL(),
                databaseConfigLoader.getUser(),
                databaseConfigLoader.getPassword());

        AbstractTableManager<Integer, Candidate> candidateTableManager = new CandidateTableManager(databaseConfigLoader.getCandidatesTable());
        IRepository<Integer, Candidate> candidateRepository = new DatabaseRepository<Integer, Candidate>(dbManager, candidateTableManager);
        IValidator<Candidate> candidateValidator = new CandidateValidator();
        CandidateController candidateController = new CandidateController(candidateRepository, candidateValidator);

        AbstractTableManager<Integer, Section> sectionTableManager = new SectionTableManager(databaseConfigLoader.getSectionsTable());
        IRepository<Integer, Section> sectionRepository = new DatabaseRepository<Integer, Section>(dbManager, sectionTableManager);
        IValidator<Section> sectionValidator = new SectionValidator();
        SectionController sectionController = new SectionController(sectionRepository, sectionValidator);


        AbstractTableManager<Pair<Integer, Integer>, Option> optionTableManager = new OptionTableManager(databaseConfigLoader.getOptionsTable());
        IRepository<Pair<Integer, Integer>, Option> optionRepository = new DatabaseRepository<Pair<Integer, Integer>, Option>(dbManager, optionTableManager);
        IValidator<Option> optionValidator = new OptionValidator();
        OptionController optionController = new OptionController(optionRepository, optionValidator, candidateRepository, sectionRepository);

        GUI gui = new GUI(primaryStage, candidateController, sectionController, optionController, config);
        gui.start();
    }
}
