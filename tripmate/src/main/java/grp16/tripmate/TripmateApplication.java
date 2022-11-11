package grp16.tripmate;

import grp16.tripmate.db.properties.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class TripmateApplication {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TripmateApplication.class, args);
        Properties properties = Properties.getInstance();
        properties.setProfileProperties(properties.getActiveProfile());
    }
}