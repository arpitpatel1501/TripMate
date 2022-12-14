package grp16.tripmate.joinRequest.model;

import grp16.tripmate.myPostRequest.model.PostRequestStatus;
import grp16.tripmate.joinRequest.persistence.IMyRequestPersistence;
import grp16.tripmate.joinRequest.model.factory.IMyRequestFactory;

import java.util.List;

public class MyRequest implements IMyRequest {
//    private int id;
    private PostRequestStatus status;   // Required in HTML
    private String postTitle;     // Required in HTML
    private String firstNameCreator;
    private String lastNameCreator;

    public MyRequest() {
    }

    public PostRequestStatus getStatus() {  // Required in HTML
        return status;
    }

    public String getPostTitle() {    // Required in HTML
        return postTitle;
    }

    public String getFirstNameCreator() {
        return firstNameCreator;
    }

    public String getLastNameCreator() {    // Required in HTML
        return lastNameCreator;
    }

    public void setStatus(PostRequestStatus status) {   // Required in HTML
        this.status = status;
    }

    public void setPostTitle(String postTitle) {      // Required in HTML
        this.postTitle = postTitle;
    }

    public void setFirstNameCreator(String firstNameCreator) {
        this.firstNameCreator = firstNameCreator;
    }

    public void setLastNameCreator(String lastNameCreator) {
        this.lastNameCreator = lastNameCreator;
    }


    public List<MyRequest> getMyRequestByUserId(IMyRequestFactory requestFactory, IMyRequestPersistence database, int userId) {
        return database.getMyRequestByUserId(requestFactory, userId);
    }
}
