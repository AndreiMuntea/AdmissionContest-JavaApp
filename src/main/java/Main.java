
import Controller.CandidateController;
import Controller.ControllerExceptions.OptionController;
import Controller.SectionController;
import DatabaseManager.DatabaseManager;
import DatabaseManager.TableManager.AbstractTableManager;
import DatabaseManager.TableManager.CandidateTableManager;
import DatabaseManager.TableManager.OptionTableManager;
import DatabaseManager.TableManager.SectionTableManager;
import Domain.Candidate;
import Domain.Option;
import Domain.Section;
import Repository.DatabaseRepository;
import Repository.IRepository;
import Utils.Pair.Pair;
import Validator.CandidateValidator;
import Validator.IValidator;
import Validator.OptionValidator;
import Validator.SectionValidator;
import javafx.scene.chart.PieChart;

import java.sql.*;

/**
 * Created by andrei on 2017-01-03.
 */
public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/app";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "test";

    public static void main(String[] args) throws  Exception {

        DatabaseManager dbManager = DatabaseManager.newInstance(JDBC_DRIVER, DB_URL, USER, PASS);

        AbstractTableManager<Integer, Candidate> candidateTableManager = new CandidateTableManager("candidates");
        IRepository<Integer, Candidate> crepo = new DatabaseRepository<Integer, Candidate>(dbManager, candidateTableManager, 5);
        IValidator<Candidate> cvalidator = new CandidateValidator();
        CandidateController cctr = new CandidateController(crepo, cvalidator);

        AbstractTableManager<Integer, Section> sectionTableManager = new SectionTableManager("sections");
        IRepository<Integer, Section> srepo = new DatabaseRepository<Integer, Section>(dbManager, sectionTableManager, 5);
        IValidator<Section> svalidator = new SectionValidator();
        SectionController sctr = new SectionController(srepo, svalidator);


        AbstractTableManager<Pair<Integer, Integer>, Option> optionTableManager = new OptionTableManager("options");
        IRepository<Pair<Integer, Integer>, Option> orepo = new DatabaseRepository<Pair<Integer, Integer>, Option>(dbManager, optionTableManager, 5);
        IValidator<Option> ovalidator = new OptionValidator();
        OptionController optionController = new OptionController(orepo, ovalidator);

        Candidate c  = new Candidate(1,"Ionica","Calea Dunarii",10.0,"0727126338");
        Candidate uc = new Candidate(2,"Marcel","Calea Turzii",5.0,"0726761562");

        Section s1 = new Section(1, "section 1", 50);
        Section s2 = new Section(2, "section 2", 100);
        Section s3 = new Section(3, "section 3", 150);

        Option o11 = new Option(1,1);
        Option o12 = new Option(1, 2);
        Option o21 = new Option(2, 1);

        System.out.println(optionController.GetPage("0"));
    }
}
