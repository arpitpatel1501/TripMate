package grp16.tripmate.request.model;

import grp16.tripmate.postrequest.model.PostRequestStatus;
import grp16.tripmate.request.database.IMyRequestDatabase;
import grp16.tripmate.request.model.factory.IMyRequestFactory;

import java.util.List;

public class MyRequest implements IMyRequest {
    private int id;
    private PostRequestStatus status;
    private String postTitle;
    private String firstNameCreator;
    private String lastNameCreator;

    public MyRequest() {
    }

    public PostRequestStatus getStatus() {
        return status;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getFirstNameCreator() {
        return firstNameCreator;
    }

    public String getLastNameCreator() {
        return lastNameCreator;
    }

    public void setStatus(PostRequestStatus status) {
        this.status = status;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setFirstNameCreator(String firstNameCreator) {
        this.firstNameCreator = firstNameCreator;
    }

    public void setLastNameCreator(String lastNameCreator) {
        this.lastNameCreator = lastNameCreator;
    }


    public List<MyRequest> getMyRequestByUserId(IMyRequestFactory requestFactory, IMyRequestDatabase database, int userId) {
        return database.getMyRequestByUserId(requestFactory, userId);
    }
}
