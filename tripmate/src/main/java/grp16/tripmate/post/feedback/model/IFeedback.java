package grp16.tripmate.post.feedback.model;

import grp16.tripmate.post.feedback.database.IFeedbackDatabase;

public interface IFeedback {
    public void createFeedback(IFeedbackDatabase database);
}
