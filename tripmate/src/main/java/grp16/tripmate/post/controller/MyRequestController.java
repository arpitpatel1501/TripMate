package grp16.tripmate.post.controller;

import grp16.tripmate.post.database.IMyRequestDB;
import grp16.tripmate.post.database.MyRequestDB;
import grp16.tripmate.post.model.MyRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyRequestController {
    private IMyRequestDB iMyRequestDB;
    private MyRequest myRequest;
    private String query;

    MyRequestController() {
        iMyRequestDB = MyRequestDB.getInstance();
        myRequest = new MyRequest();
    }

    @GetMapping("/my_requests")
    public String myRequest(Model model) throws Exception {
        model.addAttribute("title", "My Request");

        query = iMyRequestDB.getMyRequestByUserId(1);
        List<MyRequest> myRequestList =  myRequest.resultMyRequests(query);
        model.addAttribute("requests_count", myRequestList.size());

        model.addAttribute("myRequests", myRequestList);
        return "my_requests";
    }
}
