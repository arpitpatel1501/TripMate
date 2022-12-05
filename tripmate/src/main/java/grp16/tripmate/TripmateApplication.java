package grp16.tripmate;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.db.properties.DatabaseProperties;
import grp16.tripmate.db.sql.LoadSQLProfile;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;

@SpringBootApplication
public class TripmateApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(TripmateApplication.class).profiles("devint", "production", "test").run(args);

        IDatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getDatabaseConnection();

        LoadSQLProfile loadSQLProfile = new LoadSQLProfile();
        loadSQLProfile.loadSQLforProfile(connection, DatabaseProperties.getInstance().getActiveProfile());
    }
}