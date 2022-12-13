package grp16.tripmate.post.database;

import grp16.tripmate.post.database.feedback.IFeedbackDatabase;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.feedback.Feedback;
import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.model.VehicleBooking.VehicleBooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostDatabaseMock implements IPostDatabase{

    private static final Map<Integer, Post> postDB = new HashMap<>();

    @Override
    public boolean createPost(Post post) throws Exception {
        Object returnValue = postDB.put(post.getId(), post);
        return returnValue == null;
    }

    @Override
    public boolean updatePost(Post post) {
        postDB.put(post.getId(), post);
        return true;
    }

    @Override
    public boolean deletePost(int post_id) {
        postDB.remove(post_id);
        return true;
    }

    @Override
    public boolean hidePost(int post_id) {
        Post post = postDB.get(post_id);
        post.setHidden(true);
        return true;
    }

    @Override
    public List<Post> getPostsByUserId(int userId) {
        List<Post> userPosts = new ArrayList<>();
        for(Map.Entry<Integer,Post> entry : postDB.entrySet()){
            Post post = entry.getValue();
            if(post.getOwner_id() == userId){
                userPosts.add(post);
            }
        }
        return userPosts;
    }

    @Override
    public List<Post> getAllPosts(int loggedInUser) {
        List<Post> posts = new ArrayList<>();
        for(Map.Entry<Integer,Post> entry : postDB.entrySet()){
            posts.add(entry.getValue());
        }
        return posts;
    }

    @Override
    public Post getPostByPostId(int postId) {
        return postDB.get(postId);
    }

    @Override
    public List<Feedback> getFeedbacks(IFeedbackDatabase feedbackDatabase, int postId) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public List<VehicleBooking> getVehicles(IVehicleBookingDatabase vehicleBookingDatabase, int postId) {
        return null;
    }
}
