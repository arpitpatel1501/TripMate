package grp16.tripmate.postrequest.model.factory;

import grp16.tripmate.db.execute.DatabaseExecutor;
import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.postrequest.persistence.IMyPostRequestPersistence;
import grp16.tripmate.postrequest.persistence.IMyPostRequestQueryGenerator;
import grp16.tripmate.postrequest.persistence.MyPostRequestPersistence;
import grp16.tripmate.postrequest.persistence.MyPostRequestQueryGenerator;
import grp16.tripmate.postrequest.model.IMyPostRequest;
import grp16.tripmate.postrequest.model.MyPostRequest;

public class MyPostRequestFactory implements IMyPostRequestFactory{

    private static IMyPostRequestFactory instance = null;

    private MyPostRequestFactory() {

    }
    public static IMyPostRequestFactory getInstance() {
        if (instance == null) {
            instance = new MyPostRequestFactory();
        }
        return instance;

    }

    @Override
    public IMyPostRequest makeMyPostRequest() {
        return new MyPostRequest();
    }

    @Override
    public IMyPostRequestPersistence makeMyPostRequestDB() {
        return new MyPostRequestPersistence(makeMyPostRequestQueryBuilder(), makeNewDatabaseExecutor());
    }

    @Override
    public IMyPostRequestQueryGenerator makeMyPostRequestQueryBuilder() {
        return MyPostRequestQueryGenerator.getInstance();
    }

    @Override
    public IDatabaseExecutor makeNewDatabaseExecutor() {
        return new DatabaseExecutor();
    }

    @Override
    public ILogger makeNewLogger(Object object) {
        return new MyLoggerAdapter(object);
    }

}
