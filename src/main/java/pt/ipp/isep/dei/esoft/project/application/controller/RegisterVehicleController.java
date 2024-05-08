package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Brand;
import pt.ipp.isep.dei.esoft.project.domain.Model;
import pt.ipp.isep.dei.esoft.project.repository.BrandRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.Optional;

/**
 * The RegisterVehicleController class manages the registration of vehicles.
 *
 * @author Group22
 */
public class RegisterVehicleController {

    private VehicleRepository vehicleRepository;
    private BrandRepository brandRepository;

    /**
     * Constructs a RegisterVehicleController object with a default VehicleRepository instance.
     */
    public RegisterVehicleController() {
        getVehicleRepository();
        getBrandRepository();
    }

    /**
     * Constructs a RegisterVehicleController object with a specified VehicleRepository instance.
     *
     * @param vehicleRepository The VehicleRepository instance to use.
     */
    public RegisterVehicleController(VehicleRepository vehicleRepository, BrandRepository brandRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
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
     * Retrieves the BrandRepository instance.
     * If not initialized, it gets the BrandRepository from the Repositories singleton.
     *
     * @return The BrandRepository instance.
     */
    private BrandRepository getBrandRepository() {
        if (brandRepository == null) {
            Repositories repositories = Repositories.getInstance();
            brandRepository = repositories.getBrandRepository();
        }
        return brandRepository;
    }

    /**
     * Retrieves a brand by its name along with a specific model, if available.
     *
     * @param brandName The name of the brand.
     * @param modelName The name of the model associated with the brand.
     * @return An Optional containing the Brand object if found, otherwise empty.
     */
    private Optional<Brand> getBrand(String brandName, String modelName) {
        Optional<Brand> brand = Optional.empty();

        if (getBrandRepository().hasBrandByName(brandName)) {
            brand = getBrandRepository().getBrandByModelName(modelName);

        }
        return brand;
    }

    /**
     * Registers a new vehicle with the given name.
     *
     * @param type             The type of the vehicle.
     * @param tare             The tare of the vehicle.
     * @param grossWeight      The grossWeight of the vehicle.
     * @param currentKm        The currentKm of the vehicle.
     * @param registerDate     The registerDate of the vehicle.
     * @param acquisitionDate  The acquisitionDate of the vehicle.
     * @param checkUpFrequency The checkUpFrequency of the vehicle.
     * @param licensePlate     The licensePlate of the vehicle.
     * @param brandName        The brand of the vehicle.
     * @param modelName        The model of the vehicle.
     * @return An Optional containing the registered Vehicle, or empty if the registration fails.
     */
    public Optional<Vehicle> registerVehicle(String type, float tare, float grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkUpFrequency, String licensePlate, String brandName, String modelName) {
        Optional<Vehicle> newVehicle = Optional.empty();
        Optional<Brand> brand = Optional.empty();

        brand = getBrand(brandName, modelName);

        if (brand.isPresent()) {

            Optional<Model> model = Optional.empty();

            newVehicle = getVehicleRepository().createVehicle(type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkUpFrequency, licensePlate, new Brand(brandName), new Model(modelName));
        }
        return newVehicle;
    }
}
