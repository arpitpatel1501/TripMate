package grp16.tripmate.forgetPassword.Model;

public interface IForgetPassword {
    public boolean checkUserExist(String email) throws Exception;
    public void changeUserPassword() throws Exception;
}
