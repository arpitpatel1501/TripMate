package grp16.tripmate.post.feedback.model;

import grp16.tripmate.post.feedback.database.FeedbackDatabase;
import grp16.tripmate.post.feedback.database.IFeedbackDatabase;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.user.model.User;

public class Feedback implements IFeedback {

    private final ILogger logger = new MyLoggerAdapter(this);


    private int id;
    private int postId;
    private User user;
    private String feedback;

    private float rating;

    private IFeedbackDatabase database;

    public Feedback() {
        database = new FeedbackDatabase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
        database.createFeedback(this);
    }
}
