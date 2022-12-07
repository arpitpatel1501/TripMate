package grp16.tripmate.user.model;

public interface IUser {

    boolean validateUser() throws Exception;

    boolean createUser() throws Exception;

    User getLoggedInUser() throws Exception;

    User getUserById(int userid) throws Exception;
}
