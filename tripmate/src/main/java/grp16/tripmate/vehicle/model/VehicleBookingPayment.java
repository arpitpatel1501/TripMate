package grp16.tripmate.vehicle.model;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.vehicle.database.IVehicleBookingPaymentDatabase;
import grp16.tripmate.vehicle.database.IVehicleBookingPaymentQueryBuilder;
import grp16.tripmate.vehicle.database.IVehicleDatabase;
import grp16.tripmate.vehicle.database.IVehicleQueryBuilder;

import java.util.Date;
import java.util.List;

public class VehicleBookingPayment implements IVehicleBookingPayment
{

    private final ILogger logger = new MyLoggerAdapter(this);
    private int paymentId;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    private float amount;

    private Date createdOn;

    private int vehicleBookingId;

    public int getVehicleBookingId() {
        return vehicleBookingId;
    }

    public void setVehicleBookingId(int vehicleBookingId) {
        this.vehicleBookingId = vehicleBookingId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    private final IDatabaseConnection dbConnection;
    private final IVehicleBookingPaymentQueryBuilder queryBuilder;

    private static IVehicleBookingPaymentFactory vehicleBookingPaymentFactory = null;

    private final IVehicleBookingPaymentDatabase database;

    public VehicleBookingPayment()
    {
        vehicleBookingPaymentFactory = VehicleBookingPaymentFactory.getInstance();
        database = vehicleBookingPaymentFactory.getVehicleBookingPaymentDatabase();
        queryBuilder = vehicleBookingPaymentFactory.getVehicleBookingPaymentQueryBuilder();
        dbConnection = new DatabaseConnection();
    }
    @Override
    public List<VehicleBookingPayment> getVehicleBookingPaymentByUserId(int userId)
    {
        return database.getVehicleBookingPaymentByUserId(userId);
    }
}
