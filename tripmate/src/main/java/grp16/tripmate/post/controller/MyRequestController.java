package grp16.tripmate.post.controller;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.DatabaseConnectionDAO;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;
import grp16.tripmate.post.database.IMyPostRequestDB;
import grp16.tripmate.post.database.IMyRequestDB;
import grp16.tripmate.post.database.MyPostRequestDB;
import grp16.tripmate.post.database.MyRequestDB;
import grp16.tripmate.post.model.MyPostRequest;
import grp16.tripmate.post.model.MyRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyRequestController {
//    private IMyRequestDB iMyRequestDB;
//    private MyRequest myRequest;
//    private String query;
//
//    MyRequestController() {
//        iMyRequestDB = MyRequestDB.getInstance();
//        databaseConnectionDAO = new DatabaseConnection();
//    }
//
//    @GetMapping("/my_requests")
//    public String myRequest(Model model) throws Exception {
//        model.addAttribute("requests_count", 2);
//        model.addAttribute("title", "My Requests");
//
//        iMyRequestDB.getPostRequestByUserId(1);
//        List<MyRequest> myRequests = MyRequest.resultSetToMyRequests(null);
////        connection.close();
//        model.addAttribute("myRequests", myRequests);
//        return "my_requests";
//    }
}
