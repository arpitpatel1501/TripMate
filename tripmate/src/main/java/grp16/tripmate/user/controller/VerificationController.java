package grp16.tripmate.user.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.notification.model.IVerification;
import grp16.tripmate.notification.model.factory.NotificationFactory;
import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.model.factory.IUserFactory;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.factory.UserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class VerificationController {
    private final ILogger logger = new MyLoggerAdapter(this);
    IVerification verification;
    private User user;

    private final IUserDatabase database;

    VerificationController() throws Exception {
        IUserFactory userFactory = UserFactory.getInstance();
        database = userFactory.makeUserDatabase();
        verification = NotificationFactory.getInstance().createVerificationMethod();
    }

    @PostMapping("/register")
    public String userVerification(@ModelAttribute User user) throws Exception {
        verification.sendUniqueCode(user.getUsername(), "Your user verification code is: ", "User Verification for Tripmate");
        this.user = user;
        return "user_verification";
    }

    @PostMapping("/verify")
    public String userVerificationCode(HttpServletRequest request) {
        String code = request.getParameter("code");

        if (this.verification.verifyCode(code)) {
            try {
                boolean isUserCreatedSuccessfully = this.user.createUser(database);
                if (isUserCreatedSuccessfully) {
                    logger.info(this.user.getUsername() + " Register SUCCESS");
                    return "redirect:/login";
                }
            } catch (Exception e) {
                logger.info(e.getMessage());
                e.printStackTrace();
                return "redirect:/error";
            }
        } else {
            logger.error("Register FAILED");
            return "redirect:/error";
        }
        return "redirect:/login";
    }
}