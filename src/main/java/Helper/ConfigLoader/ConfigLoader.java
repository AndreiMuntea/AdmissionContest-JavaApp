package Helper.ConfigLoader;

import Helper.Encryptor.IEncryption;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by andrei on 2017-01-20.
 */
public class ConfigLoader {

    private static final String PATH = "/config/config.yaml";
    private String configFile;
    private Yaml yaml;
    private Map<?, ?> config;
    private IEncryption encryptor;


    public ConfigLoader() throws Exception {
        this.configFile = URLDecoder.decode(this.getClass().getResource(PATH).getFile(), "UTF-8");
        this.yaml = new Yaml();
        loadConfiguration();
    }

    public void setEncryptor(IEncryption encryptor) {
        this.encryptor = encryptor;
    }

    private void loadConfiguration() {
        try {
            config = (Map<?, ?>) yaml.load(new FileReader(new File(configFile)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getPageSize() {
        return (Integer) config.get("page_size");
    }

    public ArrayList<Map<String, String>> getUsers() {
        return (ArrayList<Map<String, String>>) config.get("super_users");
    }

    public Boolean exists(String userName, String password) {
        ArrayList<Map<String, String>> allUsers = getUsers();
        Boolean found = false;

        for (Map<String, String> c : allUsers) {
            try {
                String p = encryptor.encrypt(password);
                found = found || (c.get("username").equals(userName) && c.get("password").equals(encryptor.encrypt(password)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return found;
    }


}
