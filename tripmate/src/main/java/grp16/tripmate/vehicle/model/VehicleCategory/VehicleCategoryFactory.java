package grp16.tripmate.vehicle.model.VehicleCategory;

import grp16.tripmate.vehicle.database.VehicleCategory.IVehicleCategoryDatabase;
import grp16.tripmate.vehicle.database.VehicleCategory.IVehicleCategoryQueryGenerator;
import grp16.tripmate.vehicle.database.VehicleCategory.VehicleCategoryDatabase;
import grp16.tripmate.vehicle.database.VehicleCategory.VehicleCategoryQueryGenerator;

public class VehicleCategoryFactory implements IVehicleCategoryFactory
{
    private static IVehicleCategoryFactory instance;

    private VehicleCategoryFactory()
    {

    }

    public static VehicleCategoryFactory getInstance() {
        if (instance == null) {
            instance = new VehicleCategoryFactory();
        }
        return (VehicleCategoryFactory) instance;
    }

    @Override
    public VehicleCategory getNewVehicleCategory() {
        return new VehicleCategory();
    }

    @Override
    public IVehicleCategoryDatabase getVehicleCategoryDatabase() {
        return new VehicleCategoryDatabase();
    }

    @Override
    public IVehicleCategoryQueryGenerator getVehicleCategoryQueryGenerator() {
        return VehicleCategoryQueryGenerator.getInstance();
    }
}
