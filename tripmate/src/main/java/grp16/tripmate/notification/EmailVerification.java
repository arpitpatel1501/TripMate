package grp16.tripmate.notification;

import grp16.tripmate.properties.MyProperties;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailVerification implements IVerification{

    String emailSender;
    String userEmail;
    private JavaMailSender mailSender;
    public EmailVerification () throws Exception {
        emailSender = MyProperties.getInstance().getMailSender();
    }
    @Override
    public void verification(String userEmail) {

    }
}
