package grp16.tripmate.postrequest.model;

import java.sql.ResultSet;
import java.util.List;

public interface IMyPostRequest {
    public void setFirstNameRequestee(String firstNameRequestee);

    public void setLastNameRequestee(String lastNameRequestee);

    public void setPostTitle(String postTitle);

    public void setIdCreator(int idCreator);

    public void setFirstNameCreator(String firstNameCreator);

    public void setLastNameCreator(String lastNameCreator);
    public ResultSet resultExecuteQuery(String query) throws Exception;
    public List<IMyPostRequest> resultMyPostRequests(String query) throws Exception;

}
