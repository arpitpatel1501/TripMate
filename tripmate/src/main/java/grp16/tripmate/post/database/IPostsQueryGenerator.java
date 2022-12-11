package grp16.tripmate.post.database;

import grp16.tripmate.post.model.IPost;
import grp16.tripmate.post.model.Post;

public interface IPostsQueryGenerator {

    String getCreatePostQuery(IPost post);

    String getAllPosts();

    String getPostsByUserId(int userId);

    String getPostByPostId(int postId);

    String getUpdatePostQuery(IPost post);

    String deletePostQuery(int postId);

    String hidePostQuery(int postId);

    String getFeedbackPosts();
}
