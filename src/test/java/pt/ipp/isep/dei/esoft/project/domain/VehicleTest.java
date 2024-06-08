package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Brand;
import pt.ipp.isep.dei.esoft.project.domain.model.Model;
import pt.ipp.isep.dei.esoft.project.domain.model.Vehicle;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The VehicleTest class contains unit tests for the Vehicle class.
 * It validates the behavior of the Vehicle class methods.
 * Each test method focuses on a specific aspect or scenario related to the Vehicle class functionality.
 *
 * @author Group22
 */
class VehicleTest {

    /**
     * Tests if a vehicle is created successfully with valid atributes.
     */
    @Test
    void ensureVehicleIsCreatedSuccessfully() {

        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a vehicle with a null name.
     */
    @Test
    void ensureVehicleIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle(null, 0, 0, 0, null, null, 0, null, null, null));
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a vehicle with a license plate using the wrong format.
     */
    @Test
    void testVehicleLicensePlate() {

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"AA-00-AA",new Brand("BMW"), new Model("XM")));
    }

    /**
     * Tests if two references to the same object are considered equal.
     */
    @Test
    void testEqualsSameObject() {

        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));

        assertEquals(vehicle, vehicle);
    }

    /**
     * Tests if a Vehicle object is not equal to an object of a different class.
     */
    @Test
    void testEqualsDifferentClass() {

        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));

        assertNotEquals(vehicle, new Object());
    }

    /**
     * Tests if a Vehicle object is not equal to null.
     */
    @Test
    void testEqualsNull() {
        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));

        assertNotEquals(vehicle, null);
    }

    /**
     * Tests if two different Vehicle objects with different atributes are not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {
        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));
        Vehicle vehicle1 = new Vehicle("Big Truck",10000,7500,150000, Date.valueOf("2008-5-5"),Date.valueOf("2022-5-5"),100000,"00-AX-10",new Brand("Opel"), new Model("Astra"));

        assertNotEquals(vehicle, vehicle1);
    }

    /**
     * Tests if two different Vehicle objects with different license plates are not considered equal.
     */
    @Test
    void testEqualsDifferentLicensePlate() {
        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));
        Vehicle vehicle1 = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AX",new Brand("BMW"), new Model("XM"));

        assertNotEquals(vehicle, vehicle1);
    }

    /**
     * Tests if two Vehicle objects with the same atributes are considered equal.
     */
    @Test
    void testEqualsSameObjectSameAtributes() {
        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));
        Vehicle vehicle1 = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));

        assertEquals(vehicle, vehicle1);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {
        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));

        assertEquals(vehicle.hashCode(), vehicle.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {
        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));
        Vehicle vehicle1 = new Vehicle("Big Truck",10000,7500,150000, Date.valueOf("2008-5-5"),Date.valueOf("2022-5-5"),100000,"00-AX-10",new Brand("Opel"), new Model("Astra"));

        assertNotEquals(vehicle.hashCode(), vehicle1.hashCode());
    }

    /**
     * Tests if the clone method creates a new Vehicle object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Vehicle vehicle = new Vehicle("SUV",1000,750,1500000, Date.valueOf("1999-5-5"),Date.valueOf("2024-5-5"),100000,"00-00-AA",new Brand("BMW"), new Model("XM"));
        Vehicle clone = vehicle.clone();
        assertEquals(vehicle, clone);
    }
}