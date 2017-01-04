package Repository.RepositoryExceptions;

import Utils.Exceptions.MyException;

/**
 * Created by andrei on 2017-01-03.
 */
public class RepositoryException extends MyException {
    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
    }
}
