package grp16.tripmate.notification.model;

import grp16.tripmate.notification.model.factory.NotificationFactory;

public class EmailVerification implements IVerification{

    String emailSender;
    private EmailVerification instance;
//    private String messageSubject = "User Verification for Tripmate";
    private INotification iNotification;
    int uniqueNumber;

    @Override
    public void sendUniqueCode(String userEmail, String body, String subject) throws Exception {
        uniqueNumber = generateNumber();
        body += uniqueNumber;
        iNotification = NotificationFactory.getInstance().createEmailNotification();
        iNotification.sendNotification(userEmail, subject, body);
    }

    @Override
    public boolean verifyCode(String code) throws InvalidTokenException{
        if (uniqueNumber == Integer.parseInt(code)) {
            return true;
        }
        else {
            throw new InvalidTokenException();
        }
//        return false;
    }

    private int generateNumber() {
        int min = 1000;
        int max = 9999;
        int range = (max-min+1);
        double random = Math.random();
        double finalNumber = Math.floor((random * range) + min);
//        int number = (int)Math.floor(Math.random()*(max-min+1)+min);
        return (int) finalNumber;
    }
}
