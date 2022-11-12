package grp16.tripmate.db.connection;

import grp16.tripmate.db.DatabaseConnectionException;

import java.sql.Connection;

public interface DatabaseConnectionDAO {
    Connection getDatabaseConnection() throws Exception;

    void clearDatabaseConnection();
}
