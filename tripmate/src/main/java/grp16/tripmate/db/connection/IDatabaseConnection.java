package grp16.tripmate.db.connection;

import java.sql.Connection;

public interface IDatabaseConnection {
    Connection getDatabaseConnection() throws Exception;
}
