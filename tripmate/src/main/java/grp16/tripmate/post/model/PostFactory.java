package grp16.tripmate.post.model;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.IPostsQueryGenerator;
import grp16.tripmate.post.database.PostDatabase;
import grp16.tripmate.post.database.PostsQueryGenerator;
import grp16.tripmate.post.feedback.database.FeedbackDatabase;
import grp16.tripmate.post.feedback.database.FeedbackQueryBuilder;
import grp16.tripmate.post.feedback.database.IFeedbackDatabase;
import grp16.tripmate.post.feedback.database.IFeedbackQueryBuilder;
import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.post.feedback.model.IFeedback;

public class PostFactory implements IPostFactory {

    private static IPostFactory instance;
    private final IPostDatabase postDatabase;
    private final IPostsQueryGenerator postQueryBuilder;
    private final IFeedbackDatabase feedbackDatabase;
    private final IFeedbackQueryBuilder feedbackQueryBuilder;
    private final ILogger logger;

    private PostFactory() {
        postDatabase = new PostDatabase();
        postQueryBuilder = PostsQueryGenerator.getInstance();
        feedbackDatabase = new FeedbackDatabase();
        feedbackQueryBuilder = FeedbackQueryBuilder.getInstance();
        logger = new MyLoggerAdapter(this);
    }

    public static IPostFactory getInstance() {
        if (instance == null) {
            instance = new PostFactory();
        }
        return instance;
    }

    @Override
    public IPost getNewPost() {
        return new Post(postDatabase);
    }

    @Override
    public IPostDatabase getPostDatabase() {
        return postDatabase;
    }

    @Override
    public IPostsQueryGenerator getPostQueryBuilder() {
        return postQueryBuilder;
    }

    @Override
    public IFeedback getNewFeedback() {
        return new Feedback();
    }

    @Override
    public IFeedbackDatabase getFeedbackDatabase() {
        return feedbackDatabase;
    }

    @Override
    public IFeedbackQueryBuilder getFeedbackQueryBuilder() {
        return feedbackQueryBuilder;
    }

    @Override
    public ILogger getLogger(Object classObj) {
        return new MyLoggerAdapter(classObj);
    }
}