package grp16.tripmate.vehicle.model;

import grp16.tripmate.vehicle.database.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.database.IVehicleBookingQueryBuilder;


public interface IVehicleBookingFactory {
    VehicleBooking getNewVehicleBooking();

    IVehicleBookingDatabase getVehicleBookingDatabase();

    IVehicleBookingQueryBuilder getVehicleBookingQueryBuilder();


}
