package grp16.tripmate.notification.model;

public interface INotification{
    public void sendNotification(String sendTo, String subject, String body) throws Exception;
}
