package grp16.tripmate.notification;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailNotification implements INotification{
    private final ILogger logger = new MyLoggerAdapter(this);

    private JavaMailSender mailSender;
    private SimpleMailMessage message;
    @Override
    public void sendNotification(String sendBy, String sendTo, String subject, String body) {
        message = new SimpleMailMessage();
        message.setFrom(sendBy);
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        logger.info("Mail sent successfully");
    }
}
