package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Model;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The ModelTest class contains unit tests for the Model class.
 * It validates the behavior of the Model class methods.
 * Each test method focuses on a specific aspect or scenario related to the Model class functionality.
 *
 * @author Group22
 */
class ModelTest {

    /**
     * Tests if a model is created successfully with a valid name.
     */
    @Test
    void ensureModelIsCreatedSuccessfully() {
        Model model = new Model("XM");
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a model with a null name.
     */
    @Test
    void ensureModelIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new Model(null));
    }

    /**
     * Tests if two references to the same object are considered equal.
     */
    @Test
    void testEqualsSameObject() {

        Model model = new Model("XM");

        assertEquals(model, model);
    }

    /**
     * Tests if a Model object is not equal to an object of a different class.
     */
    @Test
    void testEqualsDifferentClass() {

        Model model = new Model("XM");

        assertNotEquals(model, new Object());
    }

    /**
     * Tests if a Model object is not equal to null.
     */
    @Test
    void testEqualsNull() {
        Model model = new Model("XM");

        assertNotEquals(model, null);
    }

    /**
     * Tests if two different Model objects with different names are not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {
        Model model = new Model("XM");
        Model model1 = new Model("C4");

        assertNotEquals(model, model1);
    }

    /**
     * Tests if two Model objects with the same name are considered equal.
     */
    @Test
    void testEqualsSameObjectSameName() {
        Model model = new Model("XM");
        Model model1 = new Model("XM");

        assertEquals(model, model1);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {
        Model model = new Model("XM");

        assertEquals(model.hashCode(), model.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {
        Model model = new Model("XM");
        Model model1 = new Model("C4");

        assertNotEquals(model.hashCode(), model1.hashCode());
    }

    /**
     * Tests if the clone method creates a new Model object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Model model = new Model("XM");
        Model clone = model.clone();
        assertEquals(model, clone);
    }
}