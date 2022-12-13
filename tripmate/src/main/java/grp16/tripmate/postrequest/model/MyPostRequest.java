package grp16.tripmate.postrequest.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyPostRequest implements IMyPostRequest {

    private final ILogger logger = new MyLoggerAdapter(this);
    private IDatabaseConnection databaseConnection;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int idRequest;
    private PostRequestStatus status;
    private int postId;
    private String firstNameRequestee;
    private String lastNameRequestee;
    private int idRequestee;
    private String postTitle;
    private int idCreator;
    private String firstNameCreator;
    private String lastNameCreator;

    public MyPostRequest() {
    }

    public int getIdRequest() {
        return this.idRequest;
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

    public int getIdRequestee() {
        return idRequestee;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
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
    public void setIdRequestee(int idRequestee) {
        this.idRequestee = idRequestee;
    }

    public void setFirstNameCreator(String firstNameCreator) {
        this.firstNameCreator = firstNameCreator;
    }

    public void setLastNameCreator(String lastNameCreator) {
        this.lastNameCreator = lastNameCreator;
    }

    public Statement getConnection() throws Exception {
        databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getDatabaseConnection();
        statement = connection.createStatement();

        return statement;
    }

    public ResultSet resultExecuteQuery(String query) throws Exception {
        statement = getConnection();
        resultSet = statement.executeQuery(query);

        return resultSet;
    }

    public boolean executeQuery(String query) throws Exception {
        statement = getConnection();
        boolean returnResult = statement.execute(query);
        return returnResult;
    }

    public List<IMyPostRequest> resultMyPostRequests(String query) throws Exception {
        resultSet = resultExecuteQuery(query);

        List<IMyPostRequest> results = new ArrayList<>();
        while (resultSet.next()) {
            IMyPostRequest myPostRequest = MyPostRequestFactory.getInstance().createMyPostRequest();

            myPostRequest.setIdRequest(resultSet.getInt("requestId"));
            myPostRequest.setFirstNameRequestee(resultSet.getString("firstNameRequestee"));
            myPostRequest.setLastNameRequestee(resultSet.getString("lastNameRequestee"));
            myPostRequest.setIdRequestee(resultSet.getInt("idRequestee"));
            myPostRequest.setPostTitle(resultSet.getString("postTitle"));
            myPostRequest.setFirstNameCreator(resultSet.getString("firstNameCreator"));
            myPostRequest.setLastNameCreator(resultSet.getString("lastNameCreator"));

            logger.info(resultSet.getString("firstNameRequestee"));
            results.add(myPostRequest);
        }
        connection.close();
        return results;
    }

    @Override
    public boolean updateRequest(String query) throws Exception {
        statement = getConnection();
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


