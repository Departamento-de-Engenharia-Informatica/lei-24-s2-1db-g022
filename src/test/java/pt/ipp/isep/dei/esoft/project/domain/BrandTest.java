package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Brand;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The BrandTest class contains unit tests for the Brand class.
 * It validates the behavior of the Brand class methods.
 * Each test method focuses on a specific aspect or scenario related to the Brand class functionality.
 *
 * @author Group22
 */
class BrandTest {

    /**
     * Tests if a brand is created successfully with a valid name.
     */
    @Test
    void ensureBrandIsCreatedSuccessfully() {
        Brand brand = new Brand("BMW");
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a brand with a null name.
     */
    @Test
    void ensureBrandIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new Brand(null));
    }

    /**
     * Tests if two references to the same object are considered equal.
     */
    @Test
    void testEqualsSameObject() {

        Brand brand = new Brand("BMW");

        assertEquals(brand, brand);
    }

    /**
     * Tests if a Brand object is not equal to an object of a different class.
     */
    @Test
    void testEqualsDifferentClass() {

        Brand brand = new Brand("BMW");

        assertNotEquals(brand, new Object());
    }

    /**
     * Tests if a Brand object is not equal to null.
     */
    @Test
    void testEqualsNull() {
        Brand brand = new Brand("BMW");

        assertNotEquals(brand, null);
    }

    /**
     * Tests if two different Brand objects with different names are not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {
        Brand brand = new Brand("BMW");
        Brand brand1 = new Brand("Opel");

        assertNotEquals(brand, brand1);
    }

    /**
     * Tests if two Brand objects with the same name are considered equal.
     */
    @Test
    void testEqualsSameObjectSameName() {
        Brand brand = new Brand("BMW");
        Brand brand1 = new Brand("BMW");

        assertEquals(brand, brand1);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {
        Brand brand = new Brand("BMW");

        assertEquals(brand.hashCode(), brand.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {
        Brand brand = new Brand("BMW");
        Brand brand1 = new Brand("Opel");

        assertNotEquals(brand.hashCode(), brand1.hashCode());
    }

    /**
     * Tests if the clone method creates a new Brand object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Brand brand = new Brand("BMW");
        Brand clone = brand.clone();
        assertEquals(brand, clone);
    }
}