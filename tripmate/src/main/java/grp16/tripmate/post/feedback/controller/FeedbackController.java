package grp16.tripmate.post.feedback.controller;

import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.post.feedback.model.IFeedback;
import grp16.tripmate.post.model.IPostFactory;
import grp16.tripmate.post.model.PostFactory;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.UserDbColumnNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedbackController {

    final private IFeedback feedback;

    private IPostFactory postFactory;

    public FeedbackController() {
        postFactory = PostFactory.getInstance();
        feedback = postFactory.getNewFeedback();
    }

    @GetMapping("/feedback/{id}")
    public String loadFeedbackPage(Model model, @PathVariable("id") int postId) {
        try {
            model.addAttribute("post", postFactory.getNewPost().getPostByPostId(postId));
            model.addAttribute("currentFeedback", new Feedback());
            model.addAttribute("title", "Feedback");
        } catch (Exception e) {
            model.addAttribute("error", "Post not found " + e.getMessage());
            e.printStackTrace();
        }
        return "feedback";
    }


    @PostMapping("/feedback/{id}")
    public String createFeedback(Model model, @PathVariable("id") int postId, @ModelAttribute Feedback feedback) {
        try {
            feedback.setPostId(postId);
            feedback.setUser((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
            feedback.createFeedback();
        } catch (Exception e) {
            return "redirect:/error";
        }
        return "redirect:/dashboard";

    }
}