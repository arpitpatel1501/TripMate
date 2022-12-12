package grp16.tripmate.post.database;

import grp16.tripmate.post.database.feedback.IFeedbackDatabase;
import grp16.tripmate.post.model.feedback.Feedback;
import grp16.tripmate.post.model.Post;

import java.util.List;

public interface IPostDatabase {

    boolean updatePost(Post post);

    boolean deletePost(int post_id);

    boolean hidePost(int post_id);

    boolean createPost(Post post) throws Exception;

    List<Post> getPostsByUserId(int userId);

    List<Post> getAllPosts(int loggedInUser);

    Post getPostByPostId(int post_id);

    List<Feedback> getFeedbacks(IFeedbackDatabase feedbackDatabase, int post_id) throws Exception;
}
