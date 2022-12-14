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
    public IPost makeNewPost() {
        return new Post();
    }

    @Override
    public IPostDatabase makePostDatabase() {
        return new PostDatabase(makeNewDatabaseExecutor(), makePostQueryBuilder());
    }

    @Override
    public IPostsQueryGenerator makePostQueryBuilder() {
        return PostsQueryGenerator.getInstance();
    }

    @Override
    public IFeedbackDatabase makeFeedbackDatabase() {
        return new FeedbackDatabase(makeNewDatabaseExecutor(), makeFeedbackQueryBuilder());
    }

    @Override
    public IFeedbackQueryGenerator makeFeedbackQueryBuilder() {
        return FeedbackQueryGenerator.getInstance();
    }

    @Override
    public PostValidator makePostValidator() {
        return new PostValidator();
    }

    @Override
    public ILogger makeNewLogger(Object classObj) {
        return new MyLoggerAdapter(classObj);
    }

    @Override
    public IDatabaseExecutor makeNewDatabaseExecutor() {
        return new DatabaseExecutor();
    }
}