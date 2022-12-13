package grp16.tripmate.request.model;

import grp16.tripmate.db.execute.DatabaseExecutor;
import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.request.database.IMyRequestDatabase;
import grp16.tripmate.request.database.IMyRequestQueryGenerator;
import grp16.tripmate.request.database.MyRequestDatabase;
import grp16.tripmate.request.database.MyRequestQueryGenerator;

public class MyRequestFactory implements IMyRequestFactory {
    private static IMyRequestFactory instance = null;

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
    public IMyRequestDatabase makeMyRequestDatabase() {
        return new MyRequestDatabase(makeDatabaseExecutor(), makeMyRequestQueryGenerator());
    }

    private IDatabaseExecutor makeDatabaseExecutor() {
        return new DatabaseExecutor();
    }

    private IMyRequestQueryGenerator makeMyRequestQueryGenerator() {
        return MyRequestQueryGenerator.getInstance();
    }
}
