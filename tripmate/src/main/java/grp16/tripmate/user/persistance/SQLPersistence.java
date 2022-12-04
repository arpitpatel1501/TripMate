package grp16.tripmate.user.persistance;

import grp16.tripmate.db.DatabaseConnectionException;
import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.user.model.User;

import java.sql.Connection;

public class SQLPersistance implements IUserPersistance{
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection;

    @Override
    public User getUser() throws Exception {
        connection = databaseConnection.getDatabaseConnection();
        connection.close();
        return null;
    }
}
