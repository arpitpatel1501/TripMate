package grp16.tripmate.post.database;

public class GetAllPostsQueryBuilder implements GetAllPostsQueryBuilderDAO {
    private static GetAllPostsQueryBuilder instance;


    private GetAllPostsQueryBuilder() {
        //Required empty constructor
    }

    public static GetAllPostsQueryBuilder getInstance() {
        if (instance == null) {
            instance = new GetAllPostsQueryBuilder();
        }
        return instance;
    }


    @Override
    public String getPostsByUserId(int userid) {
        /*
        posts.add(new Post(1, new User(), "title 1", 5, "source 1", "destination 1", new Date(), new Date(), 15, 25, "description 1", false));
         * */
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
    public String getAllPostsNotCraetedByUserId(int userid) {
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
                "FROM `Post` where `is_hidden` != 1 and `created_by` != " + userid;
    }

    @Override
    public String getPostByPostId(int postid) {
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
                "FROM `Post` where `id` = " + postid;
    }
}


