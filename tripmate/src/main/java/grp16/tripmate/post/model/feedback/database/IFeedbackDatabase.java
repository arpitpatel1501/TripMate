package grp16.tripmate.post.model.feedback.database;

import grp16.tripmate.post.model.feedback.model.Feedback;

import java.util.List;

public interface IFeedbackDatabase {

    void createFeedback(Feedback feedback);

    void deleteFeedbackByPostId(int postid);

    List<Feedback> getFeedbacksByPostId(int post_id);
}
