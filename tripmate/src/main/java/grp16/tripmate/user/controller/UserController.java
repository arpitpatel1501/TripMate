package grp16.tripmate.user.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.notification.EmailNotificationFactory;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class UserController {
    private final ILogger logger = new MyLoggerAdapter(this);

    IVerification iVerification;
    private final IUserFactory userFactory;
    private User user;

    public UserController() {
        userFactory = UserFactory.getInstance();
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("user", userFactory.getNewUser());
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(Model model, @ModelAttribute User user) {
        logger.info(user.toString());
        try {
            boolean isValidUser = user.validateUser();

            if (isValidUser) {
                logger.info(user.getUsername() + " Login SUCCESS");
                return "redirect:/dashboard";
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error("Username doesn't exists");
            e.printStackTrace();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/error";
    }

    @GetMapping("/register")
    public String userRegister(Model model) {
        model.addAttribute("user", userFactory.getNewUser());
        model.addAttribute("title", "Register");
        return "register";
    }

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

    @GetMapping("/profile")
    public String userProfile(Model model) throws Exception {
        User loggedInUser = userFactory.getNewUser().getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        logger.info("loaded user: " + loggedInUser);
        model.addAttribute("title", "View/Update Profile");
        return "view_profile";
    }

    @PostMapping("/changeUserDetails")
    public String changeUserDetails(Model model, @ModelAttribute User user) throws Exception {
        logger.info("Change user to " + user);
        user.changeUserDetails();
        return "view_profile";
    }

    @GetMapping("/")
    public String loadMainPage(Model model) {
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        SessionManager.Instance().removeValue(UserDbColumnNames.id);
        return "redirect:/login";
    }
}