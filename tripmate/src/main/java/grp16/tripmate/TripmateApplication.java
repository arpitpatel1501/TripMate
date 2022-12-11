package grp16.tripmate;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.db.sql.LoadSQLProfile;
import grp16.tripmate.properties.MyProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.sql.Connection;

@SpringBootApplication
public class TripmateApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(TripmateApplication.class).profiles("devint", "production", "test").run(args);

        IDatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getDatabaseConnection();

        LoadSQLProfile loadSQLProfile = new LoadSQLProfile();
        loadSQLProfile.loadSQLforProfile(connection, MyProperties.getInstance().getActiveProfile());
    }
}