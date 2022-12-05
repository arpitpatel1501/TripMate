package grp16.tripmate.user.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.IUser;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final ILogger logger = new MyLoggerAdapter(this);


    public UserController() {
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user) throws Exception {
        logger.info(user.toString());
        boolean isValidUser = user.validateUser();

        if (isValidUser) {
            logger.info(user.getUsername() + " Login SUCCESS");
            return "greeting";
        } else {
            logger.error(user.getUsername() + " Login FAILED");
            return "error";
        }
    }

    @GetMapping("/register")
    public String userRegister(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user) throws Exception {
        boolean isUserCreatedSuccessfully = user.createUser();
        if (isUserCreatedSuccessfully) {
            logger.info(user.getUsername() + " Register SUCCESS");
            return "login";
        } else {
            logger.error("Register FAILED");
            return "error";
        }
    }

    @GetMapping("/profile")
    public String userProfile(Model model) throws Exception {
        model.addAttribute("user", new User().getLoggedInUser());
        model.addAttribute("title", "Register");
        return "view_profile";
    }

    @PostMapping("/changePassword")
    public String changePassword(Model model, @ModelAttribute User user) throws Exception {
        logger.info("Password changing to " + user.getPassword());
        user.changePassword();
        return "view_profile";
    }
}
