package grp16.tripmate.post.database.feedback;

import grp16.tripmate.post.model.feedback.Feedback;

public interface IFeedbackQueryGenerator {
    String createFeedback(Feedback feedback);

    String updateFeedback(Feedback feedback);

    String getFeedbacksByPostId(int postId);

    String deleteFeedbackByPostId(int postId);
}
