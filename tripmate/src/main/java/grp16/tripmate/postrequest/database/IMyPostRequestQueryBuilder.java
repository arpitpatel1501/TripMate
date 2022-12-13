package grp16.tripmate.postrequest.database;

import grp16.tripmate.postrequest.model.PostRequestStatus;

public interface IMyPostRequestQueryBuilder {
    public String getMyPostRequests(int userid);
    public String createJoinRequest(int post_id, int user_id);
    public String getPostOwnerDetails(int post_id);
    public String updateRequestStatus(int requestId, PostRequestStatus postRequestStatus);
    public String getPostRequesteeDetails (int request_id);


//    public String getPostOwnerDetailsbyPostId(int post_id);
//    public String getPostRequesteeDetailsbyRequestId (int request_id);

}
