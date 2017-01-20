package DatabaseManager.TableManager;

import DatabaseManager.DatabaseDomain.Query;
import Domain.HasID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrei on 2017-01-03.
 */
public abstract class AbstractTableManager<ID, E extends HasID<ID>> {

    protected String tableName;

    public AbstractTableManager(String tableName) {
        this.tableName = tableName;
    }

    public AbstractTableManager() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Query createInsertQuery(E object) {
        Map<String, String> properties = mapObject(object);
        ArrayList<String> queryArguments = new ArrayList<String>();

        String columns = "";
        String values = "";
        String query = "";

        for (String key : properties.keySet()) {
            columns += key + ", ";
            values += "?, ";
            queryArguments.add(properties.get(key));
        }
        // remove the last ", "
        columns = columns.substring(0, columns.length() - 2);
        values = values.substring(0, values.length() - 2);

        query = String.format("INSERT INTO `%s` (%s) VALUES (%s)", tableName, columns, values);

        return new Query(query, queryArguments);
    }

    public Query createRemoveQuery(ID ID) {
        Map<String, String> properties = mapID(ID);
        ArrayList<String> queryArguments = new ArrayList<String>();
        String condition = "";
        String query = "";

        for (String key : properties.keySet()) {
            condition += key + " = ? AND ";
            queryArguments.add(properties.get(key));
        }

        // remove the last " AND "
        condition = condition.substring(0, condition.length() - 5);

        query = String.format("DELETE FROM `%s` WHERE %s", tableName, condition);

        return new Query(query, queryArguments);
    }

    public Query createUpdateQuery(E object) {
        Map<String, String> properties = mapObject(object);
        Map<String, String> propertiesID = mapID(object.getID());
        ArrayList<String> queryArguments = new ArrayList<String>();

        String fields = "";
        String condition = "";
        String query = "";

        for (String key : properties.keySet()) {
            fields += key + "=?, ";
            queryArguments.add(properties.get(key));
        }
        fields = fields.substring(0, fields.length() - 2);

        for (String key : propertiesID.keySet()) {
            condition += key + "=? AND ";
            queryArguments.add(properties.get(key));
        }
        condition = condition.substring(0, condition.length() - 5);

        query = String.format("UPDATE `%s` SET %s WHERE %s", tableName, fields, condition);
        return new Query(query, queryArguments);
    }

    public Query createGetElementQuery(ID ID) {
        Map<String, String> propertiesID = mapID(ID);
        ArrayList<String> queryArguments = new ArrayList<String>();

        String condition = "";
        String query = "";

        for (String key : propertiesID.keySet()) {
            condition += "t." + key + " = ? AND ";
            queryArguments.add(propertiesID.get(key));
        }
        condition = condition.substring(0, condition.length() - 5);

        query = String.format("SELECT t.* FROM `%s` t WHERE %s", tableName, condition);
        return new Query(query, queryArguments);
    }

    public Query createGetAllQuery() {
        ArrayList<String> queryArguments = new ArrayList<String>();
        String query = String.format("SELECT * FROM `%s`", tableName);
        return new Query(query, queryArguments);
    }

    public Query createGetRangeQuery(int startRow, int rowCount) {
        ArrayList<String> queryArguments = new ArrayList<String>();
        String query = String.format("SELECT * FROM `%s` LIMIT %d, %d", tableName, startRow, rowCount);
        return new Query(query, queryArguments);
    }


    public Query createFilterQuery(int startRow, int rowCount, HashMap<String, String>filters)
    {
        ArrayList<String> queryArguments = new ArrayList<String>();
        String queryStatement = String.format("SELECT * FROM `%s`", tableName);

        Query query = new Query(queryStatement, queryArguments);

        if(filters.size() > 0) {
            query.addStatement(" WHERE ");
            filters.keySet().forEach(key ->
                    query.addQuery(analyzeFilter(key, filters.get(key)), " AND ")
            );
            query.setQuery(query.getQuery().substring(0, query.getQuery().length() - 4));
        }

        query.addStatement(String.format(" LIMIT %d, %d", startRow, rowCount));

        return query;
    }

    public abstract Query analyzeFilter(String filter, String argument);

    public abstract Map<String, String> mapObject(E object);

    public abstract Map<String, String> mapID(ID ID);

    public abstract E createObject(ResultSet result) throws SQLException;
}
