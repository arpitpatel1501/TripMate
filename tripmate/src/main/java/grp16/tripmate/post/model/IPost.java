package grp16.tripmate.post.model;

import grp16.tripmate.post.feedback.model.Feedback;

import java.util.List;

public interface IPost {

    boolean createPost() throws Exception;

    List<Post> getAllPosts();

    List<Post> getPostsByUserId(int userid);

    Post getPostByPostId(int postid);

    boolean updatePost();

    boolean deletePost();

    boolean isEligibleForFeedback();

    List<Feedback> getFeedbacks();

    boolean hidePost();

    boolean isEligibleToJoin() throws Exception;

    Post copy(Post post);
}
