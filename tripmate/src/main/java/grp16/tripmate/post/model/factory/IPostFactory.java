package grp16.tripmate.post.model.factory;

import grp16.tripmate.db.execute.IDatabaseExecution;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.IPostsQueryGenerator;
import grp16.tripmate.post.database.feedback.IFeedbackDatabase;
import grp16.tripmate.post.database.feedback.IFeedbackQueryGenerator;
import grp16.tripmate.post.model.feedback.IFeedback;
import grp16.tripmate.post.model.IPost;
import grp16.tripmate.post.model.PostValidator;
import grp16.tripmate.user.database.IUserDatabase;

public interface IPostFactory {

    IPost getNewPost();

    IPostDatabase getPostDatabase();

    PostValidator getPostValidator();

    IPostsQueryGenerator getPostQueryBuilder();

    IFeedback getNewFeedback();

    IFeedbackDatabase getFeedbackDatabase();

    IFeedbackQueryGenerator getFeedbackQueryBuilder();

    ILogger getLogger(Object classObj);


    IDatabaseExecution getNewDatabaseExecutor();
}
