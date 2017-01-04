package Validator;

import Validator.ValidatorExceptions.ValidatorException;

/**
 * Created by andrei on 2017-01-04.
 */
public interface IValidator<T> {
    void Validate(T object) throws ValidatorException;
}
