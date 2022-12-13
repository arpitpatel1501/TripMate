package grp16.tripmate.vehicle.database.VehicleBookingPayment;

import grp16.tripmate.vehicle.model.VehicleBookingPayment.IVehicleBookingPayment;
import grp16.tripmate.vehicle.model.VehicleBookingPayment.VehicleBookingPayment;

import java.text.ParseException;
import java.util.List;

public interface IVehicleBookingPaymentDatabase
{
    List<VehicleBookingPayment> getVehicleBookingPaymentByUserId(int userId) throws ParseException;

    boolean createVehicleBookingPayment(IVehicleBookingPayment vehicleBookingPayment);

}
