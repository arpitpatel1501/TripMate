package grp16.tripmate.vehicle.model.VehicleBookingPayment;

import grp16.tripmate.vehicle.database.VehicleBookingPayment.IVehicleBookingPaymentDatabase;
import grp16.tripmate.vehicle.database.VehicleBookingPayment.IVehicleBookingPaymentQueryGenerator;
import grp16.tripmate.vehicle.database.VehicleBookingPayment.VehicleBookingPaymentDatabase;
import grp16.tripmate.vehicle.database.VehicleBookingPayment.VehicleBookingPaymentQueryGenerator;

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
    public IVehicleBookingPaymentQueryGenerator getVehicleBookingPaymentQueryBuilder() {
        return VehicleBookingPaymentQueryGenerator.getInstance();
    }
}
