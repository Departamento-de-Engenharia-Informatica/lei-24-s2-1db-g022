package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.model.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.model.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Date;
import java.util.Optional;

/**
 * Controller class for registering vehicle check-ups.
 *
 * @author Group22
 */
public class RegisterCheckUpController {
    private VehicleRepository vehicleRepository;

    /**
     * Constructor to initialize repositories and vehicle-related objects.
     */
    public RegisterCheckUpController() {
        getVehicleRepository();
    }

    /**
     * Retrieves the VehicleRepository instance.
     * If not initialized, it gets the VehicleRepository from the Repositories singleton.
     *
     * @return The VehicleRepository instance.
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Registers a check-up for a vehicle.
     *
     * @param licensePlate       The license plate of the vehicle.
     * @param checkUpDate        The date of the check-up.
     * @param checkUpCurrenteKms The current kilometers of the vehicle at the time of the check-up.
     * @return An optional containing the registered check-up if successful, otherwise empty.
     */
    public Optional<CheckUp> registerCheckUp(String licensePlate, Date checkUpDate, int checkUpCurrenteKms) {
        Optional<Vehicle> vehicleOptional = Optional.empty();
        Optional<CheckUp> checkUpOptional = Optional.empty();
        vehicleOptional = getVehicleByPlate(licensePlate);

        if (vehicleOptional.isPresent()) {
            checkUpOptional = vehicleOptional.get().regiterCheckUpByVehicle(checkUpDate, checkUpCurrenteKms);
        }

        return checkUpOptional;
    }

    /**
     * Retrieves a vehicle by its license plate.
     *
     * @param licensePlate The license plate of the vehicle to retrieve.
     * @return An optional containing the vehicle if found, otherwise empty.
     */
    private Optional<Vehicle> getVehicleByPlate(String licensePlate) {
        Optional<Vehicle> vehicle;
        vehicle = Optional.of(getVehicleRepository().getVehicleByPlate(licensePlate));
        return vehicle;
    }

}
