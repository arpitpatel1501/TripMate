package grp16.tripmate.postrequest.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.PostDbColumnNames;
import grp16.tripmate.postrequest.model.PostRequestStatus;
import grp16.tripmate.user.database.UserDbColumnNames;

public class MyPostRequestQueryBuilder implements IMyPostRequestQueryBuilder {

    private final ILogger logger = new MyLoggerAdapter(this);

    private static IMyPostRequestQueryBuilder instance;
    private MyPostRequestQueryBuilder() {

    }

    public static IMyPostRequestQueryBuilder getInstance() {
        if (instance == null) {
            instance = new MyPostRequestQueryBuilder();
        }
        return instance;
    }

    @Override
    public String getMyPostRequests(int loginUserId) {
        String query = "SELECT pr." + MyPostRequestDbColumnNames.ID + " as requestId, u." + UserDbColumnNames.FIRSTNAME + " as firstNameRequester, u." + UserDbColumnNames.LASTNAME + " as lastNameRequester, u."+ UserDbColumnNames.ID + " as idRequester, p."+ PostDbColumnNames.TITLE + " as postTitle, " +
                "p."+ PostDbColumnNames.OWNER + " as idCreator, post_owner." + UserDbColumnNames.FIRSTNAME + " as firstNameCreator, post_owner." + UserDbColumnNames.LASTNAME + " lastNameCreator \n" +
                "FROM " + MyPostRequestDbColumnNames.TABLE_NAME +" pr\n" +
                "JOIN " + PostDbColumnNames.TABLE_NAME + " p on pr." + MyPostRequestDbColumnNames.POST_ID + " = p."+ PostDbColumnNames.ID +"\n" +
                "JOIN " + UserDbColumnNames.TABLE_NAME + " u on pr." + MyPostRequestDbColumnNames.REQUEST_OWNER + " = u." + UserDbColumnNames.ID + "\n" +
                "JOIN " + UserDbColumnNames.TABLE_NAME + " post_owner on post_owner." + UserDbColumnNames.ID + " = p." + PostDbColumnNames.OWNER + "\n" +
                "WHERE pr." + MyPostRequestDbColumnNames.STATUS + " = \"PENDING\" and p." + PostDbColumnNames.OWNER + " = " + loginUserId + ";";

        logger.info(query);
        return query;
    }


    @Override
    public String createJoinRequest(int post_id, int user_id) {
        String query = "INSERT INTO "+MyPostRequestDbColumnNames.TABLE_NAME+"\n" +
                "(" +
                MyPostRequestDbColumnNames.STATUS + ",\n" +
                MyPostRequestDbColumnNames.POST_ID + ",\n" +
                MyPostRequestDbColumnNames.REQUEST_OWNER + ")\n" +
                "VALUES\n" + "(" +
                "'PENDING',\n" +
                post_id + "," +
                user_id + ");\n";

        logger.info(query);
        return query;
    }

    @Override
    public String getPostOwnerDetails(int post_id) {
        String query = "SELECT p." + PostDbColumnNames.TITLE + " as postTitle, u." + UserDbColumnNames.USERNAME + " as postOwnerEmail, u."+ UserDbColumnNames.FIRSTNAME +" as postOwnerFirstName, u." + UserDbColumnNames.LASTNAME + " as postOwnerLastName from " + PostDbColumnNames.TABLE_NAME + " p \n" +
                "JOIN "+ UserDbColumnNames.TABLE_NAME +" u on p." + PostDbColumnNames.OWNER + " = u."+ UserDbColumnNames.ID +"\n" +
                "WHERE p."+PostDbColumnNames.ID+" = "+ post_id +";";

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
    public String getPostRequesterDetails (int request_id) {
        String query = "SELECT * from " + MyPostRequestDbColumnNames.TABLE_NAME + " pr \n" +
                "JOIN " + UserDbColumnNames.TABLE_NAME + " u on pr." + MyPostRequestDbColumnNames.REQUEST_OWNER + " = u."+UserDbColumnNames.ID+" \n" +
                "JOIN "+PostDbColumnNames.TABLE_NAME+" p on pr."+MyPostRequestDbColumnNames.POST_ID+" = p."+ PostDbColumnNames.ID +" \n" +
                "WHERE pr."+ MyPostRequestDbColumnNames.ID +" = " + request_id + ";";

        logger.info(query);
        return query;
    }
}