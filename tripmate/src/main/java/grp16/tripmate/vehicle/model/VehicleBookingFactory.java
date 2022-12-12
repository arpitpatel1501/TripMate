package grp16.tripmate.vehicle.model;

import grp16.tripmate.vehicle.database.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.database.IVehicleBookingQueryBuilder;
import grp16.tripmate.vehicle.database.VehicleBookingDatabase;

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
