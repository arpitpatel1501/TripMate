package grp16.tripmate.vehicle.model;

import grp16.tripmate.vehicle.database.IVehicleBookingPaymentDatabase;
import grp16.tripmate.vehicle.database.IVehicleBookingPaymentQueryBuilder;
import grp16.tripmate.vehicle.database.VehicleBookingPaymentDatabase;
import grp16.tripmate.vehicle.database.VehicleBookingPaymentQueryBuilder;

public class VehicleBookingPaymentFactory implements IVehicleBookingPaymentFactory
{
    private static IVehicleBookingPaymentFactory instance;

    private VehicleBookingPaymentFactory()
    {

    }

    public static VehicleBookingPaymentFactory getInstance() {
        if (instance == null) {
            instance = new VehicleBookingPaymentFactory();
        }
        return (VehicleBookingPaymentFactory) instance;
    }

    @Override
    public VehicleBookingPayment getNewVehicleBookingPayment() {
        return new VehicleBookingPayment();
    }

    @Override
    public IVehicleBookingPaymentDatabase getVehicleBookingPaymentDatabase() {
        return new VehicleBookingPaymentDatabase();
    }

    @Override
    public IVehicleBookingPaymentQueryBuilder getVehicleBookingPaymentQueryBuilder() {
        return VehicleBookingPaymentQueryBuilder.getInstance();
    }
}
