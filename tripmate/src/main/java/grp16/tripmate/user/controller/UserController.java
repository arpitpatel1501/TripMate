package grp16.tripmate.user.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.database.IUserDatabase;
import grp16.tripmate.user.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final ILogger logger = new MyLoggerAdapter(this);
    private final IUserFactory userFactory;

    private final IUserDatabase database;

    public UserController() {
        userFactory = UserFactory.getInstance();
        database = UserFactory.getInstance().getUserDatabase();
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("user", userFactory.getNewUser());
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user) {
        logger.info(user.toString());
        try {
            boolean isValidUser = user.validateUser(database);

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

    @GetMapping("/profile")
    public String userProfile(Model model) throws Exception {
        User loggedInUser = userFactory.getNewUser().getLoggedInUser(database);
        model.addAttribute("user", loggedInUser);
        logger.info("loaded user: " + loggedInUser);
        model.addAttribute("title", "View/Update Profile");
        return "view_profile";
    }

    @PostMapping("/changeUserDetails")
    public String changeUserDetails(@ModelAttribute User user) throws Exception {
        logger.info("Change user to " + user);
        user.changeUserDetails(database);
        return "view_profile";
    }

    @GetMapping("/")
    public String loadMainPage() {
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        SessionManager.Instance().removeValue(UserDbColumnNames.id);
        return "redirect:/login";
    }
}