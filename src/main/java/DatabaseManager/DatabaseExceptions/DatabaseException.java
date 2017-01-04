package DatabaseManager.DatabaseExceptions;

import Repository.RepositoryExceptions.RepositoryException;

/**
 * Created by andrei on 2017-01-03.
 */
public class DatabaseException extends RepositoryException {
    public DatabaseException() {
        super();
    }

    public DatabaseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
