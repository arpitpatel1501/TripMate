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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ListUserPosts {
    private final ILogger logger = new MyLogger(this);


    @GetMapping("/posts")
    public String getUserPosts(Model model) throws Exception {
        model.addAttribute("title", "My Posts");
//        model.addAttribute("user", new User());
        List<Post> posts = getAllPosts();
        model.addAttribute("posts", posts);
        return "listposts";
    }

    private List<Post> getAllPosts() throws Exception {
        List<Post> posts = new ArrayList<>();
        final GetAllPostsQueryBuilderDAO getAllPostsQueryBuilderDAO = GetAllPostsQueryBuilder.getInstance();
        final DatabaseConnectionDAO databaseConnectionDAO = new DatabaseConnection();
        final Connection connection = databaseConnectionDAO.getDatabaseConnection();
        Statement statement = connection.createStatement();
        final ResultSet allPosts = statement.executeQuery(getAllPostsQueryBuilderDAO.getAllPosts(1));

        return resultSetToPosts(allPosts);
    }

    private List<Post> resultSetToPosts(ResultSet rs) throws SQLException {
        List<Post> results = new ArrayList<>();
        while (rs.next()) {
            Post post = new Post();
            post.setId(rs.getInt("id"));
            post.setTitle(rs.getString("title"));
            post.setCapacity(rs.getInt("capacity"));
            post.setDescription(rs.getString("description"));
            post.setEndDate(rs.getDate("end_ts"));
            post.setHidden(false);
            post.setDestination(rs.getString("destination_location"));
            post.setMaxAge(rs.getInt("max_age"));
            post.setMinAge(rs.getInt("min_age"));
            post.setStartDate(rs.getDate("start_ts"));
            post.setSource(rs.getString("source_location"));
            results.add(post);
        }
        logger.info("loaded " + results.size() + " results");
        return results;
    }
}
