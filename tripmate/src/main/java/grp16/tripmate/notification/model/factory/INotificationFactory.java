package grp16.tripmate.notification.model.factory;

import grp16.tripmate.notification.model.INotification;
import grp16.tripmate.notification.model.IVerification;

public interface INotificationFactory {
    public INotification createEmailNotification() throws Exception;
    public IVerification createVerificationMethod() throws Exception;
}
