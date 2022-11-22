package grp16.tripmate.post.controller;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.DatabaseConnectionDAO;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;
import grp16.tripmate.post.database.GetAllPostsQueryBuilder;
import grp16.tripmate.post.database.GetAllPostsQueryBuilderDAO;
import grp16.tripmate.post.database.IPostRequestDB;
import grp16.tripmate.post.database.PostRequestDB;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.PostRequest;
import grp16.tripmate.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Controller
public class PostRequestController {

    private final ILogger logger = new MyLogger(this);
    final IPostRequestDB iPostRequestDB;
    final DatabaseConnectionDAO databaseConnectionDAO;

    PostRequestController() {
        iPostRequestDB = PostRequestDB.getInstance();
        databaseConnectionDAO = new DatabaseConnection();
    }

    @GetMapping("/post_request")
    public String postRequest(Model model) throws Exception {
        // Stud function
        return "post_requests";
    }
}
