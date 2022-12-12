package grp16.tripmate.post.model;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.feedback.IFeedbackDatabase;
import grp16.tripmate.post.model.feedback.Feedback;
import grp16.tripmate.post.model.exception.MinAgeGreaterThanMaxAgeException;
import grp16.tripmate.post.model.exception.StartDateAfterEndDateException;
import grp16.tripmate.post.model.exception.StartDateBeforeTodayException;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.database.UserDbColumnNames;

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
    private int id;
    private int owner_id;
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

    public Post() {
        this.setStartDate(new Date());
        this.setEndDate(new Date());
    }

    @Override
    public boolean createPost(IPostDatabase database) throws Exception {
        boolean isPostCreated = database.createPost(this);
        if (isPostCreated) {
            notifyObservers();
        }
        return isPostCreated;
    }

    @Override
    public List<Post> getPostsByUserId(IPostDatabase database, int userId) {
        return database.getPostsByUserId(userId);
    }

    @Override
    public List<Post> getAllPosts(IPostDatabase database, int loggedInUser) {
        return database.getAllPosts(loggedInUser);
    }

    @Override
    public Post getPostByPostId(IPostDatabase database, int postId) {
        return database.getPostByPostId(postId);
    }

    @Override
    public boolean updatePost(IPostDatabase database) {
        return database.updatePost(this);
    }

    @Override
    public boolean deletePost(IPostDatabase database) {
        return database.deletePost(this.getId());
    }

    @Override
    public boolean hidePost(IPostDatabase database) {
        return database.hidePost(this.getId());
    }

    @Override
    public List<Feedback> getFeedbacks(IPostDatabase database, IFeedbackDatabase feedbackDatabase) throws Exception {
        return database.getFeedbacks(feedbackDatabase, this.getId());
    }

    public boolean isEligibleToJoin() throws Exception {
        boolean isPastDate = endDate.before(new Date());
        boolean isOwner = getOwner_id() == (int) SessionManager.Instance().getValue(UserDbColumnNames.ID);
        return !isPastDate && !isOwner;
    }

    @Override
    public void validatePost(PostValidator validator) throws ParseException,
            StartDateAfterEndDateException,
            MinAgeGreaterThanMaxAgeException,
            StartDateBeforeTodayException {
        validator.isMinAgeLessThanMaxAge(this);
        validator.isStarDateBeforeToday(this);
        validator.isStartDateBeforeEndDate(this);

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


    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "Post{" +
                " id=" + id +
                ", owner=" + owner_id +
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
