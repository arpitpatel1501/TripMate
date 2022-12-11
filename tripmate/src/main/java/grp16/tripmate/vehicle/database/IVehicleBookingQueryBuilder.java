package grp16.tripmate.vehicle.database;

public interface IVehicleBookingQueryBuilder
{
    String getVehicleBookingByPostId(int postId);

    String getVehicleBookingByUserId(int userId);

    String getVehicleBookingByBookingId(int bookingId);
}
