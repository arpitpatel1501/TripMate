package grp16.tripmate.user.model;

import grp16.tripmate.db.execute.DatabaseExecution;
import grp16.tripmate.db.execute.IDatabaseExecution;
import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.database.IUserQueryBuilder;
import grp16.tripmate.user.database.UserDatabase;
import grp16.tripmate.user.database.UserQueryBuilder;
import grp16.tripmate.user.encoder.IPasswordEncoder;
import grp16.tripmate.user.encoder.PasswordEncoder;

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
    public IUserQueryBuilder getUserQueryBuilder() {
        return UserQueryBuilder.getInstance();
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