package grp16.tripmate.postrequest.database;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.PostDbColumnNames;
import grp16.tripmate.postrequest.model.IMyPostRequest;
import grp16.tripmate.postrequest.model.PostRequestStatus;
import grp16.tripmate.postrequest.model.factory.MyPostRequestFactory;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.database.IUserQueryGenerator;
import grp16.tripmate.user.database.UserDbColumnNames;
import grp16.tripmate.user.model.User;

import java.util.*;

public class MyPostRequestDB implements IMyPostRequestDB {

    private final ILogger logger = new MyLoggerAdapter(this);
    private final IMyPostRequestQueryBuilder queryGenerator;
    private final IDatabaseExecutor databaseExecution;

    public MyPostRequestDB(IMyPostRequestQueryBuilder queryGenerator, IDatabaseExecutor databaseExecution) {
        this.queryGenerator = queryGenerator;
        this.databaseExecution = databaseExecution;
    }

    @Override
    public List<Map<String, Object>> getMyPostRequests() throws Exception {

        String query = queryGenerator.getMyPostRequests((Integer) SessionManager.getInstance().getValue(UserDbColumnNames.ID));
        logger.info(query);
//        List<IMyPostRequest> myPostRequestList = listToMyPostRequest(databaseExecution.executeSelectQuery(query));
        List<Map<String, Object>> result = databaseExecution.executeSelectQuery(query);
        return result;
    }

    @Override
    public boolean createJoinRequest(int postId) throws Exception {
        String query = queryGenerator.createJoinRequest(postId, (Integer) SessionManager.getInstance().getValue(UserDbColumnNames.ID));
        logger.info(query);
        boolean isCreateJoinRequest = databaseExecution.executeUpdateQuery(query);
        return isCreateJoinRequest;
    }

    @Override
    public List<Map<String, Object>> getPostOwnerDetails(int postId) {
        String query = queryGenerator.getPostOwnerDetails(postId);
        logger.info(query);
        List<Map<String, Object>> result = databaseExecution.executeSelectQuery(query);
        return result;
    }

    @Override
    public List<Map<String, Object>> getPostRequesteeDetails(int requestId) {
        String query = queryGenerator.getPostRequesteeDetails(requestId);
        logger.info(query);
        return databaseExecution.executeSelectQuery(query);
    }

    @Override
    public boolean updateRequest(int requestId, PostRequestStatus postRequestStatus) {
        String query = queryGenerator.updateRequestStatus(requestId, postRequestStatus);
        logger.info(query);
        return databaseExecution.executeUpdateQuery(query);
    }

}
