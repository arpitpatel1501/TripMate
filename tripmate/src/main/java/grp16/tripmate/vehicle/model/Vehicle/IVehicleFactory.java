package grp16.tripmate.vehicle.model.Vehicle;

import grp16.tripmate.vehicle.database.Vehicle.IVehicleDatabase;
import grp16.tripmate.vehicle.database.Vehicle.IVehicleQueryBuilder;
import grp16.tripmate.vehicle.model.Vehicle.Vehicle;

public interface IVehicleFactory
{
    Vehicle getNewVehicle();
    IVehicleDatabase getVehicleDataBase();
    IVehicleQueryBuilder getVehicleQueryBuilder();

}
