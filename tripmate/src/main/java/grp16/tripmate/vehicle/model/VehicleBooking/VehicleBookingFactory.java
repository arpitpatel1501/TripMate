package grp16.tripmate.vehicle.model.VehicleBooking;

import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingQueryBuilder;
import grp16.tripmate.vehicle.database.VehicleBooking.VehicleBookingDatabase;

public class VehicleBookingFactory implements IVehicleBookingFactory {
    private static IVehicleBookingFactory instance;

    private VehicleBookingFactory() {

    }

    public static VehicleBookingFactory getInstance() {
        if (instance == null) {
            instance = new VehicleBookingFactory();
        }
        return (VehicleBookingFactory) instance;
    }

    @Override
    public VehicleBooking getNewVehicleBooking() {
        return new VehicleBooking();
    }

    @Override
    public IVehicleBookingDatabase getVehicleBookingDatabase() {
        return new VehicleBookingDatabase();
    }

    @Override
    public IVehicleBookingQueryBuilder getVehicleBookingQueryBuilder() {
        return null;
    }
}
