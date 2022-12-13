package grp16.tripmate.vehicle.model.VehicleBookingPayment;

import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.database.VehicleBookingPayment.IVehicleBookingPaymentDatabase;

import java.text.ParseException;
import java.util.List;

public interface IVehicleBookingPayment {

    List<VehicleBookingPayment> getVehicleBookingPaymentByUserId(int userId) throws ParseException;

    boolean createVehicleBookingPayment(IVehicleBookingPaymentDatabase vehicleBookingPayment);
}
