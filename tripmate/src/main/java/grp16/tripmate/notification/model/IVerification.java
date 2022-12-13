package grp16.tripmate.notification.model;

public interface IVerification {
    public void sendUniqueCode(String userEmail, String body, String subject) throws Exception;
    public boolean verifyCode(String code);
}
