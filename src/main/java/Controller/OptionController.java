package Controller;

import Controller.AbstractController;
import Controller.ControllerExceptions.ControllerException;
import Domain.Option;
import Repository.IRepository;
import Utils.Pair.Pair;
import Validator.IValidator;

/**
 * Created by andrei on 2017-01-04.
 */
public class OptionController extends AbstractController<Pair<Integer, Integer>, Option> {

    public OptionController(IRepository<Pair<Integer, Integer>, Option> repository, IValidator<Option> validator) {
        super(repository, validator);
    }

    @Override
    public Option CreateFromFormat(String... format) throws ControllerException {
        return new Option(CreateIDFromFormat(format));
    }

    @Override
    public Pair<Integer, Integer> CreateIDFromFormat(String... format) throws ControllerException {
        if (format.length != 2) throw new ControllerException("Invalid number of parameters!\n");

        try{
            Integer candidateID = Integer.parseInt(format[0]);
            Integer sectionID = Integer.parseInt(format[1]);

            return new Pair<>(candidateID, sectionID);
        }catch(NumberFormatException e){
            throw new ControllerException("ID should be a positive Integer: " + e.getMessage() + "\n");
        }
    }
}
