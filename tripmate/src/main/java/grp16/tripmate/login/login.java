package grp16.tripmate.login;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class login {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


}