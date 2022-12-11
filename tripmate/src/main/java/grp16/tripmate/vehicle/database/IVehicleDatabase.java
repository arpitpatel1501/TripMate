package grp16.tripmate.vehicle.database;

import grp16.tripmate.vehicle.model.Vehicle;

import java.util.List;
import java.sql.ResultSet;


public interface IVehicleDatabase
{
    List<Vehicle> resultSetToVehicles(ResultSet rs) throws Exception;
    List<Vehicle> getVehiclesByPostId(int postId);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getVehiclesByUserId(int userId);
    Vehicle getVehicleById(int vehicleId);
}
