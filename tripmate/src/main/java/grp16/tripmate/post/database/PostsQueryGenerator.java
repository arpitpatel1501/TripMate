package grp16.tripmate.post.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.IPost;
import grp16.tripmate.post.model.Post;

public class PostsQueryGenerator implements IPostsQueryGenerator {
    private final ILogger logger = new MyLoggerAdapter(this);
    private static PostsQueryGenerator instance;

    private PostsQueryGenerator() {

    }

    public static PostsQueryGenerator getInstance() {
        if (instance == null) {
            instance = new PostsQueryGenerator();
        }
        return instance;
    }

    @Override
    public String getCreatePostQuery(IPost postToCreate) {
        Post post = (Post) postToCreate;
        String query = "INSERT INTO " + PostDbColumnNames.TABLE_NAME + "( \n" +
                PostDbColumnNames.OWNER + "," +
                PostDbColumnNames.TITLE + "," +
                PostDbColumnNames.SOURCE + "," +
                PostDbColumnNames.DESTINATION + "," +
                PostDbColumnNames.STARTDATE + "," +
                PostDbColumnNames.ENDDATE + "," +
                PostDbColumnNames.MINAGE + "," +
                PostDbColumnNames.MAXAGE + "," +
                PostDbColumnNames.CAPACITY + "," +
                PostDbColumnNames.DESCRIPTION + ") \n" +
                "VALUES (\n" +
                post.getOwner_id() + ",\n" +
                "'" + post.getTitle() + "',\n" +
                "'" + post.getSource() + "',\n" +
                "'" + post.getDestination() + "',\n" +
                "'" + post.getStartDate() + "',\n" +
                "'" + post.getEndDate() + "',\n" +
                post.getMinAge() + ",\n" +
                post.getMaxAge() + ",\n" +
                post.getCapacity() + ",\n" +
                "'" + post.getDescription() + "' );";
        logger.info(query);
        return query;
    }

    @Override
    public String getAllPosts(int loggedInUser) {
        String query = "SELECT \n" +
                PostDbColumnNames.ID + ", \n" +
                PostDbColumnNames.OWNER + ", \n" +
                PostDbColumnNames.TITLE + ", \n" +
                PostDbColumnNames.SOURCE + ", \n" +
                PostDbColumnNames.DESTINATION + ", \n" +
                PostDbColumnNames.STARTDATE + ", \n" +
                PostDbColumnNames.ENDDATE + ", \n" +
                PostDbColumnNames.MINAGE + ", \n" +
                PostDbColumnNames.MAXAGE + ", \n" +
                PostDbColumnNames.CAPACITY + ", \n" +
                PostDbColumnNames.ISHIDDEN + ", \n" +
                PostDbColumnNames.DESCRIPTION + " \n" +
                "FROM " + PostDbColumnNames.TABLE_NAME + "\n" +
                "WHERE " + PostDbColumnNames.ISHIDDEN + " != 1 " +
                "AND " + PostDbColumnNames.OWNER + " != " + loggedInUser;
        logger.info(query);
        return query;
    }

    @Override
    public String getPostsByUserId(int userId) {
        String query = "SELECT \n" +
                PostDbColumnNames.ID + ", \n" +
                PostDbColumnNames.OWNER + ", \n" +
                PostDbColumnNames.TITLE + ", \n" +
                PostDbColumnNames.SOURCE + ", \n" +
                PostDbColumnNames.DESTINATION + ", \n" +
                PostDbColumnNames.STARTDATE + ", \n" +
                PostDbColumnNames.ENDDATE + ", \n" +
                PostDbColumnNames.MINAGE + ", \n" +
                PostDbColumnNames.MAXAGE + ", \n" +
                PostDbColumnNames.CAPACITY + ", \n" +
                PostDbColumnNames.ISHIDDEN + ", \n" +
                PostDbColumnNames.DESCRIPTION + " \n" +
                "FROM " + PostDbColumnNames.TABLE_NAME + "\n" +
                "WHERE " + PostDbColumnNames.ISHIDDEN + " != 1 AND \n" +
                PostDbColumnNames.OWNER + " = " + userId;
        logger.info(query);
        return query;
    }


