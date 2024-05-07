package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The JobTest class contains unit tests for the Job class.
 * It validates the behavior of the Job class methods.
 * Each test method focuses on a specific aspect or scenario related to the Job class functionality.
 *
 * @author Group22
 */
class JobTest {

    /**
     * Tests if a job is created successfully with a valid name.
     */
    @Test
    void ensureJobIsCreatedSuccessfully() {
        Job job = new Job("Empreteiro");
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a job with a null name.
     */
    @Test
    void ensureJobIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new Job(null));
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a job with a name containing numbers.
     */
    @Test
    void testJobHasNumbers() {

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Job("Empreteiro2"));
    }

    /**
     * Tests if two references to the same object are considered equal.
     */
    @Test
    void testEqualsSameObject() {

        Job job = new Job("Empreteiro");

        assertEquals(job, job);
    }

    /**
     * Tests if a Job object is not equal to an object of a different class.
     */
    @Test
    void testEqualsDifferentClass() {

        Job job = new Job("Empreteiro");

        assertNotEquals(job, new Object());
    }

    /**
     * Tests if a Job object is not equal to null.
     */
    @Test
    void testEqualsNull() {
        Job job = new Job("Empreteiro");

        assertNotEquals(job, null);
    }

    /**
     * Tests if two different Job objects with different names are not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {
        Job job = new Job("Empreteiro");
        Job job1 = new Job("Condutor");

        assertNotEquals(job, job1);
    }

    /**
     * Tests if two Job objects with the same name are considered equal.
     */
    @Test
    void testEqualsSameObjectSameName() {
        Job job = new Job("Empreteiro");
        Job job1 = new Job("Empreteiro");

        assertEquals(job, job1);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {
        Job job = new Job("Empreteiro");

        assertEquals(job.hashCode(), job.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {
        Job job = new Job("Empreteiro");
        Job job1 = new Job("Condutor");

        assertNotEquals(job.hashCode(), job1.hashCode());
    }

    /**
     * Tests if the clone method creates a new Job object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Job job = new Job("Empreteiro");
        Job clone = job.clone();
        assertEquals(job, clone);
    }
}