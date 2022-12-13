package grp16.tripmate.vehicle.database.VehicleBookingPayment;

import grp16.tripmate.vehicle.model.VehicleBooking.IVehicleBooking;
import grp16.tripmate.vehicle.model.VehicleBookingPayment.VehicleBookingPayment;

public interface IVehicleBookingPaymentQueryBuilder
{
    String getVehicleBookingPaymentByUserId(int userId);

    String createVehicleBookingPayment(VehicleBookingPayment vehicleBookingPayment);
}
