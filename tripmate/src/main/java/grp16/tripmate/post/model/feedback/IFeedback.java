package grp16.tripmate.post.model.feedback;

import grp16.tripmate.post.database.feedback.IFeedbackDatabase;

public interface IFeedback {
    public void createFeedback(IFeedbackDatabase database);
}
