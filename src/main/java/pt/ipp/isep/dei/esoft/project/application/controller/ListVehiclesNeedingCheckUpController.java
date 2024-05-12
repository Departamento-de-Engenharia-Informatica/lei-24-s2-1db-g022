package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class ListVehiclesNeedingCheckUpController {

    private VehicleRepository vehicleRepository;

    public ListVehiclesNeedingCheckUpController() {
        getVehicleRepository();
    }

    public List<Vehicle> getVehiclesNeedingCheckUp() {
        return vehicleRepository.getVehiclesNeedingCheckUp();
    }


    private VehicleRepository getVehicleRepository(){
        if(vehicleRepository == null){
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }
}