package grp16.tripmate.post.model.feedback.model;

import grp16.tripmate.post.model.feedback.database.IFeedbackDatabase;

public interface IFeedback {
    public void createFeedback(IFeedbackDatabase database);
}
