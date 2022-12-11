package grp16.tripmate.request.model;

import grp16.tripmate.postrequest.model.PostRequestStatus;

import java.sql.ResultSet;
import java.util.List;

public interface IMyRequest {
    public void setStatus(PostRequestStatus status);

    public void setPostTitle(String postTitle);

    public void setFirstNameCreator(String firstNameCreator);

    public void setLastNameCreator(String lastNameCreator);

    public ResultSet resultExecuteQuery(String query) throws Exception;
    public List<IMyRequest> resultMyRequests(String query) throws Exception;
}
