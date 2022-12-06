package grp16.tripmate.user.database;

import grp16.tripmate.user.model.User;

public interface IUserQueryBuilder {

    String getUserByUsername(String username);

    String getUserByUserID(int userid);

    String createUser(User user);

    String changeUserDetails(User user);
}
