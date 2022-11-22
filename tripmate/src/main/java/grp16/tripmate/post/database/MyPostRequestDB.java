package grp16.tripmate.post.database;

public class MyPostRequestDB implements IMyPostRequestDB {

    private static MyPostRequestDB instance;

    private MyPostRequestDB() {
        //Required empty constructor
    }

    public static MyPostRequestDB getInstance() {
        if (instance == null) {
            instance = new MyPostRequestDB();
        }
        return instance;
    }

    public String getPostRequests(int userid) {
//        return "SELECT `status`,\n" +
//                "FROM `PostRequest` where `` != 1 and `created_by` = " + userid;

//        posts.add(new Post(1, new User(), "title 1", 5, "source 1", "destination 1", new Date(), new Date(), 15, 25, "description 1", false));
        return null;
    }
}
