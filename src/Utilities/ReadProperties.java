package Utilities;

import resources.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    private static ReadProperties instance = null;
    private Properties properties = null;

    private ReadProperties() {
        properties = new Properties();
        String userCurrDir = System.getProperty("user.dir");
        try {
            InputStream inputStream = new FileInputStream(userCurrDir + Constants.PROPERTIES_FILE_PATH);
            properties.load(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static synchronized ReadProperties getInstance() {
        if (instance == null) {
            instance = new ReadProperties();
        }
        return instance;
    }

    public String getValue(String key) {
        return this.properties.getProperty(key, String.format("The key %s does not exists!", key));
    }
}