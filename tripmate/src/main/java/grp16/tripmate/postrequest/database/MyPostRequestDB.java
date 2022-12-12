package grp16.tripmate.postrequest.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.PostDbColumnNames;
import grp16.tripmate.postrequest.model.PostRequestStatus;
import grp16.tripmate.user.database.UserDbColumnNames;

public class MyPostRequestDB implements IMyPostRequestDB {
    private final ILogger logger = new MyLoggerAdapter(this);

    private static MyPostRequestDB instance;

    public static MyPostRequestDB getInstance() {
        if (instance == null) {
            instance = new MyPostRequestDB();
        }
        return instance;
    }

    @Override
    public String getPostRequestByUserId(int loginUserId) {
        String query = "SELECT pr.id as requestId, u.firstname as firstNameRequestee, u.lastname as lastNameRequestee, u.id as idRequestee, p.title as postTitle, " +
                "p.created_by as idCreator, post_owner.firstname as firstNameCreator, post_owner.lastname lastNameCreator \n" +
                "FROM PostRequest pr\n" +
                "JOIN Post p on pr.Post_id = p.id\n" +
                "JOIN User u on pr.request_owner = u.id\n" +
                "JOIN User post_owner on post_owner.id = p.created_by\n" +
                "WHERE pr.status = \"pending\" and p.created_by = " + loginUserId + ";";

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
                "'pending',\n" +
                post_id + "," +
                user_id + ");\n";

        logger.info(query);
        return query;
    }

    @Override
    public String updateRequestStatus(int requestId, PostRequestStatus postRequestStatus) {
        String query = "update " +
                PostRequestDbColumnNames.TABLE_NAME + " set " +
                PostRequestDbColumnNames.STATUS + " = '" + postRequestStatus.toString() + "'" +
                " where " +
                PostRequestDbColumnNames.ID + " = " + requestId + ";";
        logger.info(query);
        return query;
    }
}
