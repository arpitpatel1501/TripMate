package grp16.tripmate.post.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.PostDbColumnNames;

public class PostsQueryBuilder implements IPostsQueryBuilder {
    private final ILogger logger = new MyLoggerAdapter(this);
    private static PostsQueryBuilder instance;


    private PostsQueryBuilder() {
        //Required empty constructor
    }

    public static PostsQueryBuilder getInstance() {
        if (instance == null) {
            instance = new PostsQueryBuilder();
        }
        return instance;
    }

    @Override
    public String getCreatePostQuery(Post post) {
        String query = "INSERT INTO " + PostDbColumnNames.TABLENAME + "( \n" +
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
                "VALUES \n" +
                "( \n" +
                post.getOwner().getId() + ",\n" +
                "'" + post.getTitle() + "',\n" +
                "'" + post.getSource() + "',\n" +
                "'" + post.getDestination() + "',\n" +
                "'" + post.getStartDate() + "',\n" +
                "'" + post.getEndDate() + "',\n" +
                post.getMinAge() + ",\n" +
                post.getMaxAge() + ",\n" +
                post.getCapacity() + ",\n" +
                "'" + post.getDescription() + "'\n );";
        logger.info(query);
        return query;
    }

    @Override
    public String getAllPosts() {
        String query = "SELECT `Post`.`id`,\n" +
                "    `Post`.`title`,\n" +
                "    `Post`.`source_location`,\n" +
                "    `Post`.`destination_location`,\n" +
                "    `Post`.`start_ts`,\n" +
                "    `Post`.`end_ts`,\n" +
                "    `Post`.`min_age`,\n" +
                "    `Post`.`max_age`,\n" +
                "    `Post`.`is_hidden`,\n" +
                "    `Post`.`capacity`,\n" +
                "    `Post`.`created_by`,\n" +
                "    `Post`.`description`\n" +
                "FROM `CSCI5308_16_DEVINT`.`Post`" +
                " where " + PostDbColumnNames.ISHIDDEN + " != 1";

        logger.info(query);

        return query;
    }

    @Override
    public String getPostsByUserId(int userid) {
        return "SELECT `id`,\n" +
                "    `title`,\n" +
                "    `source_location`,\n" +
                "    `destination_location`,\n" +
                "    `start_ts`,\n" +
                "    `end_ts`,\n" +
                "    `min_age`,\n" +
                "    `max_age`,\n" +
                "    `capacity`,\n" +
                "    `created_by`,\n" +
                "    `description`\n" +
                "FROM `Post` where `is_hidden` != 1 and `created_by` = " + userid;
    }


    @Override
    public String getPostByPostId(int postid) {
        String query = "SELECT `id`,\n" +
                "    `title`,\n" +
                "    `source_location`,\n" +
                "    `destination_location`,\n" +
                "    `start_ts`,\n" +
                "    `end_ts`,\n" +
                "    `min_age`,\n" +
                "    `max_age`,\n" +
                "    `capacity`,\n" +
                "    `created_by`,\n" +
                "    `description` ,\n " +
                PostDbColumnNames.ISHIDDEN +
                " FROM `Post` where `id` = " + postid;
        logger.info(query);
        return query;
    }

    @Override
    public String getUpdatePostQuery(Post post) {
        String query = "UPDATE " + PostDbColumnNames.TABLENAME + "    SET " +
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
                "FROM " + PostDbColumnNames.TABLENAME +
                "where " + PostDbColumnNames.ENDDATE + "< now()";
        logger.info(query);
        return query;
    }

    @Override
    public String deletePostQuery(int postid) {
        String query = "DELETE FROM " + PostDbColumnNames.TABLENAME + " WHERE " + PostDbColumnNames.ID + " = " + postid;
        logger.info(query);
        return query;
    }

    @Override
    public String hidePostQuery(int postid) {
        String query = "UPDATE " + PostDbColumnNames.TABLENAME + "\n" +
                "    SET " + PostDbColumnNames.ISHIDDEN + "=" + true + "\n" +
                "    WHERE " + PostDbColumnNames.ID + "=" + postid;
        logger.info(query);
        return query;
    }
}


