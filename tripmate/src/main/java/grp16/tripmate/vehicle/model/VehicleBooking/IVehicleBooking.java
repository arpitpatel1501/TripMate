package grp16.tripmate.vehicle.model.VehicleBooking;

import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingDatabase;

import java.util.List;

public interface IVehicleBooking
{
    List<VehicleBooking> getVehicleBookingByUserId(int userId);

    List<VehicleBooking> getVehicleBookingByPostId(int postId);

    VehicleBooking getVehicleBookingByBookingId(int bookingId);

    boolean createVehicleBooking(IVehicleBookingDatabase vehicleBookingDatabaseObj);
}
