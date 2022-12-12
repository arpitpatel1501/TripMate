package grp16.tripmate.vehicle.database;

import grp16.tripmate.vehicle.model.IVehicleBooking;
import grp16.tripmate.vehicle.model.VehicleBooking;

import java.sql.ResultSet;
import java.util.List;

public interface IVehicleBookingDatabase {
    List<VehicleBooking> resultSetToVehicleBooking(ResultSet rs) throws Exception;

    List<VehicleBooking> getVehicleBookingByPostId(int postId);

    List<VehicleBooking> getVehicleBookingByUserId(int userId);

    VehicleBooking getVehicleBookingByBookingId(int bookingId);

    boolean createVehicleBooking(IVehicleBooking vehicleBooking);

}
