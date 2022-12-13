package grp16.tripmate.postrequest.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.postrequest.database.IMyPostRequestDB;
import grp16.tripmate.postrequest.model.factory.MyPostRequestFactory;
import grp16.tripmate.user.database.IUserDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private String emailRequestee;
    private String postTitle;
    private int idCreator;
    private String firstNameCreator;
    private String lastNameCreator;
    private String emailCreator;

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

    public String getEmailRequestee() {
        return emailRequestee;
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

    public String getEmailCreator() {
        return emailCreator;
    }

    public String getFirstNameCreator() {
        return firstNameCreator;
    }

    public String getLastNameCreator() {
        return lastNameCreator;
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

    public void setEmailRequestee(String emailRequestee) {
        this.emailRequestee = emailRequestee;
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

    public void setEmailCreator(String emailCreator) {
        this.emailCreator = emailCreator;
    }

    @Override
    public List<IMyPostRequest> getMyPostRequests(IMyPostRequestDB myPostRequestDB) throws Exception {
        List<IMyPostRequest> myPostRequestList = listToMyPostRequest(myPostRequestDB.getMyPostRequests());
        return myPostRequestList;
    }

    @Override
    public boolean createJoinRequest(IMyPostRequestDB myPostRequestDB, int postId) throws Exception {
        return myPostRequestDB.createJoinRequest(postId);
    }

    @Override
    public IMyPostRequest getPostOwnerDetails(IMyPostRequestDB myPostRequestDB, int postId) throws Exception {
        List<IMyPostRequest> myPostRequestPostOwnerList = listToMyPostRequestPostOwner(myPostRequestDB.getPostOwnerDetails(postId));
        return myPostRequestPostOwnerList.get(0);
    }

    @Override
    public boolean updateRequest(IMyPostRequestDB myPostRequestDB, int requestId, PostRequestStatus postRequestStatus) {
        return myPostRequestDB.updateRequest(requestId, postRequestStatus);
    }

    @Override
    public IMyPostRequest getPostRequesteeDetails(IMyPostRequestDB myPostRequestDB, int requestId) throws Exception {
        List<IMyPostRequest> postRequesteeDetails = listToPostRequesteeDetails(myPostRequestDB.getPostRequesteeDetails(requestId));
        return postRequesteeDetails.get(0);
    }

    public List<IMyPostRequest> listToMyPostRequest(List<Map<String, Object>> results) throws Exception {
        List<IMyPostRequest> myPostRequestList = new ArrayList<>();
        for (Map<String, Object> result : results) {
            IMyPostRequest myPostRequest = MyPostRequestFactory.getInstance().createMyPostRequest();

            myPostRequest.setIdRequest((Integer) result.get("requestId"));
            myPostRequest.setFirstNameRequestee((String) result.get("firstNameRequestee"));
            myPostRequest.setLastNameRequestee((String) result.get("lastNameRequestee"));
            myPostRequest.setIdRequestee((Integer) result.get("idRequestee"));
            myPostRequest.setPostTitle((String) result.get("postTitle"));
            myPostRequest.setFirstNameCreator((String) result.get("firstNameCreator"));
            myPostRequest.setLastNameCreator((String) result.get("lastNameCreator"));

            myPostRequestList.add(myPostRequest);
        }
        return myPostRequestList;
    }

    public List<IMyPostRequest> listToMyPostRequestPostOwner(List<Map<String, Object>> results) throws Exception {
        List<IMyPostRequest> myPostRequestPostOwnerList = new ArrayList<>();
        for (Map<String, Object> result : results) {
            IMyPostRequest myPostRequest = MyPostRequestFactory.getInstance().createMyPostRequest();

            myPostRequest.setPostTitle((String) result.get("postTitle"));
            myPostRequest.setEmailCreator((String) result.get("postOwnerEmail"));
            myPostRequest.setFirstNameCreator((String) result.get("postOwnerFirstName"));
            myPostRequest.setLastNameCreator((String) result.get("postOwnerLastName"));

            myPostRequestPostOwnerList.add(myPostRequest);
        }
        return myPostRequestPostOwnerList;
    }

    public List<IMyPostRequest> listToPostRequesteeDetails(List<Map<String, Object>> results) throws Exception {
        List<IMyPostRequest> postRequesteeDetails = new ArrayList<>();
        for (Map<String, Object> result : results) {
            IMyPostRequest myPostRequest = MyPostRequestFactory.getInstance().createMyPostRequest();

            myPostRequest.setEmailRequestee((String) result.get("email"));
            myPostRequest.setPostTitle((String) result.get("title"));

            postRequesteeDetails.add(myPostRequest);
        }
        return postRequesteeDetails;
    }

//    public Statement getConnection() throws Exception {
//        databaseConnection = new DatabaseConnection();
//        connection = databaseConnection.getDatabaseConnection();
//        statement = connection.createStatement();
//
//        return statement;
//    }
//
//    public ResultSet resultExecuteQuery(String query) throws Exception {
//        statement = getConnection();
//        resultSet = statement.executeQuery(query);
//
//        return resultSet;
//    }
//
//    public boolean executeQuery(String query) throws Exception {
//        statement = getConnection();
//        boolean returnResult = statement.execute(query);
//        return returnResult;
//    }
//
////    public List<IMyPostRequest> resultMyPostRequests(String query) throws Exception {
////
////    }
//
//    public IMyPostRequest resultPostOwnerDetails(String query) throws Exception {
//        resultSet = resultExecuteQuery(query);
//
//        IMyPostRequest myPostRequest = null;
//        while (resultSet.next()) {
//            myPostRequest = MyPostRequestFactory.getInstance().createMyPostRequest();
//
//            myPostRequest.setPostTitle(resultSet.getString("postTitle"));
//            myPostRequest.setEmailCreator(resultSet.getString("postOwnerEmail"));
//            myPostRequest.setFirstNameCreator(resultSet.getString("postOwnerFirstName"));
//            myPostRequest.setLastNameCreator(resultSet.getString("postOwnerLastName"));
//        }
//        connection.close();
//        return myPostRequest;
//    }
//
//    @Override
//    public IMyPostRequest resultPostRequesteeDetails(String query) throws Exception {
//        resultSet = resultExecuteQuery(query);
//
//        IMyPostRequest myPostRequest = null;
//        while (resultSet.next()) {
//            myPostRequest = MyPostRequestFactory.getInstance().createMyPostRequest();
//
//            myPostRequest.setEmailRequestee(resultSet.getString("email"));
//            myPostRequest.setPostTitle(resultSet.getString("title"));
//
//        }
//        connection.close();
//        return myPostRequest;
//    }
//    @Override
//    public boolean updateRequest(String query) throws Exception {
//        statement = getConnection();
//        int rowUpdate = statement.executeUpdate(query);
//        boolean isRowUpdated;
//        if (rowUpdate == 1) {
//            isRowUpdated = true;
//        }
//        else {
//            isRowUpdated = false;
//        }
//        connection.close();
//        return isRowUpdated;
//    }


}


