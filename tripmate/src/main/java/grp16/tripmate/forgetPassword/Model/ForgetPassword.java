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
import java.sql.Statement;

public class ForgetPassword implements IForgetPassword{

    String emailSender;
    private String messageSubject = "Reset Password Request Code";
    private INotification iNotification;
    private IForgetPasswordQueryBuilder queryBuilder;
    private final IDatabaseConnection dbConnection;
    int uniqueNumber;

    public ForgetPassword() {
        this.queryBuilder = ForgetPasswordQueryBuilder.getInstance();
        dbConnection = new DatabaseConnection();
    }

    @Override
    public void sendUniqueCode(String userEmail) throws Exception {
        emailSender = MyProperties.getInstance().getMailSender();
        uniqueNumber = generateNumber();
        String body = "Your resent password code is: " + uniqueNumber;
        iNotification = EmailNotificationFactory.getInstance().createEmailNotification();
        iNotification.sendNotification(emailSender, userEmail, messageSubject, body);
    }
    private int generateNumber() {
        int min = 1000;
        int max = 9999;
        int range = (max-min+1);
        double random = Math.random();
        double finalNumber = Math.floor((random * range) + min);
//        int number = (int)Math.floor(Math.random()*(max-min+1)+min);
        return (int) finalNumber;
    }

    public void changeUserPassword() throws Exception {
//        Connection connection = dbConnection.getDatabaseConnection();
//        Statement statement = connection.createStatement();
//        this.setId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
//        String query = queryBuilder.changeUserDetails(this);
//        int rowUpdate = statement.executeUpdate(query);
//        connection.close();
    }
}
