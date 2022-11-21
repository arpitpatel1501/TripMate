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
public class ListUserPosts {
    private final ILogger logger = new MyLogger(this);
    final GetAllPostsQueryBuilderDAO getAllPostsQueryBuilderDAO;
    final DatabaseConnectionDAO databaseConnectionDAO;

    ListUserPosts() throws Exception {
        getAllPostsQueryBuilderDAO = GetAllPostsQueryBuilder.getInstance();
        databaseConnectionDAO = new DatabaseConnection();
    }

    @GetMapping("/posts")
    public String getUserPosts(Model model) throws Exception {
        model.addAttribute("title", "My Posts");
        List<Post> posts = getUserPosts(1);
        model.addAttribute("posts", posts);
        return "listposts";
    }

    private List<Post> getUserPosts(int userid) throws Exception {
        Connection connection = databaseConnectionDAO.getDatabaseConnection();
        Statement statement = connection.createStatement();
        String query = getAllPostsQueryBuilderDAO.getPostsByUserId(userid);
        logger.info(query);
        final ResultSet allPosts = statement.executeQuery(query);
        List<Post> posts = Post.resultSetToPosts(allPosts);
        connection.close();
        return posts;
    }


}
