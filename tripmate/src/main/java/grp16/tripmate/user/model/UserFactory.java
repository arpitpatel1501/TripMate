package grp16.tripmate.user.model;

import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.database.UserDatabase;

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
        return new User(getUserDatabase());
    }

    @Override
    public IUserDatabase getUserDatabase() {
        return new UserDatabase();
    }

    @Override
    public IUserDatabase getUserDatabase() {
        return new UserDatabase();
    }


}
