package grp16.tripmate.db;

import java.sql.Connection;

public interface DatabaseConnectionDAO {
    Connection getDatabaseConnection() throws DatabaseConnectionException;

    void clearDatabaseConnection();
}
