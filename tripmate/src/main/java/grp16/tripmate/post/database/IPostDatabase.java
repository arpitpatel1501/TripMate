package grp16.tripmate.post.database;

import grp16.tripmate.post.database.feedback.IFeedbackDatabase;
import grp16.tripmate.post.model.feedback.Feedback;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.model.VehicleBooking.VehicleBooking;

import java.util.List;

public interface IPostDatabase {

    boolean updatePost(Post post);

    boolean deletePost(int post_id);

    boolean hidePost(int post_id);

    boolean createPost(Post post) throws Exception;

    List<Post> getPostsByUserId(int userId);

    List<Post> getAllPosts(int loggedInUser);

    Post getPostByPostId(int postId);

    List<Feedback> getFeedbacks(IFeedbackDatabase feedbackDatabase, int postId) throws Exception;

    List<VehicleBooking> getVehicles(IVehicleBookingDatabase vehicleBookingDatabase, int postId);
}
