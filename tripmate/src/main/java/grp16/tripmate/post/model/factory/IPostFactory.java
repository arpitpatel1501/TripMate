package grp16.tripmate.post.model.factory;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.IPostsQueryGenerator;
import grp16.tripmate.post.model.feedback.database.IFeedbackDatabase;
import grp16.tripmate.post.model.feedback.database.IFeedbackQueryBuilder;
import grp16.tripmate.post.model.feedback.model.IFeedback;
import grp16.tripmate.post.model.IPost;
import grp16.tripmate.post.model.PostValidator;

public interface IPostFactory {

    IPost getNewPost();

    IPostDatabase getPostDatabase();

    PostValidator getPostValidator();

    IPostsQueryGenerator getPostQueryBuilder();

    IFeedback getNewFeedback();

    IFeedbackDatabase getFeedbackDatabase();

    IFeedbackQueryBuilder getFeedbackQueryBuilder();

    ILogger getLogger(Object classObj);

}
