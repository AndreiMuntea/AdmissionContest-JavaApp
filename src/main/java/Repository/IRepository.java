package Repository;

import Repository.RepositoryExceptions.RepositoryException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by andrei on 2017-01-03.
 */
public interface IRepository<ID, E>
{
    void AddElement(E element) throws RepositoryException;

    E GetElement(ID ID) throws RepositoryException;

    void UpdateElement(E updatedElement) throws RepositoryException;

    void RemoveElement(ID ID) throws RepositoryException;

    Boolean ExistsElement(ID ID) throws RepositoryException;

    List<E> GetAll() throws RepositoryException;

    List<E> GetPage(Integer pageSize, Integer pageNumber) throws RepositoryException;

    List<E> Filter(Integer pageSize, Integer pageNumber, HashMap<String, String> filters) throws RepositoryException;
}

