package grp16.tripmate.post.database;

public class MyPostRequestDB implements IMyPostRequestDB {

    private static MyPostRequestDB instance;
    public static MyPostRequestDB getInstance() {
        if (instance == null) {
            instance = new MyPostRequestDB();
        }
        return instance;
    }
    @Override
    public String getPostRequestByUserId(int userid) {
        String query = "SELECT u.firstname as firstNameRequestee, u.lastname as lastNameRequestee, p.title as postTitle, p.created_by as idCreator, post_owner.firstname as firstNameCreator, post_owner.lastname lastNameCreator \n" +
                "FROM PostRequest pr\n" +
                "JOIN Post p on pr.Post_id = p.id\n" +
                "JOIN User u on pr.request_owner = u.id\n" +
                "JOIN User post_owner on post_owner.id = p.created_by\n" +
                "WHERE pr.status = \"pending\" and pr.request_owner = "+ userid +" and "+ userid +" != p.created_by;";
        return query;
    }
}
