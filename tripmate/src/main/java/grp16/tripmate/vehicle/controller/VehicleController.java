package grp16.tripmate.vehicle.controller;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.vehicle.model.IVehicle;
import grp16.tripmate.vehicle.model.IVehicleFactory;
import grp16.tripmate.vehicle.model.Vehicle;
import grp16.tripmate.vehicle.model.VehicleFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class VehicleController implements IVehicleController
{
    private final ILogger logger = new MyLoggerAdapter(this);

    final private IVehicleFactory vehicleFactory;

    private final IVehicle vehicle;

    VehicleController() {
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

    @PostMapping("/vehicle/{id}")
    public String confirmVehicleBooking(Model model, @PathVariable("id") int vehicleId) {
        return "vehicledetails";
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
