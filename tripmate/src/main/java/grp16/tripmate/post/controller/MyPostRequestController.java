package grp16.tripmate.post.controller;

import grp16.tripmate.post.database.IMyPostRequestDB;
import grp16.tripmate.post.database.MyPostRequestDB;
import grp16.tripmate.post.model.MyPostRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String postRequest(Model model) throws Exception {
        model.addAttribute("requests_count", 2);
        model.addAttribute("title", "Post Request");

        query = iMyPostRequestDB.getPostRequestByUserId(1);
        List<MyPostRequest> postRequests =  myPostRequest.resultMyPostRequests(query);

        model.addAttribute("postRequests", postRequests);
        return "post_requests";
    }
}
