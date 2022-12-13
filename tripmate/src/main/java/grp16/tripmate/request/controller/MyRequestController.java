package grp16.tripmate.request.controller;

import grp16.tripmate.request.database.IMyRequestDatabase;
import grp16.tripmate.request.model.IMyRequest;
import grp16.tripmate.request.model.IMyRequestFactory;
import grp16.tripmate.request.model.MyRequest;
import grp16.tripmate.request.model.MyRequestFactory;
import grp16.tripmate.session.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MyRequestController {
    private final IMyRequestFactory myRequestFactory;
    private final IMyRequestDatabase myRequestDatabase;
    private final IMyRequest myRequest;

    MyRequestController() {
        myRequestFactory = MyRequestFactory.getInstance();
        myRequestDatabase = myRequestFactory.makeMyRequestDatabase();
        myRequest = myRequestFactory.makeMyRequest();
    }

    @GetMapping("/my_requests")
    public String myRequest(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("title", "My Request");
        try {
            List<MyRequest> myRequestList = myRequest.getMyRequestByUserId(myRequestFactory, myRequestDatabase, SessionManager.getInstance().getLoggedInUserId());
            model.addAttribute("requests_count", myRequestList.size());
            model.addAttribute("myRequests", myRequestList);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            e.printStackTrace();
            return "redirect:/error";
        }
        return "my_requests";
    }
}