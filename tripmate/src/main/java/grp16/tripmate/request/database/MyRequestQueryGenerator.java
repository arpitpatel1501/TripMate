package grp16.tripmate.request.database;

public class MyRequestQueryGenerator implements IMyRequestQueryGenerator {
    private static MyRequestQueryGenerator instance;

    private MyRequestQueryGenerator() {
    }

    public static MyRequestQueryGenerator getInstance() {
        if (instance == null) {
            instance = new MyRequestQueryGenerator();
        }
        return instance;
    }

    @Override
    public String getMyRequestByUserId(int userid) {
        //TODO: do something about this static query
        String query = "SELECT p.title as postTitle, status, post_owner.firstname as firstNameCreator, post_owner.lastname lastNameCreator " +
                "FROM PostRequest pr " +
                "JOIN Post p on pr.Post_id = p.id " +
                "JOIN User u on pr.request_owner = u.id " +
                "JOIN User post_owner on post_owner.id = p.created_by " +
                "WHERE u.id = " + userid + ";";
        return query;
    }
}