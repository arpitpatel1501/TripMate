package grp16.tripmate.user.model;

public interface IVerification {
    public void verification(String userEmail) throws Exception;
    public boolean verifyCode(String code);
}
