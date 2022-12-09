package grp16.tripmate.post.database;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.feedback.database.IFeedbackDatabase;
import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.post.model.*;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.UserDbColumnNames;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDatabase implements IPostDatabase {
    private final ILogger logger = new MyLoggerAdapter(this);
    private final IPostsQueryBuilder queryBuilder;
    private final IDatabaseConnection dbConnection;

    public PostDatabase() {
        queryBuilder = PostsQueryBuilder.getInstance();
        dbConnection = new DatabaseConnection();
    }

    @Override
    public boolean createPost(Post post) throws Exception {
        post.setOwner((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
        String query = queryBuilder.getCreatePostQuery(post);
        return executeQuery(query);
    }

    @Override
    public List<Post> getPostsByUserId(int userid) {
        String query = queryBuilder.getPostsByUserId(userid);
        return selectQueryExecute(query);
    }

    @Override
    public List<Post> getAllPosts() {
        String query = queryBuilder.getAllPosts();
        return selectQueryExecute(query);
    }

    @Override
    public Post getPostByPostId(int postid) {
        String query = queryBuilder.getPostByPostId(postid);
        List<Post> posts = selectQueryExecute(query);
        if (posts != null) {
            return posts.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean updatePost(Post post) {
        String query = queryBuilder.getUpdatePostQuery(post);
        return executeQuery(query);
    }

    @Override
    public boolean deletePost(Post post) {
        PostFactory.getInstance().getFeedbackDatabase().deleteFeedbackByPostId(post.getId());
        String query = queryBuilder.deletePostQuery(post.getId());
        return executeQuery(query);
    }

    @Override
    public boolean hidePost(Post post) {
        String query = queryBuilder.hidePostQuery(post.getId());
        return executeQuery(query);
    }

    @Override
    public List<Feedback> getFeedbacks(int post_id) {
        return PostFactory.getInstance().getFeedbackDatabase().getFeedbacksByPostId(post_id);
    }

    @Override
    public List<Post> resultSetToPosts(ResultSet rs) throws Exception {
        List<Post> results = new ArrayList<>();
        while (rs.next()) {
            Post post = (Post) PostFactory.getInstance().getNewPost();
            post.setId(rs.getInt(PostDbColumnNames.ID));
            post.setTitle(rs.getString(PostDbColumnNames.TITLE));
            post.setCapacity(rs.getInt(PostDbColumnNames.CAPACITY));
            post.setDescription(rs.getString(PostDbColumnNames.DESCRIPTION));
            post.setEndDate(rs.getDate(PostDbColumnNames.ENDDATE));
            post.setHidden(rs.getBoolean(PostDbColumnNames.ISHIDDEN));
            post.setDestination(rs.getString(PostDbColumnNames.DESTINATION));
            post.setMaxAge(rs.getInt(PostDbColumnNames.MAXAGE));
            post.setMinAge(rs.getInt(PostDbColumnNames.MINAGE));
            post.setStartDate(rs.getDate(PostDbColumnNames.STARTDATE));
            post.setSource(rs.getString(PostDbColumnNames.SOURCE));
            post.setOwner(rs.getInt(PostDbColumnNames.OWNER));
            results.add(post);
        }
        return results;
    }

    private boolean executeQuery(String query) {
        Connection connection;
        boolean isSuccess = false;
        try {
            connection = dbConnection.getDatabaseConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            isSuccess = true;
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

    private List<Post> selectQueryExecute(String query) {
        try {
            final Connection connection = dbConnection.getDatabaseConnection();
            final ResultSet postRS = connection.createStatement().executeQuery(query);
            List<Post> posts = resultSetToPosts(postRS);
            connection.close();
            return posts;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
