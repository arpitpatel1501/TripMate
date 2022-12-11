package grp16.tripmate.vehicle.model;

import grp16.tripmate.vehicle.database.IVehicleDatabase;
import grp16.tripmate.vehicle.database.IVehicleQueryBuilder;
import grp16.tripmate.vehicle.database.VehicleDatabase;
import grp16.tripmate.vehicle.database.VehiclesQueryBuilder;

public class VehicleFactory implements IVehicleFactory
{
    private static IVehicleFactory instance;

    private VehicleFactory()
    {
        // private constructor for Singleton class
    }

    public static VehicleFactory getInstance() {
        if (instance == null) {
            instance = new VehicleFactory();
        }
        return (VehicleFactory) instance;
    }

    @Override
    public Vehicle getNewVehicle()
    {
        return new Vehicle();
    }
    @Override
    public IVehicleDatabase getVehicleDataBase()
    {
        return new VehicleDatabase();
    }
    public IVehicleQueryBuilder getVehicleQueryBuilder()
    {
        return VehiclesQueryBuilder.getInstance();
    }
}
