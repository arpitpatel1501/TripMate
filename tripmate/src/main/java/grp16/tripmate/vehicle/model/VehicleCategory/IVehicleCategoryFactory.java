package grp16.tripmate.vehicle.model.VehicleCategory;

import grp16.tripmate.vehicle.database.VehicleCategory.IVehicleCategoryDatabase;
import grp16.tripmate.vehicle.database.VehicleCategory.IVehicleCategoryQueryGenerator;

public interface IVehicleCategoryFactory {

    VehicleCategory getNewVehicleCategory();
    IVehicleCategoryDatabase getVehicleCategoryDatabase();

    IVehicleCategoryQueryGenerator getVehicleCategoryQueryGenerator();
}


