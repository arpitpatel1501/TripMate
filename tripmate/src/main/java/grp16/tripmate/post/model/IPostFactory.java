package grp16.tripmate.post.model;

import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.IPostsQueryBuilder;
import grp16.tripmate.post.feedback.database.IFeedbackDatabase;
import grp16.tripmate.post.feedback.database.IFeedbackQueryBuilder;
import grp16.tripmate.post.feedback.model.IFeedback;

public interface IPostFactory {

    IPost getNewPost();

    IPostDatabase getPostDatabase();

    IPostsQueryBuilder getPostQueryBuilder();

    IFeedback getNewFeedback();

    IFeedbackDatabase getFeedbackDatabase();

    IFeedbackQueryBuilder getFeedbackQueryBuilder();

}
