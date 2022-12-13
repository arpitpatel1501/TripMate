package grp16.tripmate.postrequest.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.notification.model.factory.NotificationFactory;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.postrequest.database.IMyPostRequestDB;
import grp16.tripmate.postrequest.model.IMyPostRequest;
import grp16.tripmate.postrequest.model.factory.IMyPostRequestFactory;
import grp16.tripmate.postrequest.model.factory.MyPostRequestFactory;
import grp16.tripmate.postrequest.model.PostRequestStatus;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.database.UserDbColumnNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MyPostRequestController {

    private IMyPostRequestFactory myPostRequestFactory;
    private IMyPostRequestDB myPostRequestDB;
    private IMyPostRequest myPostRequest;
    private String query;
    private ILogger logger;
    private PostRequestStatus postRequestStatus;

    MyPostRequestController() throws Exception {
        myPostRequestFactory = MyPostRequestFactory.getInstance();
        myPostRequestDB = myPostRequestFactory.createMyPostRequestDB();
        myPostRequest = myPostRequestFactory.createMyPostRequest();
        logger = new MyLoggerAdapter(this);
    }

    @GetMapping("/my_post_requests")
    public String postRequest(Model model) {
        model.addAttribute("title", "Post Request");
        try {
//            query = iMyPostRequestDB.getPostRequestByUserId((Integer) SessionManager.getInstance().getValue(UserDbColumnNames.ID));
//            query = iMyPostRequestDB.getPostRequestByUserId(11);
            List<IMyPostRequest> postRequests = myPostRequest.getMyPostRequests(myPostRequestDB);
            model.addAttribute("requests_count", postRequests.size());
            model.addAttribute("postRequests", postRequests);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error(e.getMessage());
        }
        return "myPostRequests";
    }

    @PostMapping("/join/{id}")
    public String join(Model model, @ModelAttribute Post post, @PathVariable("id") int postId) throws Exception {
//        query = iMyPostRequestDB.createJoinRequest(post_id, (Integer) SessionManager.getInstance().getValue(UserDbColumnNames.ID));
//        myPostRequest.executeQuery(query);
        myPostRequest.createJoinRequest(myPostRequestDB, postId);

//        query = iMyPostRequestDB.getPostOwnerDetailsbyPostId(postId);
//        IMyPostRequest postRequests = myPostRequest.resultPostOwnerDetails(query);

        IMyPostRequest postRequests = myPostRequest.getPostOwnerDetails(myPostRequestDB, postId);

        NotificationFactory.getInstance().createEmailNotification().sendNotification(postRequests.getEmailCreator(),
                "Join Request for " + postRequests.getPostTitle(),
                SessionManager.getInstance().getValue(UserDbColumnNames.FIRSTNAME) + " " + SessionManager.getInstance().getValue(UserDbColumnNames.LASTNAME) + " requested for joining " + postRequests.getPostTitle());
        return "redirect:/my_requests";
    }

    @PostMapping("/accept_request/{request_id}")
    public String acceptRequest(@PathVariable("request_id") int requestId) throws Exception {
        System.out.println("--- After accept request ----");
        System.out.println("--- "+requestId);
//        query = iMyPostRequestDB.updateRequestStatus(requestId, PostRequestStatus.ACCEPT);
//        myPostRequest.updateRequest(query);

        myPostRequest.updateRequest(myPostRequestDB, requestId, PostRequestStatus.ACCEPT);

//        query = iMyPostRequestDB.getPostRequesteeDetailsbyRequestId(requestId);
//        IMyPostRequest postRequests = myPostRequest.resultPostRequesteeDetails(query);

        IMyPostRequest postRequests = myPostRequest.getPostRequesteeDetails(myPostRequestDB, requestId);

        NotificationFactory.getInstance().createEmailNotification().sendNotification(postRequests.getEmailRequestee(),
                "Update on request for joining " + postRequests.getPostTitle(),
                SessionManager.getInstance().getValue(UserDbColumnNames.FIRSTNAME) + " " + SessionManager.getInstance().getValue(UserDbColumnNames.LASTNAME) + " ACCEPT request for joining " + postRequests.getPostTitle());

        return "redirect:/my_post_requests";
    }

    @PostMapping("/decline_request/{request_id}")
    public String declineRequest(@PathVariable("request_id") int requestId) throws Exception {
        System.out.println("--- After decline request ----");
        System.out.println("--- "+requestId);
//        query = iMyPostRequestDB.updateRequestStatus(requestId, PostRequestStatus.DECLINE);
//        myPostRequest.updateRequest(query);

        myPostRequest.updateRequest(myPostRequestDB, requestId, PostRequestStatus.DECLINE);

//        query = iMyPostRequestDB.getPostRequesteeDetailsbyRequestId(requestId);
//        IMyPostRequest postRequests = myPostRequest.resultPostRequesteeDetails(query);

        IMyPostRequest postRequests = myPostRequest.getPostRequesteeDetails(myPostRequestDB, requestId);

        NotificationFactory.getInstance().createEmailNotification().sendNotification(postRequests.getEmailRequestee(),
                "Update on request for joining " + postRequests.getPostTitle(),
                SessionManager.getInstance().getValue(UserDbColumnNames.FIRSTNAME) + " " + SessionManager.getInstance().getValue(UserDbColumnNames.LASTNAME) + " DECLINE requested for joining " + postRequests.getPostTitle());

        return "redirect:/my_post_requests";
    }

}
