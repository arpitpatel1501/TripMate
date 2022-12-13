package grp16.tripmate.vehicle.model.VehicleBooking;

import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingQueryBuilder;


public interface IVehicleBookingFactory {
    VehicleBooking getNewVehicleBooking();

    IVehicleBookingDatabase getVehicleBookingDatabase();

    IVehicleBookingQueryBuilder getVehicleBookingQueryBuilder();


}
