package grp16.tripmate.vehicle.model.VehicleBookingPayment;

import java.text.ParseException;
import java.util.List;

public interface IVehicleBookingPayment {

    List<VehicleBookingPayment> getVehicleBookingPaymentByUserId(int userId) throws ParseException;
}
