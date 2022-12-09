package grp16.tripmate.post.model;

import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.IPostsQueryBuilder;
import grp16.tripmate.post.database.PostDatabase;
import grp16.tripmate.post.database.PostsQueryBuilder;
import grp16.tripmate.post.feedback.database.FeedbackDatabase;
import grp16.tripmate.post.feedback.database.FeedbackQueryBuilder;
import grp16.tripmate.post.feedback.database.IFeedbackDatabase;
import grp16.tripmate.post.feedback.database.IFeedbackQueryBuilder;
import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.post.feedback.model.IFeedback;

public class PostFactory implements IPostFactory {

    private static IPostFactory instance = null;
    private IPostDatabase postDatabase;
    private IPostsQueryBuilder postQueryBuilder;
    private IFeedbackDatabase feedbackDatabase;
    private IFeedbackQueryBuilder feedbackQueryBuilder;

    private PostFactory() {
        postDatabase = new PostDatabase();
        postQueryBuilder = PostsQueryBuilder.getInstance();
        feedbackDatabase = new FeedbackDatabase();
        feedbackQueryBuilder = FeedbackQueryBuilder.getInstance();
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
        return postDatabase;
    }

    @Override
    public IPostsQueryBuilder getPostQueryBuilder() {
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
}