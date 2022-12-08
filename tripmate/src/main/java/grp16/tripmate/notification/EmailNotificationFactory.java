package grp16.tripmate.notification;

public class EmailNotificationFactory implements INotificationFactory{

    private static INotificationFactory intance;
    public static INotificationFactory getInstance() {
        if (intance == null) {
            intance = new EmailNotificationFactory();
        }
        return intance;
    }
    @Override
    public INotification createEmailNotification() {
        return new EmailNotification();
    }
}
