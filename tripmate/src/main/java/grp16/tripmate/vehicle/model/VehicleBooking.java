package grp16.tripmate.vehicle.model;
import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.Post;
import grp16.tripmate.vehicle.database.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.database.IVehicleBookingQueryBuilder;

import java.util.List;
import java.util.Date;

public class VehicleBooking implements IVehicleBooking
{
    private final ILogger logger = new MyLoggerAdapter(this);

    private int id;
    private Vehicle vehicleObj;
    private Post postObj;
    private float totalKm;
    private Date bookingStartDate;
    private Date bookingEndDate;
    private boolean hasPaid;
    private final IDatabaseConnection dbConnection;
    private final IVehicleBookingQueryBuilder queryBuilder;

    private static IVehicleBookingFactory vehicleBookingFactory = null;

    private final IVehicleBookingDatabase database;

    public VehicleBooking()
    {
        vehicleBookingFactory = VehicleBookingFactory.getInstance();
        database = vehicleBookingFactory.getVehicleBookingDatabase();
        queryBuilder = vehicleBookingFactory.getVehicleBookingQueryBuilder();
        dbConnection = new DatabaseConnection();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicleObj() {
        return vehicleObj;
    }

    public void setVehicleObj(Vehicle vehicleObj) {
        this.vehicleObj = vehicleObj;
    }

    public Post getPostObj() {
        return postObj;
    }

    public void setPostObj(Post postObj) {
        this.postObj = postObj;
    }

    public Date getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(Date bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public Date getBookingEndDate() {
        return bookingEndDate;
    }

    public float getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(float totalKm) {
        this.totalKm = totalKm;
    }

    public boolean getHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public void setBookingEndDate(Date bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }
    @Override
    public List<VehicleBooking> getVehicleBookingByUserId(int userId)
    {
        return null;
    }

    @Override
    public List<VehicleBooking> getVehicleBookingByPostId(int postId)
    {
        return null;
    }

    @Override
    public VehicleBooking getVehicleBookingByBookingId(int bookingId)
    {
        return null;
    }
}
