package Repository.DatabaseRepository;

import DatabaseManager.DatabaseManager;
import DatabaseManager.TableManager.AbstractTableManager;
import Domain.HasID;
import Repository.IRepository;
import Repository.RepositoryExceptions.DuplicateEntryRepositoryException;
import Repository.RepositoryExceptions.RepositoryException;

import java.util.List;


/**
 * Created by andrei on 2017-01-03.
 */
public class DatabaseRepository<ID, E extends HasID<ID>> implements IRepository<ID, E> {

    protected AbstractTableManager<ID, E> tableManager;
    protected DatabaseManager databaseManager;

    public DatabaseRepository(DatabaseManager databaseManager, AbstractTableManager<ID, E> tableManager) {
        this.tableManager = tableManager;
        this.databaseManager = databaseManager;
    }

    public void AddElement(E element) throws RepositoryException {
        if (ExistsElement(element.getID())) {
            throw new DuplicateEntryRepositoryException(String.format("Entry with ID %s already exists!\n", element.getID().toString()));
        }
        databaseManager.InsertData(tableManager, element);
    }

    public E GetElement(ID ID) throws RepositoryException {
        if (!ExistsElement(ID)) {
            throw new DuplicateEntryRepositoryException(String.format("Entry with ID %s doesn't exists!\n", ID.toString()));
        }
        return databaseManager.GetElement(tableManager, ID);

    }

    public void UpdateElement(E updatedElement) throws RepositoryException {
        if (!ExistsElement(updatedElement.getID())) {
            throw new DuplicateEntryRepositoryException(String.format("Entry with ID %s doesn't exists!\n", updatedElement.getID().toString()));
        }
        databaseManager.UpdateData(tableManager, updatedElement);
    }

    public void RemoveElement(ID ID) throws RepositoryException {
        if (!ExistsElement(ID)) {
            throw new DuplicateEntryRepositoryException(String.format("Entry with ID %s doesn't exists!\n", ID.toString()));
        }
        databaseManager.RemoveData(tableManager, ID);
    }

    public Boolean ExistsElement(ID ID) throws RepositoryException {
        return databaseManager.ExistsElement(tableManager, ID);
    }

    public List<E> GetAll() throws RepositoryException {
        return databaseManager.GetAll(tableManager);
    }

    public List<E> GetPage(Integer pageSize, Integer pageNumber) throws RepositoryException {
        return databaseManager.GetRange(tableManager, pageSize * pageNumber, pageSize);
    }
}
