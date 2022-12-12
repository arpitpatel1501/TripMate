package grp16.tripmate.vehicle.database;

import grp16.tripmate.vehicle.model.VehicleBooking;

public interface IVehicleBookingQueryBuilder
{
    String getVehicleBookingByPostId(int postId);

    String getVehicleBookingByUserId(int userId);

    String getVehicleBookingByBookingId(int bookingId);
    String createVehicleBooking(VehicleBooking vehicleBooking);
}
