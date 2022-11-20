package grp16.tripmate.post.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;
import grp16.tripmate.post.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ListAllPost {
    private final ILogger logger = new MyLogger(this);

    @GetMapping("/dashboard")
    public String getAllPosts(Model model) {
        model.addAttribute("title", "Dashboard");
//        model.addAttribute("user", new User());
        List<Post> posts = new ArrayList<>(); // TODO: get posts from db
        model.addAttribute("posts", posts);
        return "listposts";
    }

}
