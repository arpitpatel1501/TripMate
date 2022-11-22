package grp16.tripmate.post.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.DatabaseConnectionDAO;
import grp16.tripmate.post.database.GetPostRequestQueryBuilder;
import grp16.tripmate.post.database.IGetPostRequestQueryBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyPostRequest {
    private int id;
    private PostRequestStatus status;
    private int postId;
    private String userName;
    private DatabaseConnectionDAO databaseConnectionDAO;
    private Connection connection;
    private Statement statement;

    private ResultSet resultSet;

    public int getId() {
        return id;
    }

    public PostRequestStatus getStatus() {
        return status;
    }

    public int getPostId() {
        return postId;
    }

    public String getUserName() {
        return userName;
    }

    public MyPostRequest(int id, PostRequestStatus status, int postId, String userName) {
        this.id = id;
        this.status = status;
        this.postId = postId;
        this.userName = userName;
    }

    public MyPostRequest() {
        // Empty constructor
        databaseConnectionDAO = new DatabaseConnection();
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
        connection.close();
        return resultSet;
    }

    public List<MyPostRequest> resultMyPostRequests(String query) throws Exception {
        resultSet = resultExecuteQuery(query);

        List<MyPostRequest> results = new ArrayList<>();
//        while (rs.next()) {
            MyPostRequest myPostRequest = new MyPostRequest();
            myPostRequest.id = 1;
            myPostRequest.userName = "Harshil";
            myPostRequest.postId = 3;
            myPostRequest.status = PostRequestStatus.PENDING;
            results.add(myPostRequest);

            MyPostRequest myPostRequest2 = new MyPostRequest();
            myPostRequest2.id = 4;
            myPostRequest2.userName = "Harshil 2";
            myPostRequest2.postId = 5;
            myPostRequest2.status = PostRequestStatus.PENDING;
            results.add(myPostRequest2);
//        }
        return results;
    }
}


