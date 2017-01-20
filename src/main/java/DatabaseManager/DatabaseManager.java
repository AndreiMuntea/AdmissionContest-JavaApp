package DatabaseManager;

import DatabaseManager.DatabaseDomain.Query;
import DatabaseManager.DatabaseExceptions.DatabaseException;
import DatabaseManager.TableManager.AbstractTableManager;
import Domain.HasID;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrei on 2017-01-03.
 */
public class DatabaseManager {
    private static DatabaseManager instance = null;
    private Connection connection;

    private DatabaseManager(String Driver, String databaseURL, String username, String password) throws DatabaseException {
        try {
            Class.forName(Driver);
            connection = DriverManager.getConnection(databaseURL, username, password);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public static DatabaseManager newInstance(String Driver, String databaseURL, String username, String password) throws DatabaseException {
        if (instance == null) instance = new DatabaseManager(Driver, databaseURL, username, password);
        return instance;
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
        super.finalize();
    }

    public <ID, E extends HasID<ID>> void InsertData(AbstractTableManager<ID, E> tableManager, E element) throws DatabaseException {
        Query query = tableManager.createInsertQuery(element);
        ExecuteQuery(tableManager, query);
    }

    public <ID, E extends HasID<ID>> void RemoveData(AbstractTableManager<ID, E> tableManager, ID elementID) throws DatabaseException {
        Query query = tableManager.createRemoveQuery(elementID);
        ExecuteQuery(tableManager, query);
    }

    public <ID, E extends HasID<ID>> void UpdateData(AbstractTableManager<ID, E> tableManager, E element) throws DatabaseException {
        Query query = tableManager.createUpdateQuery(element);
        ExecuteQuery(tableManager, query);
    }

    public <ID, E extends HasID<ID>> E GetElement(AbstractTableManager<ID, E> tableManager, ID elementID) throws DatabaseException {
        Query query = tableManager.createGetElementQuery(elementID);
        List<E> elements = ExecuteQuery(tableManager, query);
        if (elements == null || elements.size() == 0) {
            throw new DatabaseException("Item not found in database!\n");
        }
        return elements.get(0);
    }

    public <ID, E extends HasID<ID>> List<E> GetAll(AbstractTableManager<ID, E> tableManager) throws DatabaseException {
        Query query = tableManager.createGetAllQuery();
        List<E> elements = ExecuteQuery(tableManager, query);
        if (elements == null) {
            throw new DatabaseException("Failed to execute statement!\n");
        }
        return elements;
    }

    public <ID, E extends HasID<ID>> List<E> GetRange(AbstractTableManager<ID, E> tableManager, int start, int rowCount) throws DatabaseException {
        Query query = tableManager.createGetRangeQuery(start, rowCount);
        List<E> elements = ExecuteQuery(tableManager, query);
        if (elements == null) {
            throw new DatabaseException("Failed to execute statement!\n");
        }
        return elements;
    }

    public <ID, E extends HasID<ID>> List<E> ApplyFilters(AbstractTableManager<ID, E> tableManager,int start, int rowCount, HashMap<String, String> filters)throws DatabaseException{
        Query query = tableManager.createFilterQuery(start, rowCount, filters);
        List<E> elements = ExecuteQuery(tableManager, query);
        if (elements == null) {
            throw new DatabaseException("Failed to execute statement!\n");
        }
        return elements;
    }

    public <ID, E extends HasID<ID>> boolean ExistsElement(AbstractTableManager<ID, E> tableManager, ID elementID) throws DatabaseException {
        Query query = tableManager.createGetElementQuery(elementID);
        List<E> elements = ExecuteQuery(tableManager, query);
        return (!(elements == null || elements.size() == 0));
    }

    private <ID, E extends HasID<ID>> List<E> ProcessResult(AbstractTableManager<ID, E> tableManager, ResultSet result) throws DatabaseException {
        if (result == null) return null;

        List<E> results = new ArrayList<E>();
        try {
            while (result.next()) {
                results.add(tableManager.createObject(result));
            }
            return results;
        } catch (Exception e) {
            results.clear();
            throw new DatabaseException(e.getMessage());
        }
    }

    private <ID, E extends HasID<ID>> List<E> ExecuteQuery(AbstractTableManager<ID, E> tableManager, Query query) throws DatabaseException {
        String queryStatement = query.getQuery();
        List<String> queryArguments = query.getQueryArguments();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement);

            for (int i = 0; i < queryArguments.size(); ++i) {
                preparedStatement.setString(i + 1, queryArguments.get(i));
            }
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            List<E> resultedList = ProcessResult(tableManager, resultSet);

            if (resultSet != null) resultSet.close();
            preparedStatement.close();

            return resultedList;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


}
