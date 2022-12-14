package grp16.tripmate.vehicle.database.Vehicle;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.vehicle.model.Vehicle.IVehicleFactory;
import grp16.tripmate.vehicle.model.Vehicle.Vehicle;
import grp16.tripmate.vehicle.model.Vehicle.VehicleDbColumnNames;
import grp16.tripmate.vehicle.model.Vehicle.VehicleFactory;
import grp16.tripmate.db.execute.DatabaseExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VehicleDatabase implements IVehicleDatabase {
    private final ILogger logger = new MyLoggerAdapter(this);

    IVehicleQueryBuilder queryBuilder;

    private final IVehicleFactory factory;

    private final IDatabaseExecutor databaseExecutor;

    public VehicleDatabase() {
        queryBuilder = VehiclesQueryBuilder.getInstance();
        databaseExecutor = new DatabaseExecutor();
        factory = VehicleFactory.getInstance();
    }

    public List<Vehicle> getAllVehicles() {
        String query = queryBuilder.getAllVehicles();
        return listToVehicles(databaseExecutor.executeSelectQuery(query));
    }

    private List<Vehicle> listToVehicles(List<Map<String, Object>> resultSet) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Map<String, Object> rs : resultSet) {
            Vehicle vehicleObj = factory.getNewVehicle();
            vehicleObj.setId((Integer) rs.get(VehicleDbColumnNames.ID));
            vehicleObj.setName((String) rs.get(VehicleDbColumnNames.NAME));
            vehicleObj.setNumberOfSeats((Integer) rs.get(VehicleDbColumnNames.NUMBEROFSEATS));
            vehicleObj.setDescription((String) rs.get(VehicleDbColumnNames.DESCRIPTION));
            vehicleObj.setRegistrationNumber((String) rs.get(VehicleDbColumnNames.REGISTRATIONNUMBER));
            vehicleObj.setIsAvailable((Integer) rs.get(VehicleDbColumnNames.ISAVAILABLE) != 0);
            vehicleObj.setIsForLongJourney((Integer) rs.get(VehicleDbColumnNames.ISFORLONGJOURNEY) != 0);
            vehicleObj.setRatePerKm((Float) rs.get(VehicleDbColumnNames.RATEPERKM));
            vehicleObj.setVehicleCategory((Integer) rs.get(VehicleDbColumnNames.VEHICLECATEGORY));
            vehicles.add(vehicleObj);
        }
        logger.info(vehicles.toString());
        return vehicles;
    }

    public Vehicle getVehicleById(int vehicleId) {
        String query = queryBuilder.getVehicleById(vehicleId);
        List<Vehicle> listOfVehicles = listToVehicles(databaseExecutor.executeSelectQuery(query));
        if (listOfVehicles.size() > 0) {
            return listOfVehicles.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteVehiclesByPostId(int postId) {
        String query = queryBuilder.deleteVehiclesByPostId(postId);
        return databaseExecutor.executeDeleteQuery(query);
    }
}
