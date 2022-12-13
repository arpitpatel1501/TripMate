package grp16.tripmate.vehicle.model.Vehicle;

import grp16.tripmate.vehicle.database.Vehicle.IVehicleDatabase;
import grp16.tripmate.vehicle.database.Vehicle.IVehicleQueryBuilder;
import grp16.tripmate.vehicle.database.Vehicle.VehicleDatabase;
import grp16.tripmate.vehicle.database.Vehicle.VehiclesQueryBuilder;

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
