package grp16.tripmate.vehicle.model.Vehicle;

import grp16.tripmate.vehicle.database.Vehicle.IVehicleDatabase;
import grp16.tripmate.vehicle.database.Vehicle.IVehicleQueryGenerator;

public interface IVehicleFactory
{
    Vehicle getNewVehicle();
    IVehicleDatabase getVehicleDataBase();
    IVehicleQueryGenerator getVehicleQueryBuilder();

}
