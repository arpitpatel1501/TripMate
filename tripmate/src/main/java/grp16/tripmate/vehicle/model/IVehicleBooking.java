package grp16.tripmate.vehicle.model;

import java.util.List;

public interface IVehicleBooking
{
    List<VehicleBooking> getVehicleBookingByUserId(int userId);

    List<VehicleBooking> getVehicleBookingByPostId(int postId);

    VehicleBooking getVehicleBookingByBookingId(int bookingId);
}
