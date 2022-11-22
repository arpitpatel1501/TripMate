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
        String query = "SELECT `status`,\n" +
                "FROM `PostRequest` where `created_by` = " + userid;
        return query;
    }
}
