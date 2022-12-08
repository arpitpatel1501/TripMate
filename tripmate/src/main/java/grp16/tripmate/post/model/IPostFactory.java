package grp16.tripmate.post.model;

import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.IPostsQueryBuilder;
import grp16.tripmate.post.feedback.database.IFeedbackDatabase;
import grp16.tripmate.post.feedback.database.IFeedbackQueryBuilder;
import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.post.feedback.model.IFeedback;
import grp16.tripmate.user.model.IUser;

public interface IPostFactory {

    Post getNewPost();

    IPostDatabase getPostDatabase();

    IPostsQueryBuilder getPostQueryBuilder();

    Feedback getNewFeedback();

    IFeedbackDatabase getFeedbackDatabase();

    IFeedbackQueryBuilder getFeedbackQueryBuilder();

}
