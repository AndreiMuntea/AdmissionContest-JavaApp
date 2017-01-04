package Repository.RepositoryExceptions;

/**
 * Created by andrei on 2017-01-04.
 */
public class DuplicateEntryRepositoryException extends RepositoryException {
    public DuplicateEntryRepositoryException() {
    }

    public DuplicateEntryRepositoryException(String message) {
        super(message);
    }
}
