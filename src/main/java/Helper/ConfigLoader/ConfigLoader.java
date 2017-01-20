package Helper.ConfigLoader;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by andrei on 2017-01-20.
 */
public class ConfigLoader {

    private static ConfigLoader instance = null;
    private String configFile;
    private Yaml yaml;
    private Map<?, ?> config;

    private ConfigLoader(String configFile) {
        this.configFile = configFile;
        this.yaml = new Yaml();
        this.loadConfiguration();
    }

    public static ConfigLoader newInstance(String configFile) {
        if (instance == null) instance = new ConfigLoader(configFile);
        return instance;
    }

    private void loadConfiguration() {
        try {
            config = (Map<?, ?>) yaml.load(new FileReader(new File(configFile)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getPageSize()
    {
        return (Integer) config.get("page_size");
    }

    public ArrayList<Map<String, String>> getUsers()
    {
        return (ArrayList<Map<String, String>>) config.get("super_users");
    }

    public Boolean exists(String userName, String password)
    {
        ArrayList<Map<String,String>> allUsers = getUsers();
        Boolean found = false;

        for(Map<String,String> c : allUsers) {
            found = found || (c.get("username").equals(userName) && c.get("password").equals(password));
        }

        return found;
    }


}
