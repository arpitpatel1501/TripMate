package grp16.tripmate.postrequest.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyRequest {

    private final ILogger logger = new MyLoggerAdapter(this);
    private final IDatabaseConnection databaseConnection;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    private int id;
    private PostRequestStatus status;
    private String postTitle;
    private String firstNameCreator;
    private String lastNameCreator;

    public MyRequest() {
        databaseConnection = new DatabaseConnection();
    }

    public PostRequestStatus getStatus() {
        return status;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getFirstNameCreator() {
        return firstNameCreator;
    }

    public String getLastNameCreator() {
        return lastNameCreator;
    }

    public void setStatus(PostRequestStatus status) {
        this.status = status;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setFirstNameCreator(String firstNameCreator) {
        this.firstNameCreator = firstNameCreator;
    }

    public void setLastNameCreator(String lastNameCreator) {
        this.lastNameCreator = lastNameCreator;
    }

    public Statement getConnection() throws Exception {
        connection = databaseConnection.getDatabaseConnection();
        statement = connection.createStatement();

        return statement;
    }

    public ResultSet resultExecuteQuery(String query) throws Exception {
        statement = getConnection();
        resultSet = statement.executeQuery(query);

        return resultSet;
    }

    public List<MyRequest> resultMyRequests(String query) throws Exception {
        resultSet = resultExecuteQuery(query);

        List<MyRequest> results = new ArrayList<>();
        while (resultSet.next()) {
            MyRequest myRequest = new MyRequest();
            if (resultSet.getString("status").equals("pending")) {
                myRequest.setStatus(PostRequestStatus.PENDING);
            } else if (resultSet.getString("status").equals("approved")) {
                myRequest.setStatus(PostRequestStatus.APPROVED);
            } else {
                myRequest.setStatus(PostRequestStatus.REJECTED);
            }
            myRequest.setPostTitle(resultSet.getString("postTitle"));
            myRequest.setFirstNameCreator(resultSet.getString("firstNameCreator"));
            myRequest.setLastNameCreator(resultSet.getString("lastNameCreator"));

            logger.info(resultSet.getString("firstNameCreator"));
            results.add(myRequest);

//            myRequest.id = 1;
//            myRequest.userName = "Harshil";
//            myRequest.postId = 3;
//            myRequest.status = PostRequestStatus.PENDING;
//            results.add(myRequest);
//
//            MyRequest myRequest2 = new MyRequest();
//            myRequest2.id = 4;
//            myRequest2.userName = "Harshil 2";
//            myRequest2.postId = 5;
//            myRequest2.status = PostRequestStatus.PENDING;
//            results.add(myRequest2);
        }
        connection.close();
        return results;
    }
}
