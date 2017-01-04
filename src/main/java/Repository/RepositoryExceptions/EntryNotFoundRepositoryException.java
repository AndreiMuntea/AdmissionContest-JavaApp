package Repository.RepositoryExceptions;

/**
 * Created by andrei on 2017-01-04.
 */
public class EntryNotFoundRepositoryException extends RepositoryException {
    public EntryNotFoundRepositoryException() {
    }

    public EntryNotFoundRepositoryException(String message) {
        super(message);
    }
}
