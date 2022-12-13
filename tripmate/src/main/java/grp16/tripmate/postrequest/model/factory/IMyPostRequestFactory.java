package grp16.tripmate.postrequest.model.factory;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.postrequest.database.IMyPostRequestDB;
import grp16.tripmate.postrequest.database.IMyPostRequestQueryBuilder;
import grp16.tripmate.postrequest.model.IMyPostRequest;

public interface IMyPostRequestFactory {
    IMyPostRequest makeMyPostRequest() throws Exception;
    IMyPostRequestDB makeMyPostRequestDB();
    IMyPostRequestQueryBuilder makeMyPostRequestQueryBuilder();
    IDatabaseExecutor makeNewDatabaseExecutor();
    ILogger makeNewLogger(Object object);

}
