package grp16.tripmate.post.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostsQueryBuilder;
import grp16.tripmate.post.database.PostsQueryBuilder;
import grp16.tripmate.user.controller.UserController;
import grp16.tripmate.user.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private IDatabaseConnection dbConnection;
    private IPostsQueryBuilder queryBuilder;

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

    public String getStartDate() {
        return getSQLParsableDate(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        this.startDate = getJavaDate(startDate);
    }

    public String getEndDate() {
        return getSQLParsableDate(endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) throws ParseException {
        this.endDate = getJavaDate(endDate);
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

    public void setOwner(int ownerid) throws Exception {
        this.owner = new User().getUserById(ownerid);
        logger.info(owner.toString());
    }

    public static List<Post> resultSetToPosts(ResultSet rs) throws Exception {
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
            post.setOwner(rs.getInt(PostDbColumnNames.OWNER));
            results.add(post);
        }
        return results;
    }

    public List<Post> getPostsByUserId(int userid) {
        try {
            final Connection connection = new DatabaseConnection().getDatabaseConnection();
            IPostsQueryBuilder queryBuilder = PostsQueryBuilder.getInstance();
            String query = queryBuilder.getPostsByUserId(userid);
//            logger.info(query);
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
//            logger.info(query);
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
            final ResultSet postRS = connection.createStatement().executeQuery(query);
            Post post = Post.resultSetToPosts(postRS).get(0);
//            logger.info(post.toString());
            connection.close();
            return post;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Post> getFeedbackPosts() throws Exception {
        return null;
    }

    @Override
    public boolean isEligibleForFeedback() {
        return endDate.before(new Date());
    }

    public boolean updatePost() {
        Connection connection = null;
        boolean isUpdateSuccess = false;
        try {
            connection = dbConnection.getDatabaseConnection();
            Statement statement = connection.createStatement();
            String query = queryBuilder.getUpdatePostQuery(this);
//            logger.info(query);
            statement.executeUpdate(query);
            isUpdateSuccess = true;
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isUpdateSuccess;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", owner=" + owner +
                ", title='" + title + '\'' +
                ", capacity=" + capacity +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", description='" + description + '\'' +
                ", isHidden=" + isHidden +
                '}';
    }

    private String getSQLParsableDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private Date getJavaDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

}
