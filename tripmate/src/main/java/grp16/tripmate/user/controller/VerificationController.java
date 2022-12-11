package grp16.tripmate.user.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.model.EmailVerificationFactory;
import grp16.tripmate.user.model.IVerification;
import grp16.tripmate.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class VerificationController {
    private final ILogger logger = new MyLoggerAdapter(this);
    IVerification iVerification;
    private User user;

    @PostMapping("/register")
    public String userVerification(@ModelAttribute User user) throws Exception {

        iVerification = EmailVerificationFactory.getInstance().createVerificationMethod();
        iVerification.verification(user.getUsername());

        this.user = user;

        return "user_verification";
    }

    @PostMapping("/verify")
    public String userVerificationCode(Model model, HttpServletRequest request) {

        String code = request.getParameter("code");

        if (this.iVerification.verifyCode(code)) {
            try {
                boolean isUserCreatedSuccessfully = this.user.createUser();
                if (isUserCreatedSuccessfully) {
                    logger.info(this.user.getUsername() + " Register SUCCESS");
                    return "redirect:/login";
                } else {
                    logger.error("Register FAILED");
                    return "redirect:/error";
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                model.addAttribute("error", "User Already exists");
                logger.info(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                logger.info(e.getMessage());
                e.printStackTrace();
                return "redirect:/error";
            }
        } else {
            logger.error("Register FAILED");
            return "redirect:/error";
        }
        return "redirect:/error";
    }
}
