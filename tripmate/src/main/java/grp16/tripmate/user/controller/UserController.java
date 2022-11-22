package grp16.tripmate.user.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserValidation;
import grp16.tripmate.user.persistance.IUserPersistance;
import grp16.tripmate.user.persistance.SQLPersistance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;

@Controller
public class UserController {
    private final ILogger logger = new MyLogger(this);
    IUserPersistance persistance = new SQLPersistance();
    private UserValidation validator = new UserValidation(persistance);

    @GetMapping("/login")
    public String userLogin(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title", "Login");
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user){
        validator.validateUser(user);
        logger.info(user.getUsername() + " Login SUCCESS");
        return "greeting";
    }

    @GetMapping("/register")
    public String userRegister(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user) throws Exception {
        validator.validateUser(user);
        logger.info(user.getUsername() + " Register SUCCESS");
        return userLogin(user);
    }
}
