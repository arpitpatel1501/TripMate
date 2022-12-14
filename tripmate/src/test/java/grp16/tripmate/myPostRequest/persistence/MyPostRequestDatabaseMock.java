package grp16.tripmate.myPostRequest.persistence;

import grp16.tripmate.myPostRequest.model.MyPostRequest;
import grp16.tripmate.myPostRequest.model.PostRequestStatus;
import grp16.tripmate.myPostRequest.model.factory.IMyPostRequestFactory;
import grp16.tripmate.post.model.Post;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPostRequestDatabaseMock implements IMyPostRequestPersistence {

    private static final Map<Integer, Map<String, Object>> myPostRequestsDB = new HashMap<>();

    private Map<String, Object> myPostRequest;
    private List<Map<String, Object>> myPostRequests;
    public MyPostRequestDatabaseMock() {
        myPostRequests = new ArrayList<>();
        myPostRequest = new HashMap<>();

        myPostRequest.put("requestId", 29);
        myPostRequest.put("firstNameRequester", "Arpit");
        myPostRequest.put("lastNameRequester", "Patel");
        myPostRequest.put("idRequester", 9);
        myPostRequest.put("firstNameCreator", "Aman");
        myPostRequest.put("lastNameCreator", "Shah");
        myPostRequest.put("idCreator", 6);
        myPostRequest.put("postId", 1);
        myPostRequest.put("postTitle", "Arpit_Trip");
        myPostRequest.put("status", PostRequestStatus.PENDING);

        myPostRequests.add(myPostRequest);
    }

    @Override
    public boolean createJoinRequest(int postId) throws Exception {
//        Map<String, Object> myPostRequest = new HashMap<>();
//        myPostRequest.put("requestId", 29);
//        myPostRequest.put("firstNameRequester", "Arpit");
//        myPostRequest.put("lastNameRequester", "Patel");
//        myPostRequest.put("idRequester", 40);
//        myPostRequest.put("postId", postId);

        myPostRequestsDB.put(postId, myPostRequest);

        return postId == (Integer) myPostRequest.get("postId");
    }

    @Override
    public List<Map<String, Object>> getMyPostRequests() {
//        List<Map<String, Object>> myPostRequests = new ArrayList<>();
//        Map<String, Object> myPostRequest = new HashMap<>();
//        myPostRequest.put("requestId", 29);
//        myPostRequest.put("firstNameRequester", "Arpit");
//        myPostRequest.put("lastNameRequester", "Patel");
//        myPostRequest.put("idRequester", 40);
//        myPostRequest.put("postId", 1);
//
//        myPostRequests.add(myPostRequest);
        return myPostRequests;
    }

    @Override
    public List<Map<String, Object>> getPostOwnerDetails(int postId) {
        List<Map<String, Object>> myPostRequestOwnerDetailList = new ArrayList<>();

        Map<String, Object> myPostRequestOwnerDetail = new HashMap<>();
        myPostRequestOwnerDetail.put("requestId", myPostRequests.get(0).get("requestId"));
        myPostRequestOwnerDetail.put("firstNameCreator", myPostRequests.get(0).get("firstNameCreator"));
        myPostRequestOwnerDetail.put("lastNameCreator", myPostRequests.get(0).get("lastNameCreator"));
        myPostRequestOwnerDetail.put("idCreator", myPostRequests.get(0).get("idCreator"));
        myPostRequestOwnerDetail.put("postId", myPostRequests.get(0).get("postId"));

        myPostRequestOwnerDetailList.add(myPostRequestOwnerDetail);
        return myPostRequestOwnerDetailList;
    }

    @Override
    public boolean updateRequest(int requestId, PostRequestStatus postRequestStatus) {
        myPostRequests.get(0).put("status", postRequestStatus);
        return true;
    }

    @Override
    public List<Map<String, Object>> getPostRequesterDetails(int requestId) {
        List<Map<String, Object>> myPostRequestRequesterDetailList = new ArrayList<>();
        Map<String, Object> myPostRequestRequesterDetails = new HashMap<>();
        myPostRequestRequesterDetails.put("requestId", myPostRequests.get(0).get("requestId"));
        myPostRequestRequesterDetails.put("firstNameRequester", myPostRequests.get(0).get("firstNameRequester"));
        myPostRequestRequesterDetails.put("lastNameRequester", myPostRequests.get(0).get("lastNameRequester"));
        myPostRequestRequesterDetails.put("idRequester", myPostRequests.get(0).get("idRequester"));
        myPostRequestRequesterDetails.put("postId", myPostRequests.get(0).get("postId"));

        myPostRequestRequesterDetailList.add(myPostRequestRequesterDetails);
        return myPostRequestRequesterDetailList;
    }

    @Override
    public List<MyPostRequest> getMyRequestByUserId(IMyPostRequestFactory myPostRequestFactory, int userId) {
        List<MyPostRequest> myPostRequestList = new ArrayList<>();
        MyPostRequest myRequest = (MyPostRequest) myPostRequestFactory.makeMyPostRequest();

        myRequest.setStatus((PostRequestStatus) myPostRequest.get("status"));
        myRequest.setPostTitle((String) myPostRequest.get("postTitle"));
        myRequest.setFirstNameCreator((String) myPostRequest.get("firstNameCreator"));
        myRequest.setLastNameCreator((String) myPostRequest.get("lastNameCreator"));

        System.out.println(myRequest.getStatus());
        System.out.println(myRequest.getPostTitle());
        System.out.println(myRequest.getFirstNameCreator());
        System.out.println(myRequest.getLastNameCreator());

        myPostRequestList.add(myRequest);
        return myPostRequestList;
    }
}