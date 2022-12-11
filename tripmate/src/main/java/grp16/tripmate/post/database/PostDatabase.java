package grp16.tripmate.post.database;

import grp16.tripmate.db.execute.DatabaseExecution;
import grp16.tripmate.db.execute.IDatabaseExecution;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.post.model.*;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.UserDbColumnNames;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PostDatabase implements IPostDatabase {
    private final ILogger logger = new MyLoggerAdapter(this);
    private final IPostsQueryGenerator queryGenerator;
    private final IDatabaseExecution databaseExecution;

    public PostDatabase() {
        queryGenerator = PostsQueryGenerator.getInstance();
        databaseExecution = new DatabaseExecution();
    }

    @Override
    public boolean createPost(Post post) throws Exception {
        post.setOwner_id((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
        String query = queryGenerator.getCreatePostQuery(post);
        return databaseExecution.executeInsertQuery(query);
    }

    @Override
    public List<Post> getPostsByUserId(int userid) throws Exception {
        String query = queryGenerator.getPostsByUserId(userid);
        return listToPosts(databaseExecution.executeSelectQuery(query));
    }

    @Override
    public List<Post> getAllPosts() throws Exception {
        String query = queryGenerator.getAllPosts();
        return listToPosts(databaseExecution.executeSelectQuery(query));
    }

    @Override
    public Post getPostByPostId(int post_id) throws Exception {
        String query = queryGenerator.getPostByPostId(post_id);
        List<Post> posts = listToPosts(databaseExecution.executeSelectQuery(query));
        if (posts != null) {
            return posts.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean updatePost(Post post) {
        String query = queryGenerator.getUpdatePostQuery(post);
        return databaseExecution.executeUpdateQuery(query);
    }

    @Override
    public boolean deletePost(int post_id) {
        PostFactory.getInstance().getFeedbackDatabase().deleteFeedbackByPostId(post_id);
        String query = queryGenerator.deletePostQuery(post_id);
        return databaseExecution.executeDeleteQuery(query);
    }

    @Override
    public boolean hidePost(int post_id) {
        String query = queryGenerator.hidePostQuery(post_id);
        return databaseExecution.executeUpdateQuery(query);
    }

    @Override
    public List<Feedback> getFeedbacks(int post_id) {
        return PostFactory.getInstance().getFeedbackDatabase().getFeedbacksByPostId(post_id);
    }

    public List<Post> listToPosts(List<Map<String, Object>> responseMaps) throws Exception {
        List<Post> results = new ArrayList<>();
        for (Map<String, Object> responseMap : responseMaps) {
            Post post = (Post) PostFactory.getInstance().getNewPost();
            post.setId((Integer) responseMap.get(PostDbColumnNames.ID));
            post.setTitle((String) responseMap.get(PostDbColumnNames.TITLE));
            post.setCapacity((Integer) responseMap.get(PostDbColumnNames.CAPACITY));
            post.setDescription((String) responseMap.get(PostDbColumnNames.DESCRIPTION));
            post.setEndDate(localDateTimeToDate((LocalDateTime) responseMap.get(PostDbColumnNames.ENDDATE)));
            post.setHidden((Integer) responseMap.get(PostDbColumnNames.ISHIDDEN));
            post.setDestination((String) responseMap.get(PostDbColumnNames.DESTINATION));
            post.setMaxAge((Integer) responseMap.get(PostDbColumnNames.MAXAGE));
            post.setMinAge((Integer) responseMap.get(PostDbColumnNames.MINAGE));
            post.setStartDate(localDateTimeToDate((LocalDateTime) responseMap.get(PostDbColumnNames.STARTDATE)));
            post.setSource((String) responseMap.get(PostDbColumnNames.SOURCE));
            post.setOwner_id((Integer) responseMap.get(PostDbColumnNames.OWNER));
            results.add(post);
        }
        return results;
    }

    private Date localDateTimeToDate(LocalDateTime ldt) {
        Instant instant = ldt.toInstant(ZoneOffset.UTC);
        return Date.from(instant);
    }
}
