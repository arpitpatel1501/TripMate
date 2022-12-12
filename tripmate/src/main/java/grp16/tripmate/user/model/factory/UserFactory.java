package grp16.tripmate.user.model.factory;

import grp16.tripmate.db.execute.DatabaseExecution;
import grp16.tripmate.db.execute.IDatabaseExecution;
import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.database.IUserQueryGenerator;
import grp16.tripmate.user.database.UserDatabase;
import grp16.tripmate.user.database.UserQueryGenerator;
import grp16.tripmate.user.encoder.IPasswordEncoder;
import grp16.tripmate.user.encoder.PasswordEncoder;
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
    public IUser getNewUser() {
        return new User();
    }

    @Override
    public IUserDatabase getUserDatabase() {
        return new UserDatabase(getUserQueryBuilder(), getNewDatabaseExecutor(), getPasswordEncoder());
    }

    @Override
    public IUserQueryGenerator getUserQueryBuilder() {
        return UserQueryGenerator.getInstance();
    }

    @Override
    public IDatabaseExecution getNewDatabaseExecutor() {
        return new DatabaseExecution();
    }

    @Override
    public IPasswordEncoder getPasswordEncoder() {
        return PasswordEncoder.getInstance();
    }
}