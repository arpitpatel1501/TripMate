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
    public void setEmailRequestee(String emailRequestee);
    public void setFirstNameCreator(String firstNameCreator);

    public void setLastNameCreator(String lastNameCreator);
    public void setEmailCreator(String emailCreator);
    public String getPostTitle();
    public String getEmailCreator();
    public int getIdRequestee();
    public int getIdRequest();
    public String getFirstNameCreator();
    public String getLastNameCreator();
    public String getEmailRequestee();

    public ResultSet resultExecuteQuery(String query) throws Exception;
    public List<IMyPostRequest> resultMyPostRequests(String query) throws Exception;
    public IMyPostRequest resultPostOwnerDetails(String query) throws Exception;
    public IMyPostRequest resultPostRequesteeDetails(String query) throws Exception;

    boolean executeQuery(String query) throws Exception;
    public boolean updateRequest(String query) throws Exception;
}
