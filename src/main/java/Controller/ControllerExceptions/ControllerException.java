package Controller.ControllerExceptions;

import Utils.Exceptions.MyException;

/**
 * Created by andrei on 2017-01-04.
 */
public class ControllerException extends MyException {
    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }
}
