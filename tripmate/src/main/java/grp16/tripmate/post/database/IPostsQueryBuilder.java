package grp16.tripmate.post.database;

import grp16.tripmate.post.model.Post;

public interface IPostsQueryBuilder {

    String getAllPosts();

    String getPostsByUserId(int userid);

    String getPostByPostId(int postid);

    String getUpdatePostQuery(Post post);
}
