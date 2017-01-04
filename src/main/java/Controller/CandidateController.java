package Controller;

import Controller.ControllerExceptions.ControllerException;
import Domain.Candidate;
import Repository.IRepository;
import Validator.IValidator;

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

}
