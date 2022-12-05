package grp16.tripmate.post.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.IPost;
import grp16.tripmate.post.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController implements IPostController {
    private final ILogger logger = new MyLoggerAdapter(this);

    final private IPost post;

    PostController() {
        post = new Post();
    }

    @GetMapping("/dashboard")
    public String getAllPosts(Model model) {
        model.addAttribute("title", "Dashboard");
        List<Post> posts = post.getAllPosts();
        model.addAttribute("posts", posts);
        return "listposts";
    }

    @GetMapping("/myposts")
    public String getUserPosts(Model model) {
        model.addAttribute("title", "My Posts");
        List<Post> posts = post.getPostsByUserId(1);
        model.addAttribute("posts", posts);
        return "listposts";
    }

    @GetMapping("/viewpost")
    public String viewPost(Model model, @RequestParam(name="postid") int postid) {
        model.addAttribute("title", "View Post");
        Post myPost = post.getPostByPostId(postid);
        model.addAttribute("post", myPost);
        return "viewpost";
    }
}
