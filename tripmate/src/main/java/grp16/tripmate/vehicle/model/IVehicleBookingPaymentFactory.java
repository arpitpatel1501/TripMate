package grp16.tripmate.vehicle.model;

import grp16.tripmate.vehicle.database.IVehicleBookingPaymentDatabase;
import grp16.tripmate.vehicle.database.IVehicleBookingPaymentQueryBuilder;

public interface IVehicleBookingPaymentFactory
{
    VehicleBookingPayment getNewVehicleBookingPayment();

    IVehicleBookingPaymentDatabase getVehicleBookingPaymentDatabase();

    IVehicleBookingPaymentQueryBuilder getVehicleBookingPaymentQueryBuilder();
}
