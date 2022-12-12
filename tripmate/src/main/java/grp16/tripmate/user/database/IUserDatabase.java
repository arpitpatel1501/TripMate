package grp16.tripmate.user.database;

import grp16.tripmate.user.model.User;

import java.security.NoSuchAlgorithmException;

public interface IUserDatabase {

    boolean insertUser(User user);

    boolean updateUser(User user) throws Exception;

    User getUserById(int userid) throws Exception;

    User getUserByUsername(String username) throws NoSuchAlgorithmException;
}
