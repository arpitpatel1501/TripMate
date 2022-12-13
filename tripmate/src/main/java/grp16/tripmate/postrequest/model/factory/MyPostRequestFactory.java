package grp16.tripmate.postrequest.model.factory;

import grp16.tripmate.db.execute.DatabaseExecutor;
import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.postrequest.database.IMyPostRequestDB;
import grp16.tripmate.postrequest.database.IMyPostRequestQueryBuilder;
import grp16.tripmate.postrequest.database.MyPostRequestDB;
import grp16.tripmate.postrequest.database.MyPostRequestQueryBuilder;
import grp16.tripmate.postrequest.model.IMyPostRequest;
import grp16.tripmate.postrequest.model.MyPostRequest;
import grp16.tripmate.user.database.IUserQueryGenerator;
import grp16.tripmate.user.database.UserQueryGenerator;

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
    public IMyPostRequest createMyPostRequest() throws Exception {
        return new MyPostRequest();
    }

    @Override
    public IMyPostRequestDB createMyPostRequestDB() {
        return new MyPostRequestDB(makeMyPostRequestQueryBuilder(), makeNewDatabaseExecutor());
    }

    @Override
    public IMyPostRequestQueryBuilder makeMyPostRequestQueryBuilder() {
        return MyPostRequestQueryBuilder.getInstance();
    }

    @Override
    public IDatabaseExecutor makeNewDatabaseExecutor() {
        return new DatabaseExecutor();
    }

}
