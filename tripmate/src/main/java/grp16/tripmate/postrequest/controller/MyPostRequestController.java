package grp16.tripmate.postrequest.controller;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@Controller
public class MyPostRequestController {

    private final ILogger logger = new MyLoggerAdapter(this);

    private IMyPostRequestDB iMyPostRequestDB;
    private MyPostRequest myPostRequest;
    private String query;

    private DatabaseConnection databaseConnection;


    MyPostRequestController() {
        iMyPostRequestDB = MyPostRequestDB.getInstance();
        myPostRequest = new MyPostRequest();
        databaseConnection = new DatabaseConnection();
    }

    @GetMapping("/post_requests")
    public String postRequest(Model model) throws Exception {
        model.addAttribute("title", "Post Request");

        query = iMyPostRequestDB.getPostRequestByUserId((Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
        List<MyPostRequest> postRequests = myPostRequest.resultMyPostRequests(query);
        model.addAttribute("requests_count", postRequests.size());

        model.addAttribute("postRequests", postRequests);
        return "post_requests";
    }

    @PostMapping("/join/{id}")
    public String join(Model model, @ModelAttribute Post post, @PathVariable("id") int post_id) throws Exception {
//        SessionManager.Instance().removeValue(UserDbColumnNames.id);
        logger.info(post.toString());
        query = iMyPostRequestDB.createJoinRequest(post_id, (Integer) SessionManager.Instance().getValue(UserDbColumnNames.id));
        Connection connection = databaseConnection.getDatabaseConnection();
        Statement statement = connection.createStatement();
        statement.execute(query);
        connection.close();
        return "redirect:/dashboard";
    }
}
