package grp16.tripmate.myPostRequest.model;

import grp16.tripmate.myPostRequest.model.factory.MyPostRequestFactory;
import grp16.tripmate.myPostRequest.persistence.IMyPostRequestPersistence;
import grp16.tripmate.myPostRequest.persistence.MyPostRequestDatabaseMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


class MyPostRequestTest {

    private IMyPostRequestPersistence databaseMock;
    private MyPostRequestFactory factory;
    private MyPostRequest myPostRequest;

    public MyPostRequestTest() {
        databaseMock = new MyPostRequestDatabaseMock();
        factory = (MyPostRequestFactory) MyPostRequestFactory.getInstance();
        myPostRequest = (MyPostRequest) MyPostRequestFactory.getInstance().makeMyPostRequest();
    }

    @Test
    void createJoinRequestTest() throws Exception {
        int postId = 1;
        Assertions.assertTrue(myPostRequest.createJoinRequest(databaseMock, postId));
    }
    @Test
    void getMyPostRequestsTest() throws Exception {
        List<Map<String, Object>> myPostRequests = databaseMock.getMyPostRequests();
        Map<String, Object> myPostRequest = myPostRequests.get(0);

        Assertions.assertEquals(29, myPostRequest.get("requestId"));
        Assertions.assertEquals("Arpit", myPostRequest.get("firstNameRequester"));
        Assertions.assertEquals("Patel", myPostRequest.get("lastNameRequester"));
        Assertions.assertEquals(9, myPostRequest.get("idRequester"));
        Assertions.assertEquals(1, myPostRequest.get("postId"));
    }

    @Test
    void getMyPostRequestsNegativeTest() throws Exception {
        List<Map<String, Object>> myPostRequests = databaseMock.getMyPostRequests();
        Map<String, Object> myPostRequest = myPostRequests.get(0);

        Assertions.assertNotEquals(90, myPostRequest.get("requestId"));
        Assertions.assertNotEquals("Shreya", myPostRequest.get("firstNameRequester"));
        Assertions.assertNotEquals("PATIL", myPostRequest.get("lastNameRequester"));
        Assertions.assertNotEquals(80, myPostRequest.get("idRequester"));
        Assertions.assertNotEquals(2, myPostRequest.get("postId"));
    }

    @Test
    void getPostOwnerDetailsTest() {
        int postId = 1;
        List<Map<String, Object>> myPostRequests = databaseMock.getPostOwnerDetails(postId);
        Map<String, Object> myPostRequest = myPostRequests.get(0);

        Assertions.assertEquals(29, myPostRequest.get("requestId"));
        Assertions.assertEquals("Aman", myPostRequest.get("firstNameCreator"));
        Assertions.assertEquals("Shah", myPostRequest.get("lastNameCreator"));
        Assertions.assertEquals(6, myPostRequest.get("idCreator"));
        Assertions.assertEquals(postId, myPostRequest.get("postId"));
    }

    @Test
    void getPostOwnerDetailsNegativeTest() {
        int postId = 9;
        List<Map<String, Object>> myPostRequests = databaseMock.getPostOwnerDetails(postId);
        Map<String, Object> myPostRequest = myPostRequests.get(0);

        Assertions.assertNotEquals(7, myPostRequest.get("requestId"));
        Assertions.assertNotEquals("Arpit", myPostRequest.get("firstNameCreator"));
        Assertions.assertNotEquals("Patel", myPostRequest.get("lastNameCreator"));
        Assertions.assertNotEquals(1, myPostRequest.get("idCreator"));
        Assertions.assertNotEquals(postId, myPostRequest.get("postId"));
    }

    @Test
    void updateRequestTest() {
        boolean result = databaseMock.updateRequest(1, PostRequestStatus.ACCEPT);
        Assertions.assertTrue(result);
    }

    @Test
    void getPostRequesterDetailsTest() {
        int postId = 1;
        List<Map<String, Object>> myPostRequests = databaseMock.getPostRequesterDetails(postId);
        Map<String, Object> myPostRequest = myPostRequests.get(0);

        Assertions.assertEquals(29, myPostRequest.get("requestId"));
        Assertions.assertEquals("Arpit", myPostRequest.get("firstNameRequester"));
        Assertions.assertEquals("Patel", myPostRequest.get("lastNameRequester"));
        Assertions.assertEquals(9, myPostRequest.get("idRequester"));
        Assertions.assertEquals(postId, myPostRequest.get("postId"));
    }

    @Test
    void getPostRequesterDetailsNegativeTest() {
        int postId = 2;
        List<Map<String, Object>> myPostRequests = databaseMock.getPostRequesterDetails(postId);
        Map<String, Object> myPostRequest = myPostRequests.get(0);

        Assertions.assertNotEquals(2, myPostRequest.get("requestId"));
        Assertions.assertNotEquals("Aman", myPostRequest.get("firstNameRequester"));
        Assertions.assertNotEquals("Shah", myPostRequest.get("lastNameRequester"));
        Assertions.assertNotEquals(10, myPostRequest.get("idRequester"));
        Assertions.assertNotEquals(postId, myPostRequest.get("postId"));
    }

    @Test
    void getMyRequestByUserId() {
    }
    @Test
    void listToMyPostRequest() {

    }

    @Test
    void listToMyPostRequestPostOwner() {
    }

    @Test
    void listToPostRequesterDetails() {
    }


}