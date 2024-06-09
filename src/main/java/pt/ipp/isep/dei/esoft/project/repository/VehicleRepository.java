package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.model.Brand;
import pt.ipp.isep.dei.esoft.project.domain.model.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.model.Model;
import pt.ipp.isep.dei.esoft.project.domain.model.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The VehicleRepository class manages the storage and retrieval of vehicle objects.
 * It provides methods for adding and retrieving vehicles from the repository.
 *
 * @author Group22
 */
public class VehicleRepository {

    private final List<Vehicle> vehicleList;
    private List<CheckUp> checkUpList;

    /**
     * Constructs a VehicleRepository object.
     */
    public VehicleRepository() {
        vehicleList = new ArrayList<>();
    }

    /**
     * Adds a vehicle to the repository.
     *
     * @param vehicle The vehicle to add.
     * @return True if the vehicle is successfully added, false otherwise.
     */
    private boolean addVehicle(Vehicle vehicle) {
        boolean success = false;
        if (validateVehicle(vehicle)) {
            // A clone of the vehicle is added to the list of vehicles, to avoid side effects and outside manipulation.
            success = vehicleList.add(vehicle.clone());
        }
        return success;
    }

    /**
     * Adds a check-up entry to the list of check-ups for this vehicle.
     *
     * @param checkUp The check-up to add.
     */
    public void addCheckUp(CheckUp checkUp) {
        this.checkUpList.add(checkUp);
    }

    /**
     * Validates if the vehicle is not already present in the repository.
     *
     * @param vehicle The vehicle to validate.
     * @return True if the vehicle is valid (not already present), false otherwise.
     */
    private boolean validateVehicle(Vehicle vehicle) {

        for (Vehicle vehicleCopy : vehicleList) {

            if (vehicle.validateVehicleLicense(vehicleCopy)) {

                return false;
            }
        }

        return !vehicleList.contains(vehicle);
    }


    /**
     * Creates a new vehicle with the specified name and adds it to the repository.
     *
     * @param type             The type of the vehicle.
     * @param tare             The tare of the vehicle.
     * @param grossWeight      The grossWeight of the vehicle.
     * @param currentKm        The currentKm of the vehicle.
     * @param registerDate     The registerDate of the vehicle.
     * @param acquisitionDate  The acquisitionDate of the vehicle.
     * @param checkUpFrequency The checkUpFrequency of the vehicle.
     * @param licensePlate     The licensePlate of the vehicle.
     * @param brand            The brand of the vehicle.
     * @param model            The model of the vehicle.
     * @return An optional containing the created vehicle if successful, empty optional otherwise.
     */
    public Optional<Vehicle> createVehicle(String type, float tare, float grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkUpFrequency, String licensePlate, Brand brand, Model model) {
        // When a Vehicle is added, it should fail if the Vehicle already exists in the list of Vehicles.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Vehicle> optionalVehicle = Optional.empty();

        Vehicle vehicle = new Vehicle(type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkUpFrequency, licensePlate, brand, model);

        if (addVehicle(vehicle)) {

            optionalVehicle = Optional.of(vehicle);
        }
        return optionalVehicle;
    }

    /**
     * Retrieves a list of vehicles that are in need of a check-up.
     * <p>
     * This method iterates through the list of vehicles in the repository and checks if each vehicle requires a check-up.
     * A vehicle is considered to need a check-up if its check-up is overdue based on its check-up frequency and the current date.
     * Vehicles needing check-up are added to a new list, which is then returned.
     *
     * @return A list containing vehicles that are in need of a check-up.
     */
    public List<Vehicle> getVehiclesNeedingCheckUp() {
        List<Vehicle> vehiclesNeedingCheckUp = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.isCheckUpNeeded()) {
                vehiclesNeedingCheckUp.add(vehicle);
            }
        }
        return vehiclesNeedingCheckUp;
    }

    /**
     * Retrieves a vehicle by its license plate.
     *
     * @param licensePlate The license plate of the vehicle to retrieve.
     * @return The vehicle with the specified license plate.
     * @throws IllegalArgumentException If the vehicle with the given license plate does not exist.
     */
    public Vehicle getVehicleByPlate(String licensePlate) {
        Vehicle newVeiVehicle = new Vehicle(licensePlate);
        Vehicle vehicle = null;
        for (Vehicle vehicleFor : vehicleList) {
            if (vehicleFor.hasLicensePlateEquals(newVeiVehicle)) {
                vehicle = vehicleFor;
                break;
            }
        }

        if (vehicle == null) {
            throw new IllegalArgumentException(
                    "Vehicle requested for [" + licensePlate + "] does not exit.");
        }
        return vehicle;
    }

    /**
     * Adds a vehicle to the bootstrap list of vehicles.
     *
     * @param vehicle The vehicle to add.
     */
    public void addVehicleBootstrap(Vehicle vehicle) {
        vehicleList.add(vehicle);

    }
}

