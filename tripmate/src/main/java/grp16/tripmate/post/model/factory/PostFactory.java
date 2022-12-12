package grp16.tripmate.post.model.factory;

import grp16.tripmate.db.execute.DatabaseExecutor;
import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.IPostsQueryGenerator;
import grp16.tripmate.post.database.PostDatabase;
import grp16.tripmate.post.database.PostsQueryGenerator;
import grp16.tripmate.post.database.feedback.FeedbackDatabase;
import grp16.tripmate.post.database.feedback.FeedbackQueryGenerator;
import grp16.tripmate.post.database.feedback.IFeedbackDatabase;
import grp16.tripmate.post.database.feedback.IFeedbackQueryGenerator;
import grp16.tripmate.post.model.feedback.Feedback;
import grp16.tripmate.post.model.feedback.IFeedback;
import grp16.tripmate.post.model.IPost;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.PostValidator;

public class PostFactory implements IPostFactory {

    private static IPostFactory instance;

    private PostFactory() {

    }

    public static IPostFactory getInstance() {
        if (instance == null) {
            instance = new PostFactory();
        }
        return instance;
    }

    @Override
    public IPost getNewPost() {
        return new Post();
    }

    @Override
    public IPostDatabase getPostDatabase() {
        return new PostDatabase(getNewDatabaseExecutor(), getPostQueryBuilder());
    }

    @Override
    public IPostsQueryGenerator getPostQueryBuilder() {
        return PostsQueryGenerator.getInstance();
    }

    @Override
    public IFeedback getNewFeedback() {
        return new Feedback();
    }

    @Override
    public IFeedbackDatabase getFeedbackDatabase() {
        return new FeedbackDatabase(getNewDatabaseExecutor(), getFeedbackQueryBuilder());
    }

    @Override
    public IFeedbackQueryGenerator getFeedbackQueryBuilder() {
        return FeedbackQueryGenerator.getInstance();
    }

    @Override
    public PostValidator getPostValidator() {
        return new PostValidator();
    }

    @Override
    public ILogger getLogger(Object classObj) {
        return new MyLoggerAdapter(classObj);
    }

    @Override
    public IDatabaseExecutor getNewDatabaseExecutor() {
        return new DatabaseExecutor();
    }


}