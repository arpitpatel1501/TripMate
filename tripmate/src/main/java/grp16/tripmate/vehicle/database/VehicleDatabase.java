package grp16.tripmate.vehicle.database;

import grp16.tripmate.db.connection.DatabaseConnection;
import grp16.tripmate.db.connection.IDatabaseConnection;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.vehicle.model.IVehicleFactory;
import grp16.tripmate.vehicle.model.Vehicle;
import grp16.tripmate.vehicle.model.VehicleFactory;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleDatabase implements IVehicleDatabase
{
    private final ILogger logger = new MyLoggerAdapter(this);

    IVehicleQueryBuilder queryBuilder;

    private final IDatabaseConnection dbConnection;

    private final IVehicleFactory factory;

    public VehicleDatabase()
    {
        queryBuilder = VehiclesQueryBuilder.getInstance();
        dbConnection = new DatabaseConnection();
        factory = VehicleFactory.getInstance();
    }

    public List<Vehicle> resultSetToVehicles(ResultSet rs) throws Exception
    {
        return new ArrayList<>();
    }
    public List<Vehicle> getVehiclesByPostId(int postId)
    {
        return new ArrayList<>();
    }
    public List<Vehicle> getAllVehicles()
    {
        return new ArrayList<>();
    }
    public List<Vehicle> getVehiclesByUserId(int userId)
    {
        return new ArrayList<>();
    }
    public Vehicle getVehicleById(int vehicleId)
    {
        return new Vehicle();
    }
}
