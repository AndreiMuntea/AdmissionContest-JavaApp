package DatabaseManager.TableManager;

import DatabaseManager.DatabaseDomain.Query;
import Domain.Candidate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrei on 2017-01-03.
 */
public class CandidateTableManager extends AbstractTableManager<Integer, Candidate> {


    public CandidateTableManager() {
    }

    public CandidateTableManager(String tableName) {
        super(tableName);
    }

    @Override
    public Map<String, String> mapObject(Candidate object) {
        Map<String, String> properties = new HashMap<String, String>();

        properties.put("ID", object.getID().toString());
        properties.put("name", object.getName());
        properties.put("grade", object.getGrade().toString());
        properties.put("address", object.getAddress());
        properties.put("phoneNumber", object.getPhoneNumber());

        return properties;
    }

    @Override
    public Candidate createObject(ResultSet result) throws SQLException {
        Candidate candidate = new Candidate();

        candidate.setID(result.getInt("ID"));
        candidate.setName(result.getString("name"));
        candidate.setGrade(result.getDouble("grade"));
        candidate.setAddress(result.getString("address"));
        candidate.setPhoneNumber(result.getString("phoneNumber"));

        return candidate;
    }

    @Override
    public Map<String, String> mapID(Integer ID) {
        Map<String, String> properties = new HashMap<String, String>();

        properties.put("ID", ID.toString());
        return properties;
    }

    private Query filterByName(String arg)
    {
        ArrayList<String> queryArguments = new ArrayList<String>();
        String query = String.format("`%s`.name LIKE ? ESCAPE '!'", tableName);
        queryArguments.add(arg);
        return new Query(query, queryArguments);
    }

    private Query filterByGrade(String arg, String sign)
    {
        ArrayList<String> queryArguments = new ArrayList<String>();
        String query = String.format("`%s`.grade %s ?", tableName, sign);
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
        if(filter.equals("Greater than")) return filterByGrade(filterArg, ">=");
        if(filter.equals("Smaller than")) return filterByGrade(filterArg, "<=");
        if(filter.equals("Equals with")) return filterByGrade(filterArg, "=");

        return null;
    }
}
