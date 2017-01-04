package Utils.Exceptions;

/**
 * Created by andrei on 2017-01-03.
 */
public class MyException extends Exception {

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
