package grp16.tripmate.user.database;

public interface IUserQueryBuilder {

    String getUserByUsername(String username);

    String getUserByUserID(int userid);


}
