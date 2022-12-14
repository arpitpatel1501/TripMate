package grp16.tripmate.vehicle.database.VehicleCategory;

public interface IVehicleCategoryQueryGenerator {
    String getVehicleCategoryByVehicleId(int vehicleId);
    String getVehicleCategoryById(int categoryId);
}
