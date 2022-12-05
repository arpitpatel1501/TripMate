package grp16.tripmate.post.controller;

import org.springframework.ui.Model;

public interface IPostController {
    String getAllPosts(Model model);

    String getUserPosts(Model model);

    String viewPost(Model model, int postid);
}
