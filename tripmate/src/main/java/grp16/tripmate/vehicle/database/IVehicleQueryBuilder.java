package grp16.tripmate.vehicle.database;


public interface IVehicleQueryBuilder
{
    String getAllVehicles();

    String getVehicleById(int vehicleId);

    String getVehiclesByPostId(int postId);

    String getVehiclesByUserId(int userId);
}
