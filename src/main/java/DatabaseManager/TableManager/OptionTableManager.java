package DatabaseManager.TableManager;

import Domain.Option;
import Utils.Pair.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrei on 2017-01-04.
 */
public class OptionTableManager extends AbstractTableManager<Pair<Integer, Integer>, Option> {

    public OptionTableManager(String tableName) {
        super(tableName);
    }

    @Override
    public Map<String, String> mapObject(Option object) {
        Map<String, String> properties = new HashMap<String, String>();

        properties.put("candidateID", object.getCandidateID().toString());
        properties.put("sectionID", object.getSectionID().toString());

        return properties;
    }

    @Override
    public Map<String, String> mapID(Pair<Integer, Integer> ID) {
        Map<String, String> properties = new HashMap<String, String>();

        properties.put("candidateID", ID.getFirst().toString());
        properties.put("sectionID", ID.getSecond().toString());

        return properties;
    }

    @Override
    public Option createObject(ResultSet result) throws SQLException {
        Option option = new Option();
        Pair<Integer, Integer> ID = new Pair<Integer, Integer>();

        ID.setFirst(result.getInt("candidateID"));
        ID.setSecond(result.getInt("sectionID"));

        option.setID(ID);
        return option;
    }

}
