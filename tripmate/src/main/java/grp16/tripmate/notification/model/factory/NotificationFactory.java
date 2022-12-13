package grp16.tripmate.notification.model.factory;

import grp16.tripmate.notification.model.EmailNotification;
import grp16.tripmate.notification.model.EmailVerification;
import grp16.tripmate.notification.model.INotification;
import grp16.tripmate.notification.model.IVerification;

public class NotificationFactory implements INotificationFactory {
    private static INotificationFactory intance;
    private NotificationFactory() {

    }
    public static INotificationFactory getInstance() {
        if (intance == null) {
            intance = new NotificationFactory();
        }
        return intance;
    }

    @Override
    public INotification createEmailNotification() throws Exception {
        return new EmailNotification();
    }
    @Override
    public IVerification createVerificationMethod() throws Exception {
        return new EmailVerification();
    }
}
