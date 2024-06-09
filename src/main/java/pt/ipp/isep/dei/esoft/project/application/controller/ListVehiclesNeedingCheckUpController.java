package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.model.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

/**
 * Controller class for listing vehicles needing check-up.
 *
 * @author Group22
 */
public class ListVehiclesNeedingCheckUpController {

    private VehicleRepository vehicleRepository;

    /**
     * Constructs a new ListVehiclesNeedingCheckUpController object.
     * Initializes the controller with the necessary dependencies.
     */
    public ListVehiclesNeedingCheckUpController() {
        getVehicleRepository();
    }

    /**
     * Retrieves the list of vehicles needing check-up.
     *
     * @return The list of vehicles needing check-up.
     */
    public List<Vehicle> getVehiclesNeedingCheckUp() {
        return vehicleRepository.getVehiclesNeedingCheckUp();
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
}