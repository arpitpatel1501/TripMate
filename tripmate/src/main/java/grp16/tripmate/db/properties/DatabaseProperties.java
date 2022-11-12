package grp16.tripmate.db.properties;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {

    private ILogger logger = new MyLogger(this);

    static DatabaseProperties databaseProperties;
    public static final String DATABASE_CONFIGURATION_FILE = "./application.properties";

    public String getDatabaseURL() {
        return databaseURL;
    }

    public String getDatabaseUserName() {
        return databaseUserName;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    private String databaseURL, databaseUserName, databasePassword;

    private DatabaseProperties() throws Exception {
        setProfileProperties(getActiveProfile());
    }

    public static DatabaseProperties getInstance() throws Exception {
        if (databaseProperties == null) {
            databaseProperties = new DatabaseProperties();
        }
        return databaseProperties;
    }

    private String getActiveProfile() throws IOException {

        String activeProfile = loadPropertiesFromFile(DATABASE_CONFIGURATION_FILE).getProperty("spring.profiles.active");

        if (activeProfile == null) {
            activeProfile = "devint";
        }
        return activeProfile;
    }

    private Properties loadPropertiesFromFile(String propertiesFileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(propertiesFileName)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    private void setProfileProperties(String env) throws Exception {
        String filename = "application-" + env + ".properties";
        Properties properties = loadPropertiesFromFile(filename);
        logger.info(filename);
        this.databaseURL = properties.getProperty("databaseURL") + properties.getProperty("database");
        this.databaseUserName = properties.getProperty("username");
        this.databasePassword = properties.getProperty("password");
        logger.info(databaseURL);
        logger.info(databaseUserName);
        logger.info(databasePassword);
    }
}