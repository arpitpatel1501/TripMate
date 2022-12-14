package grp16.tripmate.vehicle.database.Vehicle;

import grp16.tripmate.vehicle.database.VehicleBooking.VehicleBookingDbColumnNames;


public class VehiclesQueryGenerator implements IVehicleQueryGenerator {
    private static VehiclesQueryGenerator instance;

    private VehiclesQueryGenerator() {

    }

    public static VehiclesQueryGenerator getInstance() {
        if (instance == null) {
            instance = new VehiclesQueryGenerator();
        }
        return instance;
    }

    @Override
    public String getAllVehicles() {
        String query = "SELECT `" + VehicleDbColumnNames.ID + "`, `" + VehicleDbColumnNames.NAME + "`, `" + VehicleDbColumnNames.NUMBEROFSEATS + "`, `" + VehicleDbColumnNames.REGISTRATIONNUMBER + "`, `" + VehicleDbColumnNames.ISAVAILABLE + "`, `" + VehicleDbColumnNames.ISFORLONGJOURNEY + "`, `" + VehicleDbColumnNames.RATEPERKM + "`, `" + VehicleDbColumnNames.DESCRIPTION + "`, `" + VehicleDbColumnNames.VEHICLECATEGORY + "` " + "FROM " + VehicleDbColumnNames.TABLENAME + " WHERE " + VehicleDbColumnNames.ISAVAILABLE + "=1;";
        return query;
    }

    @Override
    public String getVehicleById(int vehicleId) {
        String query = "SELECT `" + VehicleDbColumnNames.ID + "`, `" + VehicleDbColumnNames.NAME + "`, `" + VehicleDbColumnNames.NUMBEROFSEATS + "`, `" + VehicleDbColumnNames.REGISTRATIONNUMBER + "`, `" + VehicleDbColumnNames.ISAVAILABLE + "`, `" + VehicleDbColumnNames.ISFORLONGJOURNEY + "`, `" + VehicleDbColumnNames.RATEPERKM + "`, `" + VehicleDbColumnNames.DESCRIPTION + "`, `" + VehicleDbColumnNames.VEHICLECATEGORY + "` FROM " + VehicleDbColumnNames.TABLENAME + " WHERE " + VehicleDbColumnNames.ID + "=" + vehicleId + " and " + VehicleDbColumnNames.ISAVAILABLE + "=1;";
        return query;
    }

    @Override
    public String getVehiclesByPostId(int postId) {
        return "";
    }

    @Override
    public String getVehiclesByUserId(int userId) {
        return "";
    }

    @Override
    public String deleteVehiclesByPostId(int postId) {
        return "DELETE FROM " + VehicleBookingDbColumnNames.TABLENAME + " WHERE " + VehicleBookingDbColumnNames.POST_ID + " = " + postId;
    }
}