package grp16.tripmate.post.database;

public class PostRequestDB {

    private static PostRequestDB instance;

    private PostRequestDB() {
        //Required empty constructor
    }

    public static PostRequestDB getInstance() {
        if (instance == null) {
            instance = new PostRequestDB();
        }
        return instance;
    }
}
