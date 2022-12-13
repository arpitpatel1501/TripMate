package grp16.tripmate.vehicle.database;

import grp16.tripmate.vehicle.model.IVehicleBookingPayment;
import grp16.tripmate.vehicle.model.VehicleBookingPayment;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.List;

public interface IVehicleBookingPaymentDatabase
{
    List<VehicleBookingPayment> getVehicleBookingPaymentByUserId(int userId) throws ParseException;

    boolean createVehicleBookingPayment(IVehicleBookingPayment vehicleBookingPayment);

}
