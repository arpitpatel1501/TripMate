package grp16.tripmate.forgetPassword.Model;

public interface IForgetPassword {
    public String getEmail();
    public boolean checkUserExist(String email) throws Exception;
    public boolean changeUserPassword(String email, String password) throws Exception;
}
