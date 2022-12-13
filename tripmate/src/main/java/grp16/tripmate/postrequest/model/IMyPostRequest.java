package grp16.tripmate.postrequest.model;

import grp16.tripmate.postrequest.database.IMyPostRequestDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IMyPostRequest {
    List<MyPostRequest> getMyPostRequests(IMyPostRequestDB myPostRequestDB) throws Exception;
    boolean createJoinRequest(IMyPostRequestDB myPostRequestDB, int postId) throws Exception;
    MyPostRequest getPostOwnerDetails(IMyPostRequestDB myPostRequestDB, int postId) throws Exception;
    boolean updateRequest(IMyPostRequestDB myPostRequestDB, int requestId, PostRequestStatus postRequestStatus);
    MyPostRequest getPostRequesterDetails(IMyPostRequestDB myPostRequestDB, int requestId) throws Exception;
}
