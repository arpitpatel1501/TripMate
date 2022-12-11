package grp16.tripmate.postrequest.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.postrequest.database.IMyPostRequestDB;
import grp16.tripmate.postrequest.database.MyPostRequestDB;
import grp16.tripmate.postrequest.model.IMyPostRequest;
import grp16.tripmate.postrequest.model.IMyPostRequestFactory;
import grp16.tripmate.postrequest.model.MyPostRequest;
import grp16.tripmate.postrequest.model.MyPostRequestFactory;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.UserDbColumnNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyPostRequestController {

    private IMyPostRequestDB iMyPostRequestDB;
    private IMyPostRequest myPostRequest;
    private String query;
    private ILogger logger;

    MyPostRequestController() throws Exception {
        iMyPostRequestDB = MyPostRequestDB.getInstance();
        myPostRequest = MyPostRequestFactory.getInstance().createMyPostRequest();
        logger = new MyLoggerAdapter(this);
    }

    @GetMapping("/post_requests")
    public String postRequest(Model model) {
        model.addAttribute("title", "Post Request");
        try {
            query = iMyPostRequestDB.getPostRequestByUserId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
            List<IMyPostRequest> postRequests = myPostRequest.resultMyPostRequests(query);
            model.addAttribute("requests_count", postRequests.size());
            model.addAttribute("postRequests", postRequests);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error(e.getMessage());
        }
        return "post_requests";
    }

    @PostMapping("/join/{id}")
    public String join(Model model, @ModelAttribute Post post, @PathVariable("id") int post_id) throws Exception {
        query = iMyPostRequestDB.createJoinRequest(post_id, (Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
        myPostRequest.executeQuery(query);
        return "redirect:/post_requests";
    }
}
