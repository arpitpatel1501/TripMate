package grp16.tripmate.post.database;

public interface IPostsQueryBuilder {

    /**
     * Gets the query to select admin by email.
     *
     * @param email is email of the admin
     * @return String query to select admin by email.
     */
    String getAllPosts();

    String getPostsByUserId(int userid);

    String getPostByPostId(int postid);

}
