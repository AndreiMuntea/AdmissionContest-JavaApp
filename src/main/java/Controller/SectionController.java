package Controller;

import Controller.ControllerExceptions.ControllerException;
import Domain.Section;
import Helper.Saver.FileSaver.CSVFile.SectionCSVFileSaver;
import Helper.Saver.FileSaver.TextFile.SectionFileSaver;
import Helper.Saver.ISaver;
import Repository.IRepository;
import Validator.IValidator;

/**
 * Created by andrei on 2017-01-04.
 */
public class SectionController extends AbstractController<Integer, Section> {
    public SectionController(IRepository<Integer, Section> repository, IValidator<Section> validator) {
        super(repository, validator);
    }

    @Override
    public Section CreateFromFormat(String... format) throws ControllerException {

        if (format.length != 3) throw new ControllerException("Invalid number of parameters!\n");

        try{
            Integer sectionID = Integer.parseInt(format[0]);
            String sectionName = format[1];
            Integer availableSlots = Integer.parseInt(format[2]);

            return new Section(sectionID, sectionName, availableSlots);
        }catch(NumberFormatException e){
            throw new ControllerException("ID and available slots should be positive Integers: " + e.getMessage() + "\n");
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
    public ISaver<Section> getCSVFileSaver() {
        return new SectionCSVFileSaver();
    }

    @Override
    public ISaver<Section> getFileSaver() {
        return new SectionFileSaver("|");
    }
}
