package grp16.tripmate.post.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.IPostsQueryBuilder;
import grp16.tripmate.post.database.PostDatabase;
import grp16.tripmate.post.database.PostsQueryBuilder;
import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;

import java.sql.Connection;
import java.sql.ResultSet;
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
    private final IDatabaseConnection dbConnection;
    private final IPostsQueryBuilder queryBuilder;

    private static IPostFactory postFactory = null;

    private final IPostDatabase database;

    public Post() {
        postFactory = PostFactory.getInstance();
        database = postFactory.getPostDatabase();
        queryBuilder = postFactory.getPostQueryBuilder();
        dbConnection = new DatabaseConnection();
        this.setStartDate(new Date());
        this.setEndDate(new Date());
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

    @Override
    public boolean createPost() throws Exception {
        return database.createPost(this);
    }


    public List<Post> getPostsByUserId(int userid) {
        return database.getPostsByUserId(userid);
    }

    public List<Post> getAllPosts() {
        return database.getAllPosts();
    }

    public Post getPostByPostId(int postid) {
        return database.getPostByPostId(postid);
    }


    @Override
    public boolean isEligibleForFeedback() {
        return endDate.before(new Date());
    }

    public boolean updatePost() {
        return database.updatePost(this);
    }

    public boolean deletePost() {
        return database.deletePost(this);
    }

    public List<Feedback> getFeedbacks() {
        return database.getFeedbacks(this.getId());
    }

    public boolean hidePost() {
        return database.hidePost(this);
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

    public boolean isEligibleToJoin() throws Exception {
        boolean isPastDate = endDate.equals(new Date());
        boolean isOwner = getOwner().getId() == (int) SessionManager.Instance().getValue(UserDbColumnNames.id);
        logger.info(String.valueOf(isPastDate));
        logger.info(String.valueOf(isOwner));
        return !isPastDate && !isOwner;
    }

}
