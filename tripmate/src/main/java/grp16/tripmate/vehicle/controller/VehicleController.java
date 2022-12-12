package grp16.tripmate.vehicle.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.IPostDatabase;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.factory.IPostFactory;
import grp16.tripmate.post.model.factory.PostFactory;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.vehicle.database.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VehicleController implements IVehicleController {
    private final ILogger logger = new MyLoggerAdapter(this);

    private final IVehicleFactory vehicleFactory;
    private final IPostFactory postFactory;

    private final IVehicleBookingFactory vehicleBookingFactory;

    private final IVehicle vehicle;

    private final IPostDatabase postDatabase;

    private final IVehicleBookingDatabase vehicleBookingDatabase;

    VehicleController() {
        postFactory = PostFactory.getInstance();
        vehicleFactory = VehicleFactory.getInstance();
        vehicle = vehicleFactory.getNewVehicle();
        vehicleBookingFactory = VehicleBookingFactory.getInstance();
        postDatabase = postFactory.makePostDatabase();
        vehicleBookingDatabase = vehicleBookingFactory.getVehicleBookingDatabase();
    }

    @GetMapping("/all-vehicles")
    public String getAllVehicles(Model model) {
        model.addAttribute("title", "Vehicles");
        List<Vehicle> vehicles = vehicle.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "listvehicles";
    }

    @GetMapping("/vehicle/{id}")
    public String getVehicleDetails(Model model, @PathVariable("id") int vehicleId) throws Exception {
        model.addAttribute("title", "Vehicle Details");

        Vehicle vehicleObj = vehicle.getVehicleById(vehicleId);
        model.addAttribute("vehicle", vehicleObj);

        int userId = SessionManager.getInstance().getLoggedInUserId();
        Post post = (Post) postFactory.makeNewPost();
        List<Post> userPosts = post.getPostsByUserId(postDatabase, userId);
        model.addAttribute("userposts", userPosts);

        VehicleBooking vehicleBookingObj = vehicleBookingFactory.getNewVehicleBooking();
        model.addAttribute("vehicleBookingObj", vehicleBookingObj);

        return "vehicledetails";
    }

    @PostMapping("/confirm-booking/{id}")
    public String confirmVehicleBooking(Model model,
                                        @PathVariable("id") int vehicleId,
                                        @ModelAttribute VehicleBooking vehicleBookingObj,
                                        @ModelAttribute Post userPost) {
        logger.info("the vehiclebooking obj is: " + vehicleBookingObj);
        logger.info("the User Post obj is: " + userPost);

        vehicleBookingObj.setVehicleId(vehicleId);
        vehicleBookingDatabase.createVehicleBooking(vehicleBookingObj);
        return "payment";
    }

    @GetMapping("/my-vehicle-bookings")
    public String getAllVehicleBookingsByUserId(Model model) {
        return "my_vehiclebookings";
    }

    @GetMapping("/recommended-vehicles")
    public String getRecommendedVehiclesByTripId(Model model) {
        return "recommended_vehicles";
    }
}