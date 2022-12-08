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

import java.util.Date;

public class PostFactory implements IPostFactory {

    private static IPostFactory instance;

    private PostFactory() {
        //Required private empty constructor
    }

    public static PostFactory getInstance() {
        if (instance == null) {
            instance = new PostFactory();
        }
        return (PostFactory) instance;
    }

    @Override
    public Post getNewPost() {
        return new Post();
    }

    @Override
    public IPostDatabase getPostDatabase() {
        return new PostDatabase();
    }

    @Override
    public IPostsQueryBuilder getPostQueryBuilder() {
        return PostsQueryBuilder.getInstance();
    }

    @Override
    public Feedback getNewFeedback() {
        return new Feedback();
    }

    @Override
    public IFeedbackDatabase getFeedbackDatabase() {
        return new FeedbackDatabase();
    }

    @Override
    public IFeedbackQueryBuilder getFeedbackQueryBuilder() {
        return FeedbackQueryBuilder.getInstance();
    }
}