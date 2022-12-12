package grp16.tripmate.post.model.feedback;

import grp16.tripmate.post.database.feedback.IFeedbackDatabase;

public class Feedback implements IFeedback {
    private int id;
    private int postId;
    private int userId;
    private String feedback;
    private float rating;

    public Feedback() {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public boolean createFeedback(IFeedbackDatabase database) {
        return database.createFeedback(this);
    }
}
