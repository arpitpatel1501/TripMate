package grp16.tripmate.postrequest.controller;

import grp16.tripmate.post.model.Post;
import grp16.tripmate.postrequest.database.IMyPostRequestDB;
import grp16.tripmate.postrequest.database.MyPostRequestDB;
import grp16.tripmate.postrequest.model.MyPostRequest;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.UserDbColumnNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyPostRequestController {

    private IMyPostRequestDB iMyPostRequestDB;
    private MyPostRequest myPostRequest;
    private String query;

    MyPostRequestController() {
        iMyPostRequestDB = MyPostRequestDB.getInstance();
        myPostRequest = new MyPostRequest();
    }

    @GetMapping("/post_requests")
    public String postRequest(Model model) {
        model.addAttribute("title", "Post Request");

        try {
            query = iMyPostRequestDB.getPostRequestByUserId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
            List<MyPostRequest> postRequests = myPostRequest.resultMyPostRequests(query);
            model.addAttribute("requests_count", postRequests.size());

            model.addAttribute("postRequests", postRequests);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "post_requests";
    }

    @PostMapping("/join")
    public String join(Model model, @ModelAttribute Post post) {
        SessionManager.Instance().removeValue(UserDbColumnNames.id);
        return "redirect:/login";
    }
}
