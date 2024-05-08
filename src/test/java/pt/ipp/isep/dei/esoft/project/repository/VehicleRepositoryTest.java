package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Brand;
import pt.ipp.isep.dei.esoft.project.domain.Model;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contains unit tests for the VehicleRepository class.
 * Validates the functionality of the createVehicle method.
 *
 * @author Group22
 */
public class VehicleRepositoryTest {

    /**
     * Tests if the createVehicle method successfully creates a vehicle.
     */
    @Test
    void TestCreateVehicle() {
        VehicleRepository vehicleRepository = new VehicleRepository();

        Optional<Vehicle> result = vehicleRepository.createVehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));

        assertTrue(result.isPresent());
    }

    /**
     * Tests if creating a vehicle with a duplicate license plate fails.
     */
    @Test
    void ensureCreateVehicleDuplicateLicenseFails() {
        VehicleRepository vehicleRepository = new VehicleRepository();

        vehicleRepository.createVehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));

        Optional<Vehicle> result = vehicleRepository.createVehicle("Big Truck",10000,7500,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));

        assertTrue(result.isEmpty());
    }
}
