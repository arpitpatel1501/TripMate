package grp16.tripmate.feedback.database;

import grp16.tripmate.feedback.model.Feedback;
import grp16.tripmate.feedback.model.FeedbackDbColumnNames;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

public class FeedbackQueryBuilder implements IFeedbackQueryBuilder {

    static IFeedbackQueryBuilder instance;
    private final ILogger logger = new MyLoggerAdapter(this);

    private FeedbackQueryBuilder() {
        //Required empty constructor
    }

    public static IFeedbackQueryBuilder getInstance() {
        if (instance == null) {
            instance = new FeedbackQueryBuilder();
        }
        return instance;
    }

    @Override
    public String createFeedback(Feedback feedback) {

        String query = "INSERT INTO " +
                FeedbackDbColumnNames.TABLE_NAME +
                " (" +
                FeedbackDbColumnNames.POST_ID + "," +
                FeedbackDbColumnNames.USER_ID + "," +
                FeedbackDbColumnNames.FEEDBACK + "," +
                FeedbackDbColumnNames.RATING +
                ")" +
                " VALUES " +
                "(" +
                feedback.getPost().getId() + "," +
                feedback.getUser().getId() + "," +
                " '" + feedback.getFeedback() + "' " + "," +
                feedback.getRating() + ")";

        logger.info(query);

        return query;

    }

}
