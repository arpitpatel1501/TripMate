package grp16.tripmate.vehicle.model;

import java.util.List;

public interface IVehicleBookingPayment {

    List<VehicleBookingPayment> getVehicleBookingPaymentByUserId(int userId);
}
