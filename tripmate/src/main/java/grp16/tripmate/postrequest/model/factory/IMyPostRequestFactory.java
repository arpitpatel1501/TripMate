package grp16.tripmate.postrequest.model.factory;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.postrequest.database.IMyPostRequestDB;
import grp16.tripmate.postrequest.database.IMyPostRequestQueryBuilder;
import grp16.tripmate.postrequest.model.IMyPostRequest;

public interface IMyPostRequestFactory {
    public IMyPostRequest createMyPostRequest() throws Exception;
    public IMyPostRequestDB createMyPostRequestDB();
    public IMyPostRequestQueryBuilder makeMyPostRequestQueryBuilder();
    public IDatabaseExecutor makeNewDatabaseExecutor();

}
