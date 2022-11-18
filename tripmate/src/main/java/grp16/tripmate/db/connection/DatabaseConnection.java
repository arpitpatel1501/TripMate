package grp16.tripmate.db.connection;

import grp16.tripmate.db.properties.DatabaseProperties;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements DatabaseConnectionDAO {

    private final ILogger logger = new MyLogger(this);
    private Connection connection = null;


    private Connection connectToDatabase() throws Exception {
        String databaseURL = DatabaseProperties.getInstance().getDatabaseURL();
        String databaseUserName = DatabaseProperties.getInstance().getDatabaseUserName();
        String databasePassword = DatabaseProperties.getInstance().getDatabasePassword();

        logger.info(databaseURL);
        logger.info(databaseUserName);
        logger.info(databasePassword);
        return DriverManager.getConnection(databaseURL, databaseUserName, databasePassword);
    }

    @Override
    public Connection getDatabaseConnection() throws Exception {
        clearDatabaseConnection();
        connection = connectToDatabase();
        return connection;
    }

    @Override
    public void clearDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            connection = null;
        }
    }
}
