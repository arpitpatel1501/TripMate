package grp16.tripmate.post.model.factory;

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
    private final IPostDatabase postDatabase;
    private final IPostsQueryGenerator postQueryBuilder;
    private final IFeedbackDatabase feedbackDatabase;
    private final IFeedbackQueryGenerator feedbackQueryBuilder;
    private final PostValidator postValidator;
    private final ILogger logger;

    private PostFactory() {
        postDatabase = new PostDatabase();
        postQueryBuilder = PostsQueryGenerator.getInstance();
        feedbackDatabase = new FeedbackDatabase();
        feedbackQueryBuilder = FeedbackQueryGenerator.getInstance();
        postValidator = new PostValidator();
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
    public IFeedbackQueryGenerator getFeedbackQueryBuilder() {
        return feedbackQueryBuilder;
    }

    @Override
    public PostValidator getPostValidator() {
        return postValidator;
    }

    @Override
    public ILogger getLogger(Object classObj) {
        return new MyLoggerAdapter(classObj);
    }
}