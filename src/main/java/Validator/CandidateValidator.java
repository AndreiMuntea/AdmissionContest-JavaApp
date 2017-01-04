package Validator;

import Domain.Candidate;
import Validator.ValidatorExceptions.ValidatorException;

/**
 * Created by andrei on 2017-01-04.
 */
public class CandidateValidator implements IValidator<Candidate> {

    public void Validate(Candidate object) throws ValidatorException {
        String errors = "";

        if (!ValidateID(object.getID()))
            errors += "ID must be greater than 0!\n";
        if (!ValidateName(object.getName()))
            errors += "Name can't be void or have more than 45 characters!\n";
        if (!ValidatePhoneNumber(object.getPhoneNumber()))
            errors += "Phone number must start with 0 and be a 10 digit string!\n";
        if (!ValidateAddress(object.getAddress()))
            errors += "Address can't be void or have more than 45 characters!\n";
        if (!ValidateGrade(object.getGrade()))
            errors += "Grade should be a number in range 1 - 10!\n";

        if (!errors.equals("")) {
            throw new ValidatorException(errors);
        }
    }


    private boolean ValidateID(Integer ID)
    {
        return (ID > 0);
    }

    private boolean ValidateName(String name)
    {
        return (name.length() > 0 && name.length() < 46);
    }

    private boolean ValidateAddress(String address)
    {
        return (address.length() > 0 && address.length() < 46);
    }

    private boolean ValidatePhoneNumber(String phoneNumber)
    {
        return (phoneNumber.length() == 10 && phoneNumber.matches("0[0-9]+"));
    }

    private boolean ValidateGrade(Double grade)
    {
        return (grade >= 1 && grade <= 10);
    }


}
