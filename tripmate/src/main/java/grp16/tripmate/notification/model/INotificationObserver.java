package grp16.tripmate.notification.model;

public interface INotificationObserver {
    public void update(String sendTo, String subject, String body) throws Exception;
}
