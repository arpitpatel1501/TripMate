package grp16.tripmate.vehicle.database;

import grp16.tripmate.vehicle.model.IVehicleBooking;
import grp16.tripmate.vehicle.model.IVehicleBookingPayment;
import grp16.tripmate.vehicle.model.VehicleBookingPayment;

public interface IVehicleBookingPaymentQueryBuilder
{
    String getVehicleBookingPaymentByUserId(int userId);

    String createVehicleBookingPayment(VehicleBookingPayment vehicleBookingPayment);
}
