package grp16.tripmate.post.feedback.database;

import grp16.tripmate.post.feedback.model.Feedback;

public interface IFeedbackDatabase {

    void createFeedback(Feedback feedback);

    void deleteFeedbackByPostId(int postid);
}
