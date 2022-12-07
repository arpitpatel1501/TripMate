package grp16.tripmate.post.database;

import grp16.tripmate.post.model.Post;

import java.sql.ResultSet;
import java.util.List;

public interface IPostDatabase {

    boolean updatePost(Post post);

    boolean deletePost(Post post);

    boolean hidePost(Post post);

    boolean createPost(Post post) throws Exception;

    List<Post> resultSetToPosts(ResultSet rs) throws Exception;

    List<Post> getPostsByUserId(int userid);

    List<Post> getAllPosts();

    Post getPostByPostId(int postid);
}
