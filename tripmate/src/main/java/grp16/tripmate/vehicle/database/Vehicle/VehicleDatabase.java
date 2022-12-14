package grp16.tripmate.vehicle.database.Vehicle;

import grp16.tripmate.db.execute.IDatabaseExecutor;
import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.vehicle.model.Vehicle.IVehicleFactory;
import grp16.tripmate.vehicle.model.Vehicle.Vehicle;
import grp16.tripmate.vehicle.model.Vehicle.VehicleDbColumnNames;
import grp16.tripmate.vehicle.model.Vehicle.VehicleFactory;
import grp16.tripmate.db.execute.DatabaseExecutor;
import grp16.tripmate.vehicle.model.VehicleCategory.IVehicleCategory;
import grp16.tripmate.vehicle.model.VehicleCategory.IVehicleCategoryFactory;
import grp16.tripmate.vehicle.model.VehicleCategory.VehicleCategory;
import grp16.tripmate.vehicle.model.VehicleCategory.VehicleCategoryFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VehicleDatabase implements IVehicleDatabase
{
    private final ILogger logger = new MyLoggerAdapter(this);

    IVehicleQueryBuilder queryBuilder;

    private final IVehicleFactory factory;
    private final IVehicleCategoryFactory categoryFactory;

    private final IDatabaseExecutor databaseExecutor;

    public VehicleDatabase()
    {
        queryBuilder = VehiclesQueryBuilder.getInstance();
        databaseExecutor = new DatabaseExecutor();
        factory = VehicleFactory.getInstance();
        categoryFactory = VehicleCategoryFactory.getInstance();
    }

    public List<Vehicle> getAllVehicles() throws ParseException {
        String query = queryBuilder.getAllVehicles();
        List<Vehicle> vehicles = listToVehicles(databaseExecutor.executeSelectQuery(query));
        return assignVehicleCategoryToVehicle(vehicles);
    }

    private List<Vehicle> assignVehicleCategoryToVehicle(List<Vehicle> vehicles) throws ParseException {
        for (Vehicle vehicleObj: vehicles)
        {
            VehicleCategory vehicleCategory = categoryFactory.getVehicleCategoryDatabase().getVehicleCategoryById(vehicleObj.getCategoryId());
            vehicleObj.setCategoryName(vehicleCategory.getName());
            vehicleObj.setVehicleCategory(vehicleCategory);
        }
        return vehicles;
    }

    private List<Vehicle> listToVehicles(List<Map<String, Object>> resultSet){
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
            vehicleObj.setCategoryId((Integer) rs.get(VehicleDbColumnNames.VEHICLECATEGORY));
            vehicles.add(vehicleObj);
        }
        logger.info(vehicles.toString());
        return vehicles;
    }

    public Vehicle getVehicleById(int vehicleId){
        String query = queryBuilder.getVehicleById(vehicleId);
        List<Vehicle> listOfVehicles = listToVehicles(databaseExecutor.executeSelectQuery(query));
        if (listOfVehicles.size() > 0)
        {
            return listOfVehicles.get(0);
        }
        else {
            return null;
        }
    }
}
