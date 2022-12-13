package grp16.tripmate.vehicle.database.VehicleBooking;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.PostDbColumnNames;
import grp16.tripmate.vehicle.model.VehicleBooking.VehicleBooking;
import grp16.tripmate.vehicle.model.VehicleBooking.VehicleBookingDbColumnNames;

public class VehicleBookingQueryBuilder implements IVehicleBookingQueryBuilder {
    private final ILogger logger = new MyLoggerAdapter(this);

    private static VehicleBookingQueryBuilder instance;

    private VehicleBookingQueryBuilder() {

    }

    public static VehicleBookingQueryBuilder getInstance() {
        if (instance == null) {
            instance = new VehicleBookingQueryBuilder();
        }
        return instance;
    }

    @Override
    public String getVehicleBookingByPostId(int postId) {
        String query = "select * from " +
                VehicleBookingDbColumnNames.TABLENAME +
                " where " + VehicleBookingDbColumnNames.POST_ID + "  = " + postId + ";\n";
        logger.info(query);
        return query;
    }

    @Override
    public String getVehicleBookingByUserId(int userId) {
        String query = "select * from " +
                VehicleBookingDbColumnNames.TABLENAME + " where " +
                VehicleBookingDbColumnNames.POST_ID + " in ( select " + PostDbColumnNames.ID + " from " + PostDbColumnNames.TABLE_NAME
                + " where " + PostDbColumnNames.OWNER + " = " + userId + ");\n";

        return query;
    }

    @Override
    public String getVehicleBookingByBookingId(int bookingId) {
        return null;
    }

    @Override
    public String createVehicleBooking(VehicleBooking vehicleBooking) {
        String query = "INSERT INTO " + VehicleBookingDbColumnNames.TABLENAME +
                " (" +
                VehicleBookingDbColumnNames.POST_ID + ", " +
                VehicleBookingDbColumnNames.VEHICLE_ID + ", " +
                VehicleBookingDbColumnNames.TOTAL_KM + ", " +
                VehicleBookingDbColumnNames.BOOKING_START_DATE + ", " +
                VehicleBookingDbColumnNames.BOOKING_END_DATE + ", " +
                VehicleBookingDbColumnNames.HAS_PAID + ") " +
                "VALUES " +
                "(" +
                vehicleBooking.getPostId() + ", " +
                vehicleBooking.getVehicleId() + ", " +
                vehicleBooking.getTotalKm() + ", " +
                "'" + vehicleBooking.getBookingStartDate() + "', " +
                "'" + vehicleBooking.getBookingEndDate() + "', " +
                vehicleBooking.getHasPaid() + ");";
        logger.info(query);
        return query;
    }
}
