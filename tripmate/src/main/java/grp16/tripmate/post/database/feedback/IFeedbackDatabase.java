package grp16.tripmate.post.database.feedback;

import grp16.tripmate.post.model.feedback.Feedback;

import java.util.List;

public interface IFeedbackDatabase {

    void createFeedback(Feedback feedback);

    void deleteFeedbackByPostId(int postid);

    List<Feedback> getFeedbacksByPostId(int post_id);
}
