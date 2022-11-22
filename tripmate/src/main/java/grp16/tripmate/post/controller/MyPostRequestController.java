package grp16.tripmate.post.controller;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.DatabaseConnectionDAO;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;
import grp16.tripmate.post.database.IMyPostRequestDB;
import grp16.tripmate.post.database.MyPostRequestDB;
import grp16.tripmate.post.model.MyPostRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyPostRequestController {

    private final ILogger logger = new MyLogger(this);
    final IMyPostRequestDB iMyPostRequestDB;
    final DatabaseConnectionDAO databaseConnectionDAO;

    MyPostRequestController() {
        iMyPostRequestDB = MyPostRequestDB.getInstance();
        databaseConnectionDAO = new DatabaseConnection();
    }

    @GetMapping("/post_requests")
    public String postRequest(Model model) throws Exception {
        model.addAttribute("requests_count", 2);
        model.addAttribute("title", "Post Request");
//        final Connection connection = databaseConnectionDAO.getDatabaseConnection();
//        Statement statement = connection.createStatement();
//        String query = iPostRequestDB.getPostRequests(1);
//        logger.info(query);
//        final ResultSet allRequests = statement.executeQuery(query);
        List<MyPostRequest> postRequests = MyPostRequest.resultSetToPostRequests(null);
//        connection.close();
        model.addAttribute("postRequests", postRequests);
        return "post_requests";
    }
}
