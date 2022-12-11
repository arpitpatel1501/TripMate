package grp16.tripmate.notification;

public interface INotification{
    public void sendNotification(String sendTo, String subject, String body) throws Exception;
}
