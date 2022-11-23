package grp16.tripmate.post.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostsQueryBuilder;
import grp16.tripmate.post.database.PostsQueryBuilder;
import grp16.tripmate.user.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post implements IPost {
    private final ILogger logger = new MyLoggerAdapter(this);

    private int id;
    private User owner;
    private String title;
    private int capacity;
    private String source;
    private String destination;
    private Date startDate;
    private Date endDate;
    private int minAge;
    private int maxAge;
    private String description;
    private boolean isHidden;

    public Post(int id, User owner, String title, int capacity, String source, String destination, Date startDate, Date endDate, int minAge, int maxAge, String description, boolean isHidden) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.capacity = capacity;
        this.source = source;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.description = description;
        this.isHidden = isHidden;
    }

    IDatabaseConnection dbConnection;
    IPostsQueryBuilder queryBuilder;

    public Post() {
        queryBuilder = PostsQueryBuilder.getInstance();
        dbConnection = new DatabaseConnection();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public static List<Post> resultSetToPosts(ResultSet rs) throws SQLException {
        List<Post> results = new ArrayList<>();
        while (rs.next()) {
            Post post = new Post();
            post.setId(rs.getInt(PostDbColumnNames.ID));
            post.setTitle(rs.getString(PostDbColumnNames.TITLE));
            post.setCapacity(rs.getInt(PostDbColumnNames.CAPACITY));
            post.setDescription(rs.getString(PostDbColumnNames.DESCRIPTION));
            post.setEndDate(rs.getDate(PostDbColumnNames.ENDDATE));
            post.setHidden(false);
            post.setDestination(rs.getString(PostDbColumnNames.DESTINATION));
            post.setMaxAge(rs.getInt(PostDbColumnNames.MAXAGE));
            post.setMinAge(rs.getInt(PostDbColumnNames.MINAGE));
            post.setStartDate(rs.getDate(PostDbColumnNames.STARTDATE));
            post.setSource(rs.getString(PostDbColumnNames.SOURCE));
            results.add(post);
        }
        return results;
    }

    public List<Post> getPostsByUserId(int userid) {
        try {
            final Connection connection = new DatabaseConnection().getDatabaseConnection();
            IPostsQueryBuilder queryBuilder = PostsQueryBuilder.getInstance();
            String query = queryBuilder.getPostsByUserId(userid);
            logger.info(query);
            final ResultSet allPosts = connection.createStatement().executeQuery(query);
            List<Post> posts = Post.resultSetToPosts(allPosts);
            connection.close();
            return posts;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Post> getAllPosts() {
        try {
            final Connection connection = dbConnection.getDatabaseConnection();
            String query = queryBuilder.getAllPosts();
            logger.info(query);
            final ResultSet allPosts = connection.createStatement().executeQuery(query);
            List<Post> posts = Post.resultSetToPosts(allPosts);
            connection.close();
            return posts;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Post getPostByPostId(int postid) {
        try {
            final Connection connection = dbConnection.getDatabaseConnection();
            String query = queryBuilder.getPostByPostId(postid);
            logger.info(query);
            final ResultSet postRS = connection.createStatement().executeQuery(query);
            Post post = Post.resultSetToPosts(postRS).get(0);
            connection.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
