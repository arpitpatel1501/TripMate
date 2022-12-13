package grp16.tripmate.postrequest.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.postrequest.model.PostRequestStatus;

public class MyPostRequestQueryBuilder implements IMyPostRequestQueryBuilder {

    private final ILogger logger = new MyLoggerAdapter(this);

    private static IMyPostRequestQueryBuilder instance;

    public static IMyPostRequestQueryBuilder getInstance() {
        if (instance == null) {
            instance = new MyPostRequestQueryBuilder();
        }
        return instance;
    }

    @Override
    public String getMyPostRequests(int loginUserId) {
        String query = "SELECT pr.id as requestId, u.firstname as firstNameRequestee, u.lastname as lastNameRequestee, u.id as idRequestee, p.title as postTitle, " +
                "p.created_by as idCreator, post_owner.firstname as firstNameCreator, post_owner.lastname lastNameCreator \n" +
                "FROM PostRequest pr\n" +
                "JOIN Post p on pr.Post_id = p.id\n" +
                "JOIN User u on pr.request_owner = u.id\n" +
                "JOIN User post_owner on post_owner.id = p.created_by\n" +
                "WHERE pr.status = \"PENDING\" and p.created_by = " + loginUserId + ";";

        logger.info(query);
        return query;
    }


    @Override
    public String createJoinRequest(int post_id, int user_id) {
        String query = "INSERT INTO `PostRequest`\n" +
                "(" +
                "`status`,\n" +
                "`Post_id`,\n" +
                "`request_owner`)\n" +
                "VALUES\n" + "(" +
                "'PENDING',\n" +
                post_id + "," +
                user_id + ");\n";

        logger.info(query);
        return query;
    }

    @Override
    public String getPostOwnerDetails(int post_id) {
        String query = "SELECT p.title as postTitle, u.email as postOwnerEmail, u.firstname as postOwnerFirstName, u.lastname as postOwnerLastName from Post p \n" +
                "JOIN User u on p.created_by = u.id\n" +
                "WHERE p.id = "+ post_id +";";
        logger.info(query);
        return query;
    }

    @Override
    public String updateRequestStatus(int requestId, PostRequestStatus postRequestStatus) {
        String query = "update " +
                MyPostRequestDbColumnNames.TABLE_NAME + " set " +
                MyPostRequestDbColumnNames.STATUS + " = '" + postRequestStatus.toString() + "'" +
                " where " +
                MyPostRequestDbColumnNames.ID + " = " + requestId + ";";
        logger.info(query);
        return query;
    }

    @Override
    public String getPostRequesteeDetails (int request_id) {
        String query = "SELECT * from PostRequest pr \n" +
                "JOIN User u on pr.request_owner = u.id \n" +
                "JOIN Post p on pr.Post_id = p.id \n" +
                "WHERE pr.id = " + request_id + ";";
        logger.info(query);
        return query;
    }


}
