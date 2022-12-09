package grp16.tripmate.post.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.IPost;
import grp16.tripmate.post.model.IPostFactory;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.PostFactory;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.UserDbColumnNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class PostController implements IPostController {
    private final ILogger logger = new MyLoggerAdapter(this);

    final private IPostFactory postFactory;

    private final IPost post;

    PostController() {
        postFactory = PostFactory.getInstance();
        post = postFactory.getNewPost();
    }

    @GetMapping("/dashboard")
    public String getAllPosts(Model model) {
        model.addAttribute("title", "Dashboard");
        List<Post> posts = post.getAllPosts();
        model.addAttribute("posts", posts);
        return "listposts";
    }

    @GetMapping("/createpost")
    public String getNewPost(Model model) {
        Post myPost = postFactory.getNewPost();
        model.addAttribute("title", "New Post");
        model.addAttribute("post", myPost);
        return "createpost";
    }

    @PostMapping("/createpost")
    public String createPost(Model model, @ModelAttribute Post post) {
        model.addAttribute("title", "Create Post");
        try {
            post.createPost();
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "createpost";
        }
    }

    @GetMapping("/myposts")
    public String getUserPosts(Model model) {
        model.addAttribute("title", "My Posts");
        try {
            List<Post> posts = post.getPostsByUserId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
            model.addAttribute("posts", posts);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "listposts";
    }

    @GetMapping("/viewpost/{id}")
    public String viewPost(Model model, @PathVariable("id") int postid) {
        model.addAttribute("title", "View Post");
        try {
            Post myPost = post.getPostByPostId(postid);
            logger.info(myPost.toString());
            model.addAttribute("isUpdateButtonVisible", myPost.getOwner().getId() == (int) SessionManager.Instance().getValue(UserDbColumnNames.id));
            model.addAttribute("post", myPost);
            model.addAttribute("isFeedbackButtonVisible", myPost.isEligibleForFeedback());
            model.addAttribute("feedbacks", myPost.getFeedbacks());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "viewpost";

    }

    @GetMapping("/editpost/{id}")
    public String editPost(Model model, @PathVariable("id") int postid) {
        model.addAttribute("title", "Edit Post");
        Post myPost = post.getPostByPostId(postid);
        model.addAttribute("post", myPost);
        return "updatepost";
    }

    @PostMapping("/updatepost/{id}")
    public String udpatePost(Model model, @PathVariable("id") int postid, @ModelAttribute Post post) {
        model.addAttribute("title", "Update Post");
        post.updatePost();
        return "redirect:/dashboard";
    }

    @PostMapping("/deletepost/{id}")
    public String deletePost(Model model, @PathVariable("id") int postid, @ModelAttribute Post post) {
        model.addAttribute("title", "Delete Post");
        post.deletePost();
        return "redirect:/dashboard";
    }

    @PostMapping("/hidepost/{id}")
    public String hidePost(Model model, @PathVariable("id") int postid, @ModelAttribute Post post) {
        model.addAttribute("title", "Hide Post");
        post.hidePost();
        return "redirect:/dashboard";
    }

    @GetMapping("/error")
    public String hidePost(Model model) {
        model.addAttribute("error", "Some error has occurred");
        return "error";
    }
}
