package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.model.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.model.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Date;
import java.util.Optional;

public class RegisterCheckUpController {
    private VehicleRepository vehicleRepository;

    public RegisterCheckUpController() {
        getVehicleRepository();
    }

    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }


    public Optional<CheckUp> registerCheckUp(String licensePlate, Date checkUpDate, int checkUpCurrenteKms) {
        Optional<Vehicle> vehicleOptional = Optional.empty();
        Optional<CheckUp> checkUpOptional = Optional.empty();
        vehicleOptional = getVehicleByPlate(licensePlate);

        if (vehicleOptional.isPresent()) {
            checkUpOptional = vehicleOptional.get().regiterCheckUpByVehicle(checkUpDate, checkUpCurrenteKms);
        }

        return checkUpOptional;
    }

    private Optional<Vehicle> getVehicleByPlate(String licensePlate) {
        Optional<Vehicle> vehicle;
        vehicle = Optional.of(getVehicleRepository().getVehicleByPlate(licensePlate));
        return vehicle;
    }

}
