package grp16.tripmate.vehicle.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.IPostFactory;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.post.model.PostFactory;
import grp16.tripmate.session.SessionManager;
import grp16.tripmate.user.model.UserDbColumnNames;
import grp16.tripmate.vehicle.model.IVehicle;
import grp16.tripmate.vehicle.model.IVehicleFactory;
import grp16.tripmate.vehicle.model.Vehicle;
import grp16.tripmate.vehicle.model.VehicleFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VehicleController implements IVehicleController {
    private final ILogger logger = new MyLoggerAdapter(this);

    private final IVehicleFactory vehicleFactory;
    private final IPostFactory postFactory;

    private final IVehicle vehicle;

    VehicleController() {
        postFactory = PostFactory.getInstance();
        vehicleFactory = VehicleFactory.getInstance();
        vehicle = vehicleFactory.getNewVehicle();
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

        int userId = (Integer) SessionManager.Instance().getValue(UserDbColumnNames.id);
        Post post = (Post) postFactory.getNewPost();
        List<Post> userPosts = post.getPostsByUserId(userId);
        model.addAttribute("userposts", userPosts);

        return "vehicledetails";
    }

    @PostMapping("/vehicle/{id}")
    public String confirmVehicleBooking(Model model, @PathVariable("id") int vehicleId)
    {
        return "my_vehiclebookings";
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
