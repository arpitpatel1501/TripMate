package grp16.tripmate.user.model;

import grp16.tripmate.user.database.IUserDatabase;

public interface IUser {

    boolean validateUser(IUserDatabase userDatabase) throws Exception;

    boolean createUser(IUserDatabase userDatabase) throws Exception;

    User getLoggedInUser(IUserDatabase userDatabase) throws Exception;

    User getUserById(IUserDatabase userDatabase, int userId) throws Exception;
}
