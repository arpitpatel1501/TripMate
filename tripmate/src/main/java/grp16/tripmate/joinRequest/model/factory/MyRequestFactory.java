package grp16.tripmate.joinRequest.model.factory;

import grp16.tripmate.db.execute.DatabaseExecutor;
import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.joinRequest.persistence.IMyRequestPersistence;
import grp16.tripmate.joinRequest.persistence.IMyRequestQueryGenerator;
import grp16.tripmate.joinRequest.persistence.MyRequestPersistence;
import grp16.tripmate.joinRequest.persistence.MyRequestQueryGenerator;
import grp16.tripmate.joinRequest.model.IMyRequest;
import grp16.tripmate.joinRequest.model.MyRequest;

public class MyRequestFactory implements IMyRequestFactory {
    private static IMyRequestFactory instance = null;

    private MyRequestFactory() {

    }
    public static IMyRequestFactory getInstance() {
        if (instance == null) {
            instance = new MyRequestFactory();
        }
        return instance;
    }

    @Override
    public IMyRequest makeMyRequest() {
        return new MyRequest();
    }

    @Override
    public ILogger makeLogger(Object classObj) {
        return new MyLoggerAdapter(classObj);
    }

    @Override
    public IMyRequestPersistence makeMyRequestDatabase() {
        return new MyRequestPersistence(makeDatabaseExecutor(), makeMyRequestQueryGenerator());
    }

    private IDatabaseExecutor makeDatabaseExecutor() {
        return new DatabaseExecutor();
    }

    private IMyRequestQueryGenerator makeMyRequestQueryGenerator() {
        return MyRequestQueryGenerator.getInstance();
    }
}
