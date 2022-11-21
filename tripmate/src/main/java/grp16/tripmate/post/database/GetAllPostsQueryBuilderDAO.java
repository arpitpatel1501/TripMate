package grp16.tripmate.post.database;

public interface GetAllPostsQueryBuilderDAO {

    /**
     * Gets the query to select admin by email.
     *
     * @param email is email of the admin
     * @return String query to select admin by email.
     */
    String getPostsByUserId(int userid);

    String getAllPostsNotCraetedByUserId(int userid);

}
