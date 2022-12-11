package grp16.tripmate.post.model;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.feedback.model.Feedback;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.User;
import grp16.tripmate.user.model.UserDbColumnNames;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 **References
 **https://www.baeldung.com/java-simple-date-format
 */

public class Post extends PostSubject implements IPost {
    private final ILogger logger = new MyLoggerAdapter(this);
    private IPostDatabase database;
    private PostValidator validator;

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

    public Post(IPostDatabase postDatabase) {
        super();
        this.database = postDatabase;
        this.validator = new PostValidator();
        this.setStartDate(new Date());
        this.setEndDate(new Date());
    }

    @Override
    public boolean createPost() throws Exception {
        boolean isPostCreated = database.createPost(this);
        if (isPostCreated) {
            notifyObservers();
        }
        return isPostCreated;
    }

    @Override
    public List<Post> getPostsByUserId(int userid) throws Exception {
        return database.getPostsByUserId(userid);
    }

    @Override
    public List<Post> getAllPosts() throws Exception {
        return database.getAllPosts();
    }

    @Override
    public Post getPostByPostId(int postId) throws Exception {
        return database.getPostByPostId(postId);
    }

    @Override
    public boolean updatePost() {
        return database.updatePost(this);
    }

    @Override
    public boolean deletePost() {
        return database.deletePost(this.getId());
    }

    @Override
    public boolean hidePost() {
        return database.hidePost(this.getId());
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return database.getFeedbacks(this.getId());
    }

    public boolean isEligibleToJoin() throws Exception {
        boolean isPastDate = endDate.equals(new Date());
        boolean isOwner = getOwner().getId() == (int) SessionManager.Instance().getValue(UserDbColumnNames.id);
        logger.info(String.valueOf(isPastDate));
        logger.info(String.valueOf(isOwner));
        return !isPastDate && !isOwner;
    }

    @Override
    public void validatePost() throws ParseException, StartDateAfterEndDateException, MinAgeGreaterThanMaxAgeException, StartDateBeforeTodayException {
        validator.isStarDateBeforeToday(this);
        validator.isStartDateBeforeEndDate(this);
        validator.isMinAgeLessThanMaxAge(this);
    }

    public boolean isEligibleForFeedback() {
        return endDate.before(new Date());
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

    private String getSQLParsableDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    protected Date getJavaDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
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

    public void setHidden(int i) {
        isHidden = i == 0;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(int ownerId) throws Exception {
        this.owner = new User().getUserById(ownerId);
        logger.info(owner.toString());
    }

    public IPostDatabase getDatabase() {
        return database;
    }

    public void setDatabase(IPostDatabase database) {
        this.database = database;
    }

    public PostValidator getValidator() {
        return validator;
    }

    public void setValidator(PostValidator validator) {
        this.validator = validator;
    }

    @Override
    public String toString() {
        return "Post{" +
                ", database=" + database +
                ", id=" + id +
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
}
