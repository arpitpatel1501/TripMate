package grp16.tripmate.post.model;

import grp16.tripmate.post.model.feedback.model.Feedback;
import grp16.tripmate.post.model.exception.MinAgeGreaterThanMaxAgeException;
import grp16.tripmate.post.model.exception.StartDateAfterEndDateException;
import grp16.tripmate.post.model.exception.StartDateBeforeTodayException;

import java.text.ParseException;
import java.util.List;

public interface IPost {

    boolean createPost() throws Exception;

    List<Post> getAllPosts() throws Exception;

    List<Post> getPostsByUserId(int userid) throws Exception;

    Post getPostByPostId(int postId) throws Exception;

    boolean updatePost();

    boolean deletePost();

    List<Feedback> getFeedbacks();

    boolean hidePost();

    void validatePost() throws ParseException, StartDateAfterEndDateException, MinAgeGreaterThanMaxAgeException, StartDateBeforeTodayException;
}
