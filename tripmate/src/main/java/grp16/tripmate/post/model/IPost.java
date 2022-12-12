package grp16.tripmate.post.model;

import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.database.feedback.IFeedbackDatabase;
import grp16.tripmate.post.model.feedback.Feedback;
import grp16.tripmate.post.model.exception.MinAgeGreaterThanMaxAgeException;
import grp16.tripmate.post.model.exception.StartDateAfterEndDateException;
import grp16.tripmate.post.model.exception.StartDateBeforeTodayException;
import grp16.tripmate.vehicle.database.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.model.IVehicleBookingFactory;
import grp16.tripmate.vehicle.model.VehicleBooking;

import java.text.ParseException;
import java.util.List;

public interface IPost {

    boolean createPost(IPostDatabase database) throws Exception;

    List<Post> getAllPosts(IPostDatabase database, int loggedInUser) throws Exception;

    List<Post> getPostsByUserId(IPostDatabase database, int userid) throws Exception;

    Post getPostByPostId(IPostDatabase database, int postId) throws Exception;

    boolean updatePost(IPostDatabase database);

    boolean deletePost(IPostDatabase database);

    List<Feedback> getFeedbacks(IPostDatabase database, IFeedbackDatabase feedbackDatabase) throws Exception;

    boolean hidePost(IPostDatabase database);

    void validatePost(PostValidator validator) throws ParseException, StartDateAfterEndDateException, MinAgeGreaterThanMaxAgeException, StartDateBeforeTodayException;

    List<VehicleBooking> getVehiclesAssociatedWithCurrentPost(IPostDatabase database, IVehicleBookingDatabase vehicleBookingDatabase);
}
