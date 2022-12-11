package grp16.tripmate.vehicle.database;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.vehicle.model.IVehicleBookingFactory;
import grp16.tripmate.vehicle.model.VehicleBooking;
import grp16.tripmate.vehicle.model.VehicleBookingFactory;

import java.sql.ResultSet;
import java.util.List;

public class VehicleBookingDatabase implements IVehicleBookingDatabase
{
    private final ILogger logger = new MyLoggerAdapter(this);

    IVehicleBookingQueryBuilder queryBuilder;

    private final IDatabaseConnection dbConnection;

    private final IVehicleBookingFactory factory;

    public VehicleBookingDatabase()
    {
        queryBuilder = VehicleBookingQueryBuilder.getInstance();
        dbConnection = new DatabaseConnection();
        factory = VehicleBookingFactory.getInstance();
    }

    @Override
    public List<VehicleBooking> resultSetToVehicleBooking(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public List<VehicleBooking> getVehicleBookingByPostId(int postId) {
        return null;
    }

    @Override
    public List<VehicleBooking> getVehicleBookingByUserId(int userId) {
        return null;
    }

    @Override
    public VehicleBooking getVehicleBookingByBookingId(int bookingId) {
        return null;
    }
}
