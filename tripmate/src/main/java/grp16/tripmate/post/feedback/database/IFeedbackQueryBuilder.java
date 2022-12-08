package grp16.tripmate.post.feedback.database;

import grp16.tripmate.post.feedback.model.Feedback;

public interface IFeedbackQueryBuilder {
    String createFeedback(Feedback feedback);

    String deleteFeedbackByPostId(int postid);

    String getFeedbacksByPostId(int post_id);
}
