package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }
            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {
    }

    //Retrieve int string value from config.properties
    public static String get(String key) {
        // Priority: system property > config file
        return System.getProperty(key,
                properties.getProperty(key));
    }

    //Retrieve int config value from config.properties
    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    //Retrieve boolean config value from config.properties
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
