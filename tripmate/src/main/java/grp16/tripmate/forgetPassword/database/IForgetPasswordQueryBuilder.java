package grp16.tripmate.forgetPassword.database;

public interface IForgetPasswordQueryBuilder {
    String checkUserExist(String email);
    String changeUserPassword(String email, String password);

}
