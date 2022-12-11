package grp16.tripmate.post.model.feedback.database;

import grp16.tripmate.post.model.feedback.model.Feedback;

public interface IFeedbackQueryBuilder {
    String createFeedback(Feedback feedback);

    String deleteFeedbackByPostId(int postid);

    String getFeedbacksByPostId(int post_id);
}
