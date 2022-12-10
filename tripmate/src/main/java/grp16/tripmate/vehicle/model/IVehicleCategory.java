package grp16.tripmate.vehicle.model;
import java.util.List;

public interface IVehicleCategory
{
    List<VehicleCategory> getAllVehicleCategory();

    VehicleCategory getVehicleCategoryByVehicleId(int vehicleId);

}
