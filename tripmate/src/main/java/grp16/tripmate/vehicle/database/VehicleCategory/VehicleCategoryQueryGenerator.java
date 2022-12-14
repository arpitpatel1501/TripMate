package grp16.tripmate.vehicle.database.VehicleCategory;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

public class VehicleCategoryQueryGenerator implements IVehicleCategoryQueryGenerator
{
    private final ILogger logger = new MyLoggerAdapter(this);

    private static VehicleCategoryQueryGenerator instance;

    private VehicleCategoryQueryGenerator()
    {

    }

    public static VehicleCategoryQueryGenerator getInstance()
    {
        if (instance == null)
        {
            instance = new VehicleCategoryQueryGenerator();
        }
        return instance;
    }
    @Override
    public String getVehicleCategoryByVehicleId(int vehicleId) {
        String query = "";
        return query;
    }

    @Override
    public String getVehicleCategoryById(int categoryId) {
        String query = "";
        return query;
    }
}
