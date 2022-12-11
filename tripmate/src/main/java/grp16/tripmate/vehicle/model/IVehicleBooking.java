package grp16.tripmate.vehicle.model;

import java.util.List;

public interface IVehicleBooking
{
    List<VehicleBooking> getVehicleBookingsByUserId(int userId);

    List<VehicleBooking> getVehicleBookingsByPostId(int postId);

    VehicleBooking getVehicleBookingByBookingId(int bookingId);
}