    @Override
    public String getPostByPostId(int postId) {
        String query = "SELECT \n" +
                PostDbColumnNames.ID + ", \n" +
                PostDbColumnNames.OWNER + ", \n" +
                PostDbColumnNames.TITLE + ", \n" +
                PostDbColumnNames.SOURCE + ", \n" +
                PostDbColumnNames.DESTINATION + ", \n" +
                PostDbColumnNames.STARTDATE + ", \n" +
                PostDbColumnNames.ENDDATE + ", \n" +
                PostDbColumnNames.MINAGE + ", \n" +
                PostDbColumnNames.MAXAGE + ", \n" +
                PostDbColumnNames.CAPACITY + ", \n" +
                PostDbColumnNames.ISHIDDEN + ", \n" +
                PostDbColumnNames.DESCRIPTION + " \n" +
                "FROM " + PostDbColumnNames.TABLE_NAME + "\n" +
                "WHERE " + PostDbColumnNames.ID + " = " + postId;
        logger.info(query);
        return query;
    }

    @Override
    public String getUpdatePostQuery(IPost postToUpdate) {
        Post post = (Post) postToUpdate;
        String query = "UPDATE " + PostDbColumnNames.TABLE_NAME + "    SET " +
                PostDbColumnNames.TITLE + "='" + post.getTitle() + "',\n" +
                PostDbColumnNames.SOURCE + "='" + post.getSource() + "',\n" +
                PostDbColumnNames.DESTINATION + "='" + post.getDestination() + "',\n" +
                PostDbColumnNames.STARTDATE + "='" + post.getStartDate() + "',\n" +
                PostDbColumnNames.ENDDATE + "='" + post.getEndDate() + "',\n" +
                PostDbColumnNames.MINAGE + "=" + post.getMinAge() + ",\n" +
                PostDbColumnNames.MAXAGE + "=" + post.getMaxAge() + ",\n" +
                PostDbColumnNames.CAPACITY + "=" + post.getCapacity() + ",\n" +
                PostDbColumnNames.DESCRIPTION + "='" + post.getDescription() + "'\n" +
                "    WHERE " + PostDbColumnNames.ID + "=" + post.getId();
        logger.info(query);
        return query;
    }

    @Override
    public String deletePostQuery(int postId) {
        String query = "DELETE FROM " +
                PostDbColumnNames.TABLE_NAME +
                " WHERE " + PostDbColumnNames.ID + " = " + postId;
        logger.info(query);
        return query;
    }

    @Override
    public String hidePostQuery(int postId) {
        String query = "UPDATE " + PostDbColumnNames.TABLE_NAME + "\n" +
                "    SET " + PostDbColumnNames.ISHIDDEN + "=" + true + "\n" +
                "    WHERE " + PostDbColumnNames.ID + "=" + postId;
        logger.info(query);
        return query;
    }

    @Override
    public String getFeedbackPosts() {
        String query = "SELECT " + PostDbColumnNames.ID + "," +
                PostDbColumnNames.TITLE + "," +
                PostDbColumnNames.SOURCE + "," +
                PostDbColumnNames.DESTINATION + "," +
                PostDbColumnNames.STARTDATE + "," +
                PostDbColumnNames.ENDDATE + "," +
                PostDbColumnNames.MINAGE + "," +
                PostDbColumnNames.MAXAGE + "," +
                PostDbColumnNames.ISHIDDEN + "," +
                PostDbColumnNames.CAPACITY + "," +
                PostDbColumnNames.OWNER + "," +
                PostDbColumnNames.DESCRIPTION +
                "FROM " + PostDbColumnNames.TABLE_NAME +
                "where " + PostDbColumnNames.ENDDATE + "< now()";
        logger.info(query);
        return query;
    }
}


