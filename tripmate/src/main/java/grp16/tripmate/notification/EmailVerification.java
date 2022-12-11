package grp16.tripmate.notification;

import grp16.tripmate.properties.MyProperties;

public class EmailVerification implements IVerification{

    String emailSender;
    private EmailVerification instance;
//    private String messageSubject = "User Verification for Tripmate";
    private INotification iNotification;
    int uniqueNumber;

    @Override
    public void sendUniqueCode(String userEmail, String body, String subject) throws Exception {
        emailSender = MyProperties.getInstance().getMailSender();
        uniqueNumber = generateNumber();
        body += uniqueNumber;
        iNotification = EmailNotificationFactory.getInstance().createEmailNotification();
        iNotification.sendNotification(emailSender, userEmail, subject, body);
    }

    @Override
    public boolean verifyCode(String code) {
        if (uniqueNumber == Integer.parseInt(code)) {
            return true;
        }
        return false;
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
