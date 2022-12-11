package grp16.tripmate.post.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.post.model.IPostFactory;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.PostFactory;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.UserDbColumnNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/*
 **References
 **https://www.baeldung.com/spring-thymeleaf-request-parameters
 */

@Controller
public class PostController implements IPostController {
    private final ILogger logger;
    private final IPostFactory postFactory;

    PostController() {
        postFactory = PostFactory.getInstance();
        logger = postFactory.getLogger(this);
    }

    @Override
    @GetMapping("/dashboard")
    public String getAllPosts(Model model) {
        model.addAttribute("title", "Dashboard");
        try {
            Post post = (Post) postFactory.getNewPost();
            List<Post> posts = post.getAllPosts();
            model.addAttribute("posts", posts);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return "listposts";
    }

    @Override
    @GetMapping("/createpost")
    public String getNewPost(Model model) {
        Post myPost = (Post) postFactory.getNewPost();
        model.addAttribute("title", "New Post");
        model.addAttribute("post", myPost);
        return "createpost";
    }

    @PostMapping("/createpost")
    public String createPost(Model model, @ModelAttribute Post post) {
        model.addAttribute("title", "Create Post");
        post.setDatabase(postFactory.getPostDatabase());
        try {
            post.createPost();
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "createpost";
        }
    }

    @Override
    @GetMapping("/myposts")
    public String getUserPosts(Model model) {
        model.addAttribute("title", "My Posts");
        try {
            Post post = (Post) postFactory.getNewPost();
            List<Post> posts = post.getPostsByUserId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
            model.addAttribute("posts", posts);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error(e.getMessage());
        }
        return "listposts";
    }

    @Override
    @GetMapping("/viewpost/{id}")
    public String viewPost(Model model, @PathVariable("id") int postId) {
        model.addAttribute("title", "View Post");
        try {
            Post post = (Post) postFactory.getNewPost();
            Post myPost = post.getPostByPostId(postId);
            logger.info(myPost.toString());
            model.addAttribute("isUpdateButtonVisible", myPost.getOwner().getId() == (int) SessionManager.Instance().getValue(UserDbColumnNames.id));
            model.addAttribute("post", myPost);
            model.addAttribute("isFeedbackButtonVisible", myPost.isEligibleForFeedback());
            model.addAttribute("feedbacks", myPost.getFeedbacks());
            model.addAttribute("canJoin", myPost.isEligibleToJoin());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error(e.getMessage());
        }
        return "viewpost";

    }

    @Override
    @GetMapping("/editpost/{id}")
    public String editPost(Model model, @PathVariable("id") int postId) {
        model.addAttribute("title", "Edit Post");
        try {
            Post post = (Post) postFactory.getNewPost();
            Post myPost = post.getPostByPostId(postId);
            model.addAttribute("post", myPost);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return "updatepost";
    }

    @PostMapping("/updatepost")
    public String updatePost(Model model, @ModelAttribute Post post) {
        model.addAttribute("title", "Update Post");
        post.setDatabase(postFactory.getPostDatabase());
        post.updatePost();
        return "redirect:/dashboard";
    }

    @Override
    @PostMapping("/deletepost/{id}")
    public String deletePost(Model model, @PathVariable("id") int postId, RedirectAttributes redirectAttrs) {
        model.addAttribute("title", "Delete Post");
        try {
            Post post = (Post) postFactory.getNewPost();
            Post myPost = post.getPostByPostId(postId);
            myPost.deletePost();
            return "redirect:/dashboard";
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/viewpost/" + postId;
        }
    }

    @Override
    @PostMapping("/hidepost/{id}")
    public String hidePost(Model model, @PathVariable("id") int postId, RedirectAttributes redirectAttrs) {
        try {
            model.addAttribute("title", "Hide Post");
            Post post = (Post) postFactory.getNewPost();
            Post myPost = post.getPostByPostId(postId);
            myPost.hidePost();
            return "redirect:/dashboard";
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/viewpost/" + postId;
        }
    }

    @Override
    @GetMapping("/error")
    public String displayError(Model model) {
        model.addAttribute("error", "Some error has occurred");
        return "error";
    }
}
