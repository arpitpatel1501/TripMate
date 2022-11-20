package grp16.tripmate.post.controller;

import grp16.tripmate.post.model.Post;
import grp16.tripmate.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ListUserPosts {
    @GetMapping("/posts")
    public String getUserPosts(Model model) {
        model.addAttribute("title", "My Posts");
//        model.addAttribute("user", new User());
        List<Post> posts = new ArrayList<>(); // TODO: get posts from db
        posts.add(new Post(1, new User(), "title 1", 5, "source 1", "destination 1", new Date(), new Date(), 15, 25, "description 1", false));
        posts.add(new Post(2, new User(), "title 2", 10, "source 2", "destination 2", new Date(), new Date(), 25, 35, "description 2", false));
        model.addAttribute("posts", posts);
        return "listposts";
    }
}
