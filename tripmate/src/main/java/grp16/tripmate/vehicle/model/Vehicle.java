package grp16.tripmate.vehicle.model;

import grp16.tripmate.logger.ILogger;
import grp16.tripmate.logger.MyLoggerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Vehicle implements IVehicle
{
    private final ILogger logger = new MyLoggerAdapter(this);

    private int id;
    private String name;
    private int numberOfSeats;
    private String registrationNumber;
    private boolean isAvailable;
    private boolean isForLongJourney;
    private float ratePerKm;
    private String description;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public float getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(float ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private VehicleCategory vehicleCategory;

    public String getVehicleCategory() {
        return vehicleCategory.getName();
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isForLongJourney() {
        return isForLongJourney;
    }

    public void setForLongJourney(boolean forLongJourney) {
        isForLongJourney = forLongJourney;
    }
    public List<Vehicle> getAllVehicles()
    {
        return new ArrayList<>();
    }

    public Vehicle getVehicleById(int vehicleId)
    {
        return new Vehicle();
    }

    public List<Vehicle> getTripVehicles(int postId)
    {
        return new ArrayList<>();
    }

    public List<Vehicle> getRecommendedTripVehicles(int postId)
    {
        return new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByUserId(int userId)
    {
        return new ArrayList<>();
    }
}
