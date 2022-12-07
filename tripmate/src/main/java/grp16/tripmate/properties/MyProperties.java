package grp16.tripmate.properties;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties implements IProperties{

    private static MyProperties myProperties = null;
    private final ILogger logger = new MyLoggerAdapter(this);

    private static final String PROPERTY_CONFIGURATION_FILE = "./application.properties";
    private String databaseURL, databaseUserName, databasePassword;
    private String mailSender;
    Properties properties = new Properties();
    public MyProperties(){
        String activeProfile = getActiveProfile();
        setProfileProperties(activeProfile);
    }

    public String getDatabaseURL() {
        return databaseURL;
    }

    public String getDatabaseUserName() {
        return databaseUserName;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getMailSender() {
        return mailSender;
    }

    public static MyProperties getInstance() throws Exception {
        if (myProperties == null) {
            myProperties = new MyProperties();
        }
        return myProperties;
    }

    public String getActiveProfile() {

        String activeProfile = loadPropertiesFromFile(PROPERTY_CONFIGURATION_FILE).getProperty("spring.profiles.active");

        if (activeProfile == null) {
            activeProfile = "devint";
        }
        return activeProfile;
    }

    private Properties loadPropertiesFromFile(String propertiesFileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(propertiesFileName)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    private void setProfileProperties(String env) {
        String filename = "application-" + env + ".properties";
        properties = loadPropertiesFromFile(filename);
        logger.info(filename);
        this.databaseURL = properties.getProperty("databaseURL") + properties.getProperty("database");
        this.databaseUserName = properties.getProperty("username");
        this.databasePassword = properties.getProperty("password");
        this.mailSender = properties.getProperty("spring.mail.username");
        logger.info(databaseURL);
        logger.info(databaseUserName);
        logger.info(databasePassword);
    }
}
