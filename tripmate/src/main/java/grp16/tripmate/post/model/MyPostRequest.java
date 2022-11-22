package grp16.tripmate.post.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyPostRequest {
    private int id;
    private PostRequestStatus status;
    private int postId;
    private String userName;

    public int getId() {
        return id;
    }

    public PostRequestStatus getStatus() {
        return status;
    }

    public int getPostId() {
        return postId;
    }

    public String getUserName() {
        return userName;
    }

    public MyPostRequest(int id, PostRequestStatus status, int postId, String userName) {
        this.id = id;
        this.status = status;
        this.postId = postId;
        this.userName = userName;
    }

    public MyPostRequest() {
        // Empty constructor
    }

    public static List<MyPostRequest> resultSetToPostRequests(ResultSet rs){
        List<MyPostRequest> results = new ArrayList<>();
//        while (rs.next()) {
            MyPostRequest myPostRequest = new MyPostRequest();
            myPostRequest.id = 1;
            myPostRequest.userName = "Harshil";
            myPostRequest.postId = 3;
            myPostRequest.status = PostRequestStatus.PENDING;
            results.add(myPostRequest);

            MyPostRequest myPostRequest2 = new MyPostRequest();
            myPostRequest2.id = 4;
            myPostRequest2.userName = "Harshil 2";
            myPostRequest2.postId = 5;
            myPostRequest2.status = PostRequestStatus.PENDING;
            results.add(myPostRequest2);
//        }
        return results;
    }
}


