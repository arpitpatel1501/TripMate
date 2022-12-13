package grp16.tripmate.postrequest.database;

import grp16.tripmate.postrequest.model.PostRequestStatus;

public interface IMyPostRequestDB {
    String getPostRequestByUserId(int userid);
    public String getPostOwnerDetailsbyPostId(int post_id);
    public String getPostRequesteeDetailsbyRequestId (int request_id);
    String createJoinRequest(int post_id, int user_id);
    public String updateRequestStatus(int requestId, PostRequestStatus postRequestStatus);

}
