package grp16.tripmate.post.model;

import grp16.tripmate.user.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostRequest {
    private int id;
    private PostRequestStatus status;
    private int postId;
    private String userName;

    public PostRequest(int id, PostRequestStatus status, int postId, String userName) {
        this.id = id;
        this.status = status;
        this.postId = postId;
        this.userName = userName;
    }

    public PostRequest() {
        // Empty constructor
    }

    public static List<PostRequest> resultSetToPostRequests(ResultSet rs) throws SQLException {
        List<PostRequest> results = new ArrayList<>();
//        while (rs.next()) {
            PostRequest postRequest = new PostRequest();
            postRequest.id = 1;
            postRequest.userName = "Harshil";
            postRequest.postId = 3;
            postRequest.status = PostRequestStatus.PENDING;
            results.add(postRequest);

            PostRequest postRequest2 = new PostRequest();
            postRequest2.id = 4;
            postRequest2.userName = "Harshil 2";
            postRequest2.postId = 5;
            postRequest2.status = PostRequestStatus.PENDING;
            results.add(postRequest2);
//        }
        return results;
    }
}


