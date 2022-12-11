package grp16.tripmate.notification;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.properties.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class EmailNotification implements INotification{
    private final ILogger logger = new MyLoggerAdapter(this);

    JavaMailSenderImpl javaMailSenderImpl = null;
    private JavaMailSender mailSender = null;
    private SimpleMailMessage message;
    private Map<String, String> mailSenderProperties;
    public EmailNotification() throws Exception {
        javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(MyProperties.getInstance().getHost());
        javaMailSenderImpl.setPort(Integer.parseInt(MyProperties.getInstance().getPort()));

        javaMailSenderImpl.setUsername(MyProperties.getInstance().getMailSender());
        javaMailSenderImpl.setPassword(MyProperties.getInstance().getPassword());

        Properties properties = javaMailSenderImpl.getJavaMailProperties();

        for (Map.Entry<String, String> property : this.setMailSenderProperties().entrySet()) {
            properties.put(property.getKey(), property.getValue());
            System.out.println(property.getKey() +" -> "+ property.getValue());
        }

        mailSender = javaMailSenderImpl;
    }

    @Override
    public void sendNotification(String sendTo, String subject, String body) throws Exception {

        String sendBy = MyProperties.getInstance().getMailSender();
        message = new SimpleMailMessage();
        message.setFrom(sendBy);
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        logger.info("Mail sent successfully");
    }

    private Map<String, String> setMailSenderProperties() {
        mailSenderProperties = new HashMap<String, String>();
        mailSenderProperties.put("mail.transport.protocol", "smtp");
        mailSenderProperties.put("mail.smtp.auth", "true");
        mailSenderProperties.put("mail.smtp.starttls.enable", "true");
        mailSenderProperties.put("mail.debug", "true");

        return mailSenderProperties;
    }
}
