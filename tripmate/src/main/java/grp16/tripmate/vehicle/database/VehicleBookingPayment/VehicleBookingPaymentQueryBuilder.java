package grp16.tripmate.vehicle.database.VehicleBookingPayment;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.PostDbColumnNames;
import grp16.tripmate.vehicle.model.Vehicle.VehicleDbColumnNames;
import grp16.tripmate.vehicle.model.VehicleBooking.VehicleBookingDbColumnNames;
import grp16.tripmate.vehicle.model.VehicleBookingPayment.VehicleBookingPayment;
import grp16.tripmate.vehicle.model.VehicleBookingPayment.VehicleBookingPaymentDbColumns;

public class VehicleBookingPaymentQueryBuilder implements IVehicleBookingPaymentQueryBuilder
{
    private final ILogger logger = new MyLoggerAdapter(this);
    private static VehicleBookingPaymentQueryBuilder instance;

    private VehicleBookingPaymentQueryBuilder()
    {

    }

    public static VehicleBookingPaymentQueryBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new VehicleBookingPaymentQueryBuilder();
        }
        return instance;
    }

    @Override
    public String getVehicleBookingPaymentByUserId(int userId) {
        String query = "select vbp."+ VehicleBookingPaymentDbColumns.ID + ", vbp." +
                VehicleBookingPaymentDbColumns.VEHICLE_BOOKING_ID + ", vbp." +
                VehicleBookingPaymentDbColumns.AMOUNT + ", vbp." +
                VehicleBookingPaymentDbColumns.CREATED_ON + " from " +
                VehicleBookingPaymentDbColumns.TABLENAME + " vbp inner join " +
                VehicleBookingDbColumnNames.TABLENAME + " vb on vbp." +
                VehicleBookingPaymentDbColumns.VEHICLE_BOOKING_ID + "=vb." +
                VehicleBookingDbColumnNames.ID + " inner join " +
                PostDbColumnNames.TABLE_NAME + " p on p." +
                PostDbColumnNames.ID + "=vb." +
                VehicleBookingDbColumnNames.POST_ID + " inner join " +
                VehicleDbColumnNames.TABLENAME + " v on v." +
                VehicleDbColumnNames.ID + "=vb." +
                VehicleBookingDbColumnNames.VEHICLE_ID + " where p." +
                PostDbColumnNames.OWNER + "=" + userId + ";";
        logger.info("Vehicle Booking Payment records: " + query);
        return query;
    }

    @Override
    public String createVehicleBookingPayment(VehicleBookingPayment vehicleBookingPayment)
    {
        String query = "INSERT INTO " + VehicleBookingPaymentDbColumns.TABLENAME +
                "(" +
                VehicleBookingPaymentDbColumns.AMOUNT + ", " +
                VehicleBookingPaymentDbColumns.VEHICLE_BOOKING_ID + ", " +
                VehicleBookingPaymentDbColumns.CREATED_ON + ") " +
                "VALUES " +
                '(' +
                vehicleBookingPayment.getAmount() + ", " +
                vehicleBookingPayment.getVehicleBookingId() + ", " +
                "'" + vehicleBookingPayment.getCreatedOn() + "');";
        logger.info(query);
        return query;
    }
}
