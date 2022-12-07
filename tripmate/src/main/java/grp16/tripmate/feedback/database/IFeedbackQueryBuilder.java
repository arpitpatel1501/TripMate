package grp16.tripmate.feedback.database;

import grp16.tripmate.feedback.model.Feedback;

public interface IFeedbackQueryBuilder {
    String createFeedback(Feedback feedback);
}
