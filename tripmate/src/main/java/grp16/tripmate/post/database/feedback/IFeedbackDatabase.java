package grp16.tripmate.post.database.feedback;

import grp16.tripmate.post.model.feedback.Feedback;

import java.util.List;

public interface IFeedbackDatabase {

    boolean createFeedback(Feedback feedback);

    boolean updateFeedback(Feedback feedback);

    List<Feedback> getFeedbacksByPostId(int post_id) throws Exception;

    boolean deleteFeedbackByPostId(int postId);
}
