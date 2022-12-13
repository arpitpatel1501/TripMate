package grp16.tripmate.post.model.feedback;

import grp16.tripmate.post.database.feedback.IFeedbackDatabase;

public interface IFeedback {
    boolean createFeedback(IFeedbackDatabase database);
}
