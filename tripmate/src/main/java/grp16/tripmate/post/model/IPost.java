package grp16.tripmate.post.model;

import java.util.List;

public interface IPost {

    boolean createPost();

    List<Post> getPostsByUserId(int userid);

    List<Post> getAllPosts();

    Post getPostByPostId(int postid);

    List<Post> getFeedbackPosts() throws Exception;

    boolean isEligibleForFeedback();
}
