package grp16.tripmate.feedback.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.feedback.database.FeedbackQueryBuilder;
import grp16.tripmate.feedback.database.IFeedbackQueryBuilder;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostsQueryBuilder;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.PostDbColumnNames;
import grp16.tripmate.user.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Feedback implements IFeedback {

    private final ILogger logger = new MyLoggerAdapter(this);


    private int id;
    private Post post;
    private User user;
    private String feedback;

    private float rating;

    private static IDatabaseConnection dbConnection = null;
    private static IFeedbackQueryBuilder queryBuilder = null;

    public Feedback() {
        dbConnection = new DatabaseConnection();
        queryBuilder = FeedbackQueryBuilder.getInstance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(int postId) {
        this.post = new Post().getPostByPostId(postId);
    }

    public User getUser() {
        return user;
    }

    public void setUser(int userId) throws Exception {
        this.user = new User().getUserById(userId);
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


    public void createFeedback() {
        try {
            final Connection connection = dbConnection.getDatabaseConnection();
            String query = queryBuilder.createFeedback(this);
            connection.createStatement().execute(query);
            connection.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static List<Feedback> resultSetToFeedback(ResultSet rs) throws Exception {
        List<Feedback> results = new ArrayList<>();
        while (rs.next()) {
            Feedback feedback = new Feedback();

            feedback.setId(rs.getInt(FeedbackDbColumnNames.ID));
            feedback.setPost(rs.getInt(FeedbackDbColumnNames.POST_ID));
            feedback.setUser(rs.getInt(FeedbackDbColumnNames.USER_ID));
            feedback.setFeedback(rs.getString(FeedbackDbColumnNames.FEEDBACK));
            feedback.setRating(rs.getFloat(FeedbackDbColumnNames.RATING));

            results.add(feedback);
        }
        return results;
    }
}
