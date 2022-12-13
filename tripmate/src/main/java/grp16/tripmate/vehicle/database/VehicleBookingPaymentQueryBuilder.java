package grp16.tripmate.vehicle.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.database.PostDbColumnNames;
import grp16.tripmate.vehicle.model.VehicleBookingDbColumnNames;
import grp16.tripmate.vehicle.model.VehicleBookingPayment;
import grp16.tripmate.vehicle.model.VehicleBookingPaymentDbColumns;
import grp16.tripmate.vehicle.model.VehicleDbColumnNames;

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
        return query;
    }
}
