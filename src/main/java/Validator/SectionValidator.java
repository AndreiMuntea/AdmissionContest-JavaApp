package Validator;

import Domain.Section;
import Validator.ValidatorExceptions.ValidatorException;

/**
 * Created by andrei on 2017-01-04.
 */
public class SectionValidator implements IValidator<Section> {


    public void Validate(Section object) throws ValidatorException {
        String errors = "";

        if (!ValidateID(object.getID()))
            errors += "ID must be greater than 0!\n";
        if (!ValidateName(object.getName()))
            errors += "Name can't be void or have more than 45 characters!\n";
        if (!ValidateAvailableSlots(object.getAvailableSlots()))
            errors += "There should be at least one available slot!\n";

        if (!errors.equals("")) {
            throw new ValidatorException(errors);
        }
    }

    private Boolean ValidateName(String name) {
        return (name.length() > 0 && name.length() < 46);
    }

    private Boolean ValidateID(Integer ID) {
        return (ID > 0);
    }

    private Boolean ValidateAvailableSlots(Integer availableSlots) {
        return (availableSlots > 0);
    }
}
