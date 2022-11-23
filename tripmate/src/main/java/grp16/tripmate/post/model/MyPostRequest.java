package grp16.tripmate.post.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.DatabaseConnectionDAO;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyPostRequest {

    private final ILogger logger = new MyLogger(this);
    private DatabaseConnectionDAO databaseConnectionDAO;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private int id;
    private PostRequestStatus status;
    private int postId;
    private String firstNameRequestee;
    private String lastNameRequestee;
    private String postTitle;
    private int idCreator;
    private String firstNameCreator;
    private String lastNameCreator;

    public MyPostRequest() {
        // Empty constructor
        databaseConnectionDAO = new DatabaseConnection();
    }

    public String getFirstNameRequestee() {
        return firstNameRequestee;
    }

    public String getLastNameRequestee() {
        return lastNameRequestee;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setFirstNameRequestee(String firstNameRequestee) {
        this.firstNameRequestee = firstNameRequestee;
    }

    public void setLastNameRequestee(String lastNameRequestee) {
        this.lastNameRequestee = lastNameRequestee;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }

    public void setFirstNameCreator(String firstNameCreator) {
        this.firstNameCreator = firstNameCreator;
    }

    public void setLastNameCreator(String lastNameCreator) {
        this.lastNameCreator = lastNameCreator;
    }

    public Statement getConnection() throws Exception {
        connection = databaseConnectionDAO.getDatabaseConnection();
        statement = connection.createStatement();

        return statement;
    }

    public ResultSet resultExecuteQuery(String query) throws Exception {
//        posts.add(new Post(1, new User(), "title 1", 5, "source 1", "destination 1", new Date(), new Date(), 15, 25, "description 1", false));
        statement = getConnection();
        resultSet = statement.executeQuery(query);

        return resultSet;
    }

    public List<MyPostRequest> resultMyPostRequests(String query) throws Exception {
        resultSet = resultExecuteQuery(query);

        List<MyPostRequest> results = new ArrayList<>();
        while (resultSet.next()) {
            MyPostRequest myPostRequest = new MyPostRequest();
            myPostRequest.setFirstNameRequestee(resultSet.getString("firstNameRequestee"));
            myPostRequest.setLastNameRequestee(resultSet.getString("lastNameRequestee"));
            myPostRequest.setPostTitle(resultSet.getString("postTitle"));
            myPostRequest.setFirstNameCreator(resultSet.getString("firstNameCreator"));
            myPostRequest.setLastNameCreator(resultSet.getString("lastNameCreator"));

            logger.info(resultSet.getString("firstNameRequestee"));
            results.add(myPostRequest);

//            myPostRequest.id = 1;
//            myPostRequest.userName = "Harshil";
//            myPostRequest.postId = 3;
//            myPostRequest.status = PostRequestStatus.PENDING;
//            results.add(myPostRequest);
//
//            MyPostRequest myPostRequest2 = new MyPostRequest();
//            myPostRequest2.id = 4;
//            myPostRequest2.userName = "Harshil 2";
//            myPostRequest2.postId = 5;
//            myPostRequest2.status = PostRequestStatus.PENDING;
//            results.add(myPostRequest2);
        }
        connection.close();
        return results;
    }
}


