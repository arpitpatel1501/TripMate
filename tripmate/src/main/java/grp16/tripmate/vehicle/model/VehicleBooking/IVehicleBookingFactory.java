package grp16.tripmate.vehicle.model.VehicleBooking;

import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingDatabase;
import grp16.tripmate.vehicle.database.VehicleBooking.IVehicleBookingQueryGenerator;


public interface IVehicleBookingFactory {
    VehicleBooking getNewVehicleBooking();

    IVehicleBookingDatabase getVehicleBookingDatabase();

    IVehicleBookingQueryGenerator getVehicleBookingQueryBuilder();


    VehicleBookingValidator getVehicleBookingValidator();
}
