package grp16.tripmate.post.model;

import java.util.List;

public interface IPost {

    boolean createPost() throws Exception;

    List<Post> getPostsByUserId(int userid);

    List<Post> getAllPosts();

    Post getPostByPostId(int postid);


    boolean isEligibleForFeedback();
}
