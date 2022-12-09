package grp16.tripmate.notification;

public interface INotificationFactory {
    public INotification createEmailNotification() throws Exception;
}
