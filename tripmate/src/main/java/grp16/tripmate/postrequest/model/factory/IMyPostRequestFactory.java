package grp16.tripmate.postrequest.model.factory;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.postrequest.persistence.IMyPostRequestPersistence;
import grp16.tripmate.postrequest.persistence.IMyPostRequestQueryGenerator;
import grp16.tripmate.postrequest.model.IMyPostRequest;

public interface IMyPostRequestFactory {
    IMyPostRequest makeMyPostRequest() throws Exception;
    IMyPostRequestPersistence makeMyPostRequestDB();
    IMyPostRequestQueryGenerator makeMyPostRequestQueryBuilder();
    IDatabaseExecutor makeNewDatabaseExecutor();
    ILogger makeNewLogger(Object object);

}
