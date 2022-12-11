package grp16.tripmate.post.feedback.database;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.post.feedback.model.FeedbackDbColumnNames;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDatabase implements IFeedbackDatabase {
    private final ILogger logger = new MyLoggerAdapter(this);

    private static IDatabaseConnection dbConnection = null;
    private static IFeedbackQueryBuilder queryBuilder = null;

    public FeedbackDatabase() {
        dbConnection = new DatabaseConnection();
        queryBuilder = FeedbackQueryBuilder.getInstance();
    }

    @Override
    public void createFeedback(Feedback feedback) {
        String query = queryBuilder.createFeedback(feedback);
        execute(query);
    }

    @Override
    public void deleteFeedbackByPostId(int postid) {
        String query = queryBuilder.deleteFeedbackByPostId(postid);
        execute(query);
    }

    @Override
    public List<Feedback> getFeedbacksByPostId(int post_id) {
        String query = queryBuilder.getFeedbacksByPostId(post_id);
        return executeQuery(query);
    }

    private List<Feedback> executeQuery(String query) {
        try {
            final Connection connection = dbConnection.getDatabaseConnection();
            List<Feedback> feedbacks = resultSetToFeedback(connection.createStatement().executeQuery(query));
            connection.close();
            return feedbacks;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private void execute(String query) {
        try {
            final Connection connection = dbConnection.getDatabaseConnection();
            connection.createStatement().execute(query);
            connection.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<Feedback> resultSetToFeedback(ResultSet rs) throws Exception {
        List<Feedback> results = new ArrayList<>();
        while (rs.next()) {
            Feedback feedback = new Feedback();

            feedback.setId(rs.getInt(FeedbackDbColumnNames.ID));
            feedback.setPostId(rs.getInt(FeedbackDbColumnNames.POST_ID));
            feedback.setUserId(rs.getInt(FeedbackDbColumnNames.USER_ID));
            feedback.setFeedback(rs.getString(FeedbackDbColumnNames.FEEDBACK));
            feedback.setRating(rs.getFloat(FeedbackDbColumnNames.RATING));

            results.add(feedback);
        }
        return results;
    }


}