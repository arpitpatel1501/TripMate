package grp16.tripmate.post.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IPostController {
    String getNewPost(Model model);

    String getAllPosts(Model model);

    String getUserPosts(Model model) throws Exception;

    String viewPost(Model model, int postId) throws Exception;

    String editPost(Model model, @PathVariable("id") int postId);

    String deletePost(Model model, @PathVariable("id") int postId, RedirectAttributes redirectAttrs);

    String hidePost(Model model, @PathVariable("id") int postId, RedirectAttributes redirectAttrs);

    String displayError(Model model);
}
