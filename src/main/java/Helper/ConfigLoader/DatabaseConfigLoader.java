package Helper.ConfigLoader;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by andrei on 2017-01-20.
 */
public class DatabaseConfigLoader {

    private static final String PATH = "/config/database.yaml";
    private String configFile;
    private Yaml yaml;
    private Map<?, ?> config;

    public DatabaseConfigLoader() throws Exception {
        this.configFile = URLDecoder.decode(this.getClass().getResource(PATH).getFile(), "UTF-8");
        this.yaml = new Yaml();
        loadConfiguration();
    }

    private void loadConfiguration() {
        try {
            config = (Map<?, ?>) yaml.load(new FileReader(new File(configFile)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getConnectionDetails() {
        return (Map<String, String>) config.get("conn_details");
    }

    public Map<String, String> getTablesDetails() {
        return (Map<String, String>) config.get("tables_details");
    }

    public String getJDBCDriver() {
        return getConnectionDetails().get("JDBC_DRIVER");
    }

    public String getDBURL() {
        return getConnectionDetails().get("DB_URL");
    }

    public String getUser() {
        return getConnectionDetails().get("USER");
    }

    public String getPassword() {
        return getConnectionDetails().get("PASS");
    }

    public String getCandidatesTable() {
        return getTablesDetails().get("candidates_table");
    }

    public String getSectionsTable() {
        return getTablesDetails().get("sections_table");
    }

    public String getOptionsTable() {
        return getTablesDetails().get("options_table");
    }
}
