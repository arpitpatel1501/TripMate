package grp16.tripmate.postrequest.database;

public class MyRequestDB implements IMyRequestDB{
    private static MyRequestDB instance;
    public static MyRequestDB getInstance() {
        if (instance == null) {
            instance = new MyRequestDB();
        }
        return instance;
    }
    @Override
    public String getMyRequestByUserId(int userid) {
        String query = "SELECT p.title as postTitle, status, post_owner.firstname as firstNameCreator, post_owner.lastname lastNameCreator\n" +
                "FROM PostRequest pr\n" +
                "JOIN Post p on pr.Post_id = p.id\n" +
                "JOIN User u on pr.request_owner = u.id\n" +
                "JOIN User post_owner on post_owner.id = p.created_by\n" +
                "WHERE u.id = "+ userid +";";
        return query;
    }
}
