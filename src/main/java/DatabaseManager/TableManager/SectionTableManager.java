package DatabaseManager.TableManager;

import DatabaseManager.DatabaseDomain.Query;
import Domain.Section;
import Helper.ConfigLoader.DatabaseConfigLoader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrei on 2017-01-04.
 */
public class SectionTableManager extends AbstractTableManager<Integer, Section> {

    public void setDatabaseConfigLoader(DatabaseConfigLoader databaseConfigLoader)
    {
        this.tableName = databaseConfigLoader.getSectionsTable();
    }
    @Override
    public Map<String, String> mapObject(Section object) {
        Map<String, String> properties = new HashMap<String, String>();

        properties.put("ID", object.getID().toString());
        properties.put("name", object.getName());
        properties.put("slots", object.getAvailableSlots().toString());

        return properties;
    }

    @Override
    public Map<String, String> mapID(Integer ID) {
        Map<String, String> properties = new HashMap<String, String>();

        properties.put("ID", ID.toString());
        return properties;
    }

    @Override
    public Section createObject(ResultSet result) throws SQLException {
        Section section = new Section();

        section.setID(result.getInt("ID"));
        section.setName(result.getString("name"));
        section.setAvailableSlots(result.getInt("slots"));

        return section;
    }

    private Query filterByName(String arg)
    {
        ArrayList<String> queryArguments = new ArrayList<String>();
        String query = String.format("`%s`.name LIKE ? ESCAPE '!'", tableName);
        queryArguments.add(arg);
        return new Query(query, queryArguments);
    }

    private Query filterBySlots(String arg, String sign)
    {
        ArrayList<String> queryArguments = new ArrayList<String>();
        String query = String.format("`%s`.slots %s ?", tableName, sign);
        queryArguments.add(arg);

        return new Query(query, queryArguments);
    }

    @Override
    public Query analyzeFilter(String filter, String filterArg)
    {
        filterArg = filterArg
                .replace("!","!!")
                .replace("%","!%")
                .replace("_","!_")
                .replace("[","![");
        if(filter.equals("Starts with")) return filterByName(filterArg + "%");
        if(filter.equals("Contains")) return filterByName("%" + filterArg + "%");
        if(filter.equals("Ends with")) return filterByName("%" + filterArg);
        if(filter.equals("Greater than")) return filterBySlots(filterArg, ">=");
        if(filter.equals("Smaller than")) return filterBySlots(filterArg, "<=");
        if(filter.equals("Equals with")) return filterBySlots(filterArg, "=");

        return null;
    }
}
