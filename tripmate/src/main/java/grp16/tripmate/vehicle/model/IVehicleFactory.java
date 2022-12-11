package grp16.tripmate.vehicle.model;

import grp16.tripmate.vehicle.database.IVehicleDatabase;
import grp16.tripmate.vehicle.database.IVehicleQueryBuilder;

public interface IVehicleFactory
{
    Vehicle getNewVehicle();
    IVehicleDatabase getVehicleDataBase();
    IVehicleQueryBuilder getVehicleQueryBuilder();

}
