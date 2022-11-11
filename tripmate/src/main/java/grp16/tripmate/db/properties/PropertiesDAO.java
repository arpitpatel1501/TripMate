package grp16.tripmate.db.properties;

import grp16.tripmate.db.DatabaseConnectionException;

import java.sql.Connection;

public interface PropertiesDAO {
    Connection getDatabaseConnection() throws DatabaseConnectionException;
}
