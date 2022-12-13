package grp16.tripmate.postrequest.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IMyPostRequest {
    public void setIdRequest(int idRequest);

    public void setFirstNameRequestee(String firstNameRequestee);

    public void setLastNameRequestee(String lastNameRequestee);
    public void setIdRequestee(int idRequestee);

    public void setPostTitle(String postTitle);

    public void setIdCreator(int idCreator);

    public void setFirstNameCreator(String firstNameCreator);

    public void setLastNameCreator(String lastNameCreator);
    public int getIdRequestee();
    public int getIdRequest();

    public ResultSet resultExecuteQuery(String query) throws Exception;

    public List<IMyPostRequest> resultMyPostRequests(String query) throws Exception;

    boolean executeQuery(String query) throws Exception;
    public boolean updateRequest(String query) throws Exception;
}
