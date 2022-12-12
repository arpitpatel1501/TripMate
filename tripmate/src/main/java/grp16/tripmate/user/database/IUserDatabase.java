package grp16.tripmate.user.database;

import grp16.tripmate.user.model.User;

public interface IUserDatabase {

    boolean insertUser(User user);

    boolean updateUser(User user) throws Exception;

    User getUserById(int userid) throws Exception;
}
