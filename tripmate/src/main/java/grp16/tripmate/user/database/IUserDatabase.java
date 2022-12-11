package grp16.tripmate.user.database;

import grp16.tripmate.user.model.User;

import java.util.List;
import java.util.Map;

public interface IUserDatabase {
    boolean validateUser(User user) throws Exception;

    boolean createUser(User user);

    User getLoggedInUser() throws Exception;

    boolean changeUserDetails(User user) throws Exception;

    User getUserById(int userid) throws Exception;
}
