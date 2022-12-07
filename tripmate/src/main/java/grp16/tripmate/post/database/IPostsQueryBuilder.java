package grp16.tripmate.post.database;

import grp16.tripmate.post.model.Post;

public interface IPostsQueryBuilder {

    String getCreatePostQuery(Post post);

    String getAllPosts();

    String getPostsByUserId(int userid);

    String getPostByPostId(int postid);

    String getUpdatePostQuery(Post post);

    String deletePostQuery(int postid);

    String hidePostQuery(int postid);

    String getFeedbackPosts();
}
