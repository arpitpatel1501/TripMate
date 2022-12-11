package grp16.tripmate.forgetPassword.Model;


import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.forgetPassword.database.ForgetPasswordQueryBuilder;
import grp16.tripmate.forgetPassword.database.IForgetPasswordQueryBuilder;
import grp16.tripmate.notification.EmailNotificationFactory;
import grp16.tripmate.notification.INotification;
import grp16.tripmate.properties.MyProperties;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.database.UserQueryBuilder;
import grp16.tripmate.user.model.UserDbColumnNames;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ForgetPassword implements IForgetPassword{

    String emailSender;
    private String messageSubject = "Reset Password Request Code";
    private INotification iNotification;
    private IForgetPasswordQueryBuilder queryBuilder;
    private final IDatabaseConnection dbConnection;
    private int id;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ForgetPassword() {
        this.queryBuilder = ForgetPasswordQueryBuilder.getInstance();
        dbConnection = new DatabaseConnection();
    }

    @Override
    public boolean checkUserExist(String email) throws Exception {
        this.email = email;
        Connection connection = dbConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
        String query = queryBuilder.checkUserExist(email);
        ResultSet resultSet = statement.executeQuery(query);
        boolean gotResult = resultSet.next();
        connection.close();
        return gotResult;
    }

    @Override
    public boolean changeUserPassword(String email, String password) throws Exception {
        Connection connection = dbConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
        String query = queryBuilder.changeUserPassword(email, password);
        int rowUpdate = statement.executeUpdate(query);
        boolean isRowUpdated;
        if (rowUpdate == 1) {
            isRowUpdated = true;
        }
        else {
            isRowUpdated = false;
        }
        connection.close();
        return isRowUpdated;
    }

}
