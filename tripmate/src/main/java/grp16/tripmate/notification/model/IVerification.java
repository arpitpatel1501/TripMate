package grp16.tripmate.notification.model;

public interface IVerification {
    void sendUniqueCode(String userEmail, String body, String subject) throws Exception;
    boolean verifyCode(String code) throws InvalidTokenException;
}
