package grp16.tripmate.notification;

public interface INotification{
    public void sendNotification(String sendBy, String sendTo, String subject, String body);
}
