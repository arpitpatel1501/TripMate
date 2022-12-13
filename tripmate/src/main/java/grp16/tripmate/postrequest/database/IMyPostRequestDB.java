package grp16.tripmate.postrequest.database;

import grp16.tripmate.postrequest.model.IMyPostRequest;
import grp16.tripmate.postrequest.model.PostRequestStatus;

import java.util.List;
import java.util.Map;

public interface IMyPostRequestDB {
    public List<Map<String, Object>> getMyPostRequests() throws Exception;
    public boolean createJoinRequest(int postId) throws Exception;
    public List<Map<String, Object>> getPostOwnerDetails(int postId);
    public boolean updateRequest(int requestId, PostRequestStatus postRequestStatus);
    public List<Map<String, Object>> getPostRequesteeDetails(int requestId);

}
