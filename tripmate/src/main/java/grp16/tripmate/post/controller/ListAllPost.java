package grp16.tripmate.post.controller;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.DatabaseConnectionDAO;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLogger;
import grp16.tripmate.post.database.GetAllPostsQueryBuilder;
import grp16.tripmate.post.database.GetAllPostsQueryBuilderDAO;
import grp16.tripmate.post.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Controller
public class ListAllPost {
    private final ILogger logger = new MyLogger(this);
    final GetAllPostsQueryBuilderDAO getAllPostsQueryBuilderDAO;
    final DatabaseConnectionDAO databaseConnectionDAO;

    ListAllPost() {
        getAllPostsQueryBuilderDAO = GetAllPostsQueryBuilder.getInstance();
        databaseConnectionDAO = new DatabaseConnection();
    }

    @GetMapping("/dashboard")
    public String getAllPosts(Model model) throws Exception {
        model.addAttribute("title", "Dashboard");
        final Connection connection = databaseConnectionDAO.getDatabaseConnection();
        Statement statement = connection.createStatement();
        String query = getAllPostsQueryBuilderDAO.getAllPostsNotCraetedByUserId(1);
        logger.info(query);
        final ResultSet allPosts = statement.executeQuery(query);
        List<Post> posts = Post.resultSetToPosts(allPosts);
        connection.close();
        model.addAttribute("posts", posts);
        return "listposts";
    }

}
