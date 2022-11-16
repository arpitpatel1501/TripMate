package grp16.tripmate.user;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class login {
    private final ILogger logger = new MyLogger(this);

    @GetMapping("/greeting")
    public String greeting(Model model) {

        return "greeting";
    }

    @GetMapping("/login")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        logger.info(user.getUsername() + " " + user.getPassword());
        return "greeting";
    }
}