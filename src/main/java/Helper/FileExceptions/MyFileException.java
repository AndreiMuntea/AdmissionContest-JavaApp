package Helper.FileExceptions;

import Utils.Exceptions.MyException;

/**
 * Created by andrei on 2017-01-05.
 */
public class MyFileException extends MyException {
    public MyFileException() {
    }

    public MyFileException(String message) {
        super(message);
    }
}
