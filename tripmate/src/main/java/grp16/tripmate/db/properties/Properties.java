package grp16.tripmate.db.properties;

import grp16.tripmate.db.DatabaseConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Properties {
//    private static final String DATABASE_CONFIGURATION_FILE = "./application.properties";
//    private Connection connection = null;

    static Properties properties;
    public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

    String databaseURL, databaseUserName, databasePassword;

    private Properties() {

    }

    public static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
        }
        return properties;
    }

    public static String getActiveProfile() {
        String activeProfile = System.getProperty(SPRING_PROFILES_ACTIVE);
        if (activeProfile == null) {
            activeProfile = "devint";
        }
        return activeProfile;
    }

    public void setProfileProperties(String env) throws Exception {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("application-" + getActiveProfile() + ".properties"))) {
            final java.util.Properties databaseConfigProperties = new java.util.Properties();
            databaseConfigProperties.load(inputStream);

            Class.forName(databaseConfigProperties.getProperty("mysqlJDBCDriver")).getDeclaredConstructor().newInstance();

            this.databaseURL = databaseConfigProperties.getProperty("databaseURL") +
                    databaseConfigProperties.getProperty("database");
            this.databaseUserName = databaseConfigProperties.getProperty("username");
            this.databasePassword = databaseConfigProperties.getProperty("password");
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException e) {
            throw new DatabaseConnectionException(e.getMessage(), e);
        }
    }
}
