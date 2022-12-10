package grp16.tripmate.postrequest.model;

import java.sql.ResultSet;
import java.util.List;

public interface IMyPostRequest {
    public ResultSet resultExecuteQuery(String query) throws Exception;
    public List<MyPostRequest> resultMyPostRequests(String query) throws Exception;

}
