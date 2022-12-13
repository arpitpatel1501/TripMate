package grp16.tripmate.user.model.factory;

import grp16.tripmate.db.execute.DatabaseExecutor;
import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.database.IUserQueryGenerator;
import grp16.tripmate.user.database.UserDatabase;
import grp16.tripmate.user.database.UserQueryGenerator;
import grp16.tripmate.user.model.encoder.IPasswordEncoder;
import grp16.tripmate.user.model.encoder.PasswordEncoder;
import grp16.tripmate.user.model.IUser;
import grp16.tripmate.user.model.User;

public class UserFactory implements IUserFactory {
    private static IUserFactory instance;

    private UserFactory() {
        //Required private empty constructor
    }

    public static IUserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }

    @Override
    public IUser makeNewUser() {
        return new User();
    }

    @Override
    public IUserDatabase makeUserDatabase() {
        return new UserDatabase(makeUserQueryBuilder(), makeNewDatabaseExecutor());
    }

    @Override
    public IUserQueryGenerator makeUserQueryBuilder() {
        return UserQueryGenerator.getInstance();
    }

    @Override
    public IDatabaseExecutor makeNewDatabaseExecutor() {
        return new DatabaseExecutor();
    }

    @Override
    public IPasswordEncoder makePasswordEncoder() {
        return PasswordEncoder.getInstance();
    }

    @Override
    public ILogger makeLogger(Object obj) {
        return new MyLoggerAdapter(obj);
    }

}