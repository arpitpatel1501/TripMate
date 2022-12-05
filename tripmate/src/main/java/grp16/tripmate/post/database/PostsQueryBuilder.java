package grp16.tripmate.post.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

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
    public String getAllPosts() {
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
                "FROM `Post` where `is_hidden` != 1 ";
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
        String query =  "SELECT `id`,\n" +
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
                "FROM `Post` where `id` = " + postid;
        logger.info(query);
        return query;
    }
}


