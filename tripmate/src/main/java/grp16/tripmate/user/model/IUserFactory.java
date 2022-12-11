package grp16.tripmate.user.model;

import grp16.tripmate.user.database.IUserDatabase;

public interface IUserFactory {

    IUser getNewUser();

    IUserDatabase getUserDatabase();
}
