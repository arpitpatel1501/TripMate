package grp16.tripmate.vehicle.database.VehicleCategory;

import grp16.tripmate.vehicle.model.VehicleCategory.VehicleCategory;

import java.text.ParseException;

public interface IVehicleCategoryDatabase
{
    VehicleCategory getVehicleCategoryByVehicleId(int vehicleId) throws ParseException;

    VehicleCategory getVehicleCategoryById(int categoryId) throws ParseException;
}
