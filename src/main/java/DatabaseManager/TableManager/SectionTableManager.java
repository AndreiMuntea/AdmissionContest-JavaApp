package DatabaseManager.TableManager;

import Domain.Section;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrei on 2017-01-04.
 */
public class SectionTableManager extends AbstractTableManager<Integer, Section> {
    public SectionTableManager(String tableName) {
        super(tableName);
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
}
