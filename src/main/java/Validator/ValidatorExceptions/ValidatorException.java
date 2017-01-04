package Validator.ValidatorExceptions;

import Utils.Exceptions.MyException;

/**
 * Created by andrei on 2017-01-04.
 */
public class ValidatorException extends MyException {
    public ValidatorException() {
    }

    public ValidatorException(String message) {
        super(message);
    }
}
