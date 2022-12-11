package grp16.tripmate.user.model;

import grp16.tripmate.notification.EmailNotificationFactory;
import grp16.tripmate.notification.INotification;
import grp16.tripmate.notification.INotificationFactory;
import grp16.tripmate.properties.MyProperties;

import java.sql.SQLOutput;

public class EmailVerification implements IVerification{

    String emailSender;
    private EmailVerification instance;
    private String messageSubject = "User Verification for Tripmate";
    private INotification iNotification;
    int uniqueNumber;

    @Override
    public void verification(String userEmail) throws Exception {
        emailSender = MyProperties.getInstance().getMailSender();
        uniqueNumber = generateNumber();
        String body = "Your verification code is: " + uniqueNumber;
        iNotification = EmailNotificationFactory.getInstance().createEmailNotification();
        iNotification.sendNotification(emailSender, userEmail, messageSubject, body);
    }

    @Override
    public boolean verifyCode(String code) {
        System.out.println("----- in verifyCode ---------");
        System.out.println("uniqueNumber: "+ uniqueNumber);
        System.out.println("code: "+ code);
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
