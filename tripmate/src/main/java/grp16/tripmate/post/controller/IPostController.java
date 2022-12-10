package grp16.tripmate.post.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface IPostController {
    String getNewPost(Model model);
    String getAllPosts(Model model);
    String getUserPosts(Model model) throws Exception;
    String viewPost(Model model, int postid) throws Exception;
    String editPost(Model model, @PathVariable("id") int postid);
    String deletePost(Model model, @PathVariable("id") int postid);
    String hidePost(Model model, @PathVariable("id") int postid);
}
