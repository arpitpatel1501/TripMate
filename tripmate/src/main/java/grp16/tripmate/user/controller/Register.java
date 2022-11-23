package grp16.tripmate.user.controller;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Register {
    private final ILogger logger = new MyLoggerAdapter(this);

    IDatabaseConnection IDatabaseConnection;

    Register() {
        this.IDatabaseConnection = new DatabaseConnection();
    }

    @GetMapping("/register")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Register");

        return "register";
    }

    @PostMapping("/register")
    public String greetingSubmit(@ModelAttribute User user, Model model) throws Exception {
        model.addAttribute("user", user);
        logger.info(user.getUsername() + " " + user.getPassword());
//        Connection connection = databaseConnectionDAO.getDatabaseConnection();
//        connection.createStatement();
//        connection.close();
        return "login";
    }
}
