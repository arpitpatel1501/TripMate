package grp16.tripmate.post.database.feedback;

import grp16.tripmate.db.execute.IDatabaseExecution;
import grp16.tripmate.post.model.feedback.Feedback;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeedbackDatabase implements IFeedbackDatabase {
    private final ILogger logger = new MyLoggerAdapter(this);

    private final IDatabaseExecution databaseExecutor;
    private final IFeedbackQueryGenerator queryGenerator;

    public FeedbackDatabase(IDatabaseExecution databaseExecutor, IFeedbackQueryGenerator queryGenerator) {
        this.databaseExecutor = databaseExecutor;
        this.queryGenerator = queryGenerator;
    }

    @Override
    public boolean createFeedback(Feedback feedback) {
        String query = queryGenerator.createFeedback(feedback);
        return databaseExecutor.executeInsertQuery(query);
    }

    @Override
    public boolean deleteFeedbackByPostId(int postId) {
        String query = queryGenerator.deleteFeedbackByPostId(postId);
        return databaseExecutor.executeDeleteQuery(query);
    }

    @Override
    public List<Feedback> getFeedbacksByPostId(int post_id) throws Exception {
        String query = queryGenerator.getFeedbacksByPostId(post_id);
        return listToFeedback(databaseExecutor.executeSelectQuery(query));
    }

    private List<Feedback> listToFeedback(List<Map<String, Object>> results) {
        List<Feedback> feedbacks = new ArrayList<>();
        for (Map<String, Object> result : results) {
            Feedback feedback = new Feedback();

            feedback.setId((Integer) result.get(FeedbackDbColumnNames.ID));
            feedback.setPostId((Integer) result.get(FeedbackDbColumnNames.POST_ID));
            feedback.setUserId((Integer) result.get(FeedbackDbColumnNames.USER_ID));
            feedback.setFeedback((String) result.get(FeedbackDbColumnNames.FEEDBACK));
            feedback.setRating((Float) result.get(FeedbackDbColumnNames.RATING));

            feedbacks.add(feedback);
        }
        return feedbacks;
    }


}