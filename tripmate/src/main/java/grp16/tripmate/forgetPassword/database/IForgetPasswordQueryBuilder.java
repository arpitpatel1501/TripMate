package grp16.tripmate.forgetPassword.database;

import grp16.tripmate.user.model.User;

public interface IForgetPasswordQueryBuilder {
    public String checkUserExist(String email);
    public String changeUserPassword(String email, String password);

}
