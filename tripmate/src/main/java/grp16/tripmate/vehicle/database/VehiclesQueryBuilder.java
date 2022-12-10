package grp16.tripmate.vehicle.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;



public class VehiclesQueryBuilder implements IVehicleQueryBuilder
{
    private final ILogger logger = new MyLoggerAdapter(this);
    private static VehiclesQueryBuilder instance;

    private VehiclesQueryBuilder()
    {

    }

    public static VehiclesQueryBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new VehiclesQueryBuilder();
        }
        return instance;
    }

    @Override
    public String getAllVehicles()
    {
        return "";
    }

    @Override
    public String getVehicleById(int vehicleId)
    {
        return "";
    }

    @Override
    public String getVehiclesByPostId(int postId)
    {
        return "";
    }

    @Override
    public String getVehiclesByUserId(int userId)
    {
        return "";
    }
}
