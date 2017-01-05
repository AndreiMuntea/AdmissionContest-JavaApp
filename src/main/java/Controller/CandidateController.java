package Controller;

import Controller.ControllerExceptions.ControllerException;
import Domain.Candidate;
import Helper.Saver.FileSaver.CSVFile.CandidateCSVFileSaver;
import Helper.Saver.FileSaver.HTMLFile.CandidateHTMLSaver;
import Helper.Saver.FileSaver.TextFile.CandidateFileSaver;
import Helper.Saver.ISaver;
import Repository.IRepository;
import Utils.Exceptions.MyException;
import Validator.IValidator;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by andrei on 2017-01-04.
 */
public class CandidateController extends AbstractController<Integer, Candidate> {

    public CandidateController(IRepository<Integer, Candidate> repository, IValidator<Candidate> validator) {
        super(repository, validator);
    }

    @Override
    public Candidate CreateFromFormat(String... format) throws ControllerException {
        if (format.length != 5) {
            throw new ControllerException("Invalid number of parameters!\n");
        }

        try {
            Integer ID = Integer.parseInt(format[0]);
            String name = format[1];
            String address = format[2];
            Double grade = Double.parseDouble(format[3]);
            String phoneNumber = format[4];

            return new Candidate(ID, name, address, grade, phoneNumber);

        } catch (NumberFormatException e) {
            throw new ControllerException("ID should be a positive integer and grade should be between [1,10] " + e.getMessage() + "\n");
        }
    }

    @Override
    public Integer CreateIDFromFormat(String... format) throws ControllerException {
        if (format.length != 1) {
            throw new ControllerException("Invalid number of parameters!\n");
        }

        try {
            Integer ID = Integer.parseInt(format[0]);
            return ID;
        } catch (NumberFormatException e) {
            throw new ControllerException("ID should be a positive integer " + e.getMessage() + "\n");
        }
    }

    @Override
    public ISaver<Candidate> getCSVFileSaver() {
        return new CandidateCSVFileSaver();
    }

    @Override
    public ISaver<Candidate> getFileSaver() {
        return new CandidateFileSaver("|");
    }

    @Override
    public ISaver<Candidate> getHTMLSaver() {
        return new CandidateHTMLSaver();
    }

    public List<Candidate> filterByPrefix(String prefix) throws MyException {
        Predicate<Candidate> predicate = c-> c.getName().startsWith(prefix);
        return FilterList(GetAll(),predicate);
    }

    public List<Candidate> filterByGrade(String grade) throws MyException{
        Double compareGrade;
        try{
            compareGrade = Double.parseDouble(grade);
            Predicate<Candidate> predicate = c->c.getGrade() < compareGrade;
            return FilterList(GetAll(), predicate);
        }catch(NumberFormatException e){
            throw new ControllerException("Grade should be a valid number!\n");
        }
    }
}
