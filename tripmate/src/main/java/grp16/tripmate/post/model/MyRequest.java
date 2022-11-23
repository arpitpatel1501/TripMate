package grp16.tripmate.post.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MyRequest {
    private int id;
    private PostRequestStatus status;
    private int postId;
    private String userName;

    public MyRequest(int id, PostRequestStatus status, int postId, String userName) {
        this.id = id;
        this.status = status;
        this.postId = postId;
        this.userName = userName;
    }

    public MyRequest() {
        // Empty constructor
    }

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

    public static List<MyRequest> resultSetToMyRequests(ResultSet rs){
        List<MyRequest> results = new ArrayList<>();
//        while (rs.next()) {
        MyRequest myRequest = new MyRequest();
//        myRequest.id = 1;
//        myRequest.userName = "Harshil";
//        myRequest.postId = 3;
//        myRequest.status = PostRequestStatus.PENDING;
//        results.add(myRequest);
//
//        MyRequest myRequest2 = new MyRequest();
//        myRequest2.id = 4;
//        myRequest2.userName = "Harshil 2";
//        myRequest2.postId = 5;
//        myRequest2.status = PostRequestStatus.PENDING;
//        results.add(myRequest2);
//        }
        return results;
    }
}
