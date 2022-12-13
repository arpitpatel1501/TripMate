package grp16.tripmate.vehicle.model.Vehicle;

import java.util.List;

public interface IVehicle
{
    List<Vehicle> getAllVehicles();

    Vehicle getVehicleById(int vehicleId);

    List<Vehicle> getRecommendedTripVehicles(int postId);

    List<Vehicle> getTripVehicles(int postId);

    List<Vehicle> getVehiclesByUserId(int userId);
}
