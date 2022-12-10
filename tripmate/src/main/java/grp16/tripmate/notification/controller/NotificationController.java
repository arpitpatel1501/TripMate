package grp16.tripmate.notification.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.model.EmailVerificationFactory;
import grp16.tripmate.user.model.IVerification;
import grp16.tripmate.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    private User user;

    public User getUser() {
        return user;
    }

    IVerification iVerification;


}
