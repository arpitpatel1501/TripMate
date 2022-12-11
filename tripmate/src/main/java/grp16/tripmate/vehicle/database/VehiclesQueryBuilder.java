package grp16.tripmate.vehicle.database;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;
import grp16.tripmate.post.model.PostDbColumnNames;
import grp16.tripmate.vehicle.model.VehicleDbColumnNames;


public class VehiclesQueryBuilder implements IVehicleQueryBuilder
{
    private final ILogger logger = new MyLoggerAdapter(this);
    private static VehiclesQueryBuilder instance;

    private VehiclesQueryBuilder()
    {
        // For commit - remove this comment when you see

    }

    public static VehiclesQueryBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new VehiclesQueryBuilder();
        }
        return instance;
    }

    @Override
    public String getAllVehicles()
    {
        String query = "SELECT `Vehicle`.`id`,\n" +
                                "`Vehicle`.`name`,\n" +
                                "`Vehicle`.`no_of_seats`,\n" +
                                "`Vehicle`.`registration_numb`,\n" +
                                "`Vehicle`.`is_available`,\n" +
                                "`Vehicle`.`is_for_long_journey`,\n" +
                                "`Vehicle`.`rate_per_km`,\n" +
                                "`Vehicle`.`description`,\n" +
                                "`Vehicle`.`VechicleCategory_id`\n" +
                                "FROM `CSCI5308_16_DEVINT`.`Vehicle`" +
                                " where " + VehicleDbColumnNames.ISAVAILABLE + " = 1;";
        logger.info(query);
        return query;

    }

    @Override
    public String getVehicleById(int vehicleId)
    {
        return "";
    }

    @Override
    public String getVehiclesByPostId(int postId)
    {
        return "";
    }

    @Override
    public String getVehiclesByUserId(int userId)
    {
        return "";
    }
}
