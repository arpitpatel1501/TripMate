package grp16.tripmate.postrequest.database;

import grp16.tripmate.postrequest.model.PostRequestStatus;

public interface IMyPostRequestDB {
    String getPostRequestByUserId(int userid);

    String createJoinRequest(int post_id, int user_id);

    public String updateRequestStatus(String postId, String requestOwner, PostRequestStatus postRequestStatus);

}
