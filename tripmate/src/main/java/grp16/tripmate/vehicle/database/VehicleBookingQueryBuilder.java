package grp16.tripmate.vehicle.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

public class VehicleBookingQueryBuilder implements IVehicleBookingQueryBuilder
{
    private final ILogger logger = new MyLoggerAdapter(this);

    private static VehicleBookingQueryBuilder instance;

    private VehicleBookingQueryBuilder()
    {

    }

    public static VehicleBookingQueryBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new VehicleBookingQueryBuilder();
        }
        return instance;
    }
    @Override
    public String getVehicleBookingByPostId(int postId) {
        return null;
    }

    @Override
    public String getVehicleBookingByUserId(int userId) {
        return null;
    }

    @Override
    public String getVehicleBookingByBookingId(int bookingId) {
        return null;
    }
}
