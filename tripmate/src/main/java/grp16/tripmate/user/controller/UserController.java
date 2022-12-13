package grp16.tripmate.user.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.database.UserDbColumnNames;
import grp16.tripmate.user.model.encoder.IPasswordEncoder;
import grp16.tripmate.user.model.*;
import grp16.tripmate.user.model.factory.IUserFactory;
import grp16.tripmate.user.model.factory.UserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {
    private final ILogger logger = new MyLoggerAdapter(this);
    private final IUserFactory userFactory;
    private final IUserDatabase userDatabase;

    private final IPasswordEncoder passwordEncoder;

    public UserController() {
        userFactory = UserFactory.getInstance();
        userDatabase = UserFactory.getInstance().makeUserDatabase();
        passwordEncoder = UserFactory.getInstance().makePasswordEncoder();
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("user", userFactory.makeNewUser());
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(Model model, @ModelAttribute User user) {
        logger.info(user.toString());
        try {
            user.validateUser(userDatabase, passwordEncoder);
            logger.info(user.getUsername() + " Login SUCCESS");
            return "redirect:/dashboard";
        } catch (InvalidUsernamePasswordException | NoSuchAlgorithmException e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "login";
        }
    }

    @GetMapping("/register")
    public String userRegister(Model model) {
        model.addAttribute("user", userFactory.makeNewUser());
        model.addAttribute("title", "Register");
        return "register";
    }

    @GetMapping("/profile")
    public String userProfile(Model model) throws Exception {
        User loggedInUser = (User) userDatabase.getUserById(SessionManager.getInstance().getLoggedInUserId());
        model.addAttribute("user", loggedInUser);
        logger.info("loaded user: " + loggedInUser);
        model.addAttribute("title", "View/Update Profile");
        return "view_profile";
    }

    @PostMapping("/changeUserDetails")
    public String changeUserDetails(@ModelAttribute User user) throws Exception {
        logger.info("Change user to " + user);
        user.changeUserDetails(userDatabase);
        return "view_profile";
    }

    @GetMapping("/")
    public String loadMainPage() {
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        SessionManager.getInstance().removeValue(UserDbColumnNames.ID);
        return "redirect:/login";
    }
}