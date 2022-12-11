package grp16.tripmate.post.model;

import grp16.tripmate.post.feedback.model.Feedback;

import java.text.ParseException;
import java.util.List;

public interface IPost {

    boolean createPost() throws Exception;

    List<Post> getAllPosts() throws Exception;

    List<Post> getPostsByUserId(int userid) throws Exception;

    Post getPostByPostId(int postid) throws Exception;

    boolean updatePost();

    boolean deletePost();

    List<Feedback> getFeedbacks();

    boolean hidePost();

    void validatePost() throws ParseException, StartDateAfterEndDateException, MinAgeGreaterThanMaxAgeException;
}
