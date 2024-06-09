package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEntryToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskDto;
import pt.ipp.isep.dei.esoft.project.domain.model.Task;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TaskTest class contains unit tests for the Task class.
 * It validates the behavior of the Task class methods.
 * Each test method focuses on a specific aspect or scenario related to the Task class functionality.
 *
 * @author Group22
 */
class TaskTest {

    /**
     * Tests if a task is created successfully with a valid name.
     */
    @Test
    void ensureTaskIsCreatedSuccessfully() {
        Task task = new Task("Pintar chão");
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a task with a null name.
     */
    @Test
    void ensureTaskIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null));
    }

    /**
     * Tests if two references to the same object are considered equal.
     */
    @Test
    void testEqualsSameObject() {

        Task task = new Task("Pintar chão");

        assertEquals(task, task);
    }

    /**
     * Tests if a Task object is not equal to an object of a different class.
     */
    @Test
    void testEqualsDifferentClass() {

        Task task = new Task("Pintar chão");

        assertNotEquals(task, new Object());
    }

    /**
     * Tests if a Task object is not equal to null.
     */
    @Test
    void testEqualsNull() {
        Task task = new Task("Pintar chão");

        assertNotEquals(task, null);
    }

    /**
     * Tests if two different Task objects with different names are not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {
        Task task = new Task("Pintar chão");
        Task task1 = new Task("Podar Árvores");

        assertNotEquals(task, task1);
    }

    /**
     * Tests if two Task objects with the same name are considered equal.
     */
    @Test
    void testEqualsSameObjectSameName() {
        Task task = new Task("Pintar chão");
        Task task1 = new Task("Pintar chão");

        assertEquals(task, task1);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {
        Task task = new Task("Pintar chão");

        assertEquals(task.hashCode(), task.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {
        Task task = new Task("Pintar chão");
        Task task1 = new Task("Podar Árvores");

        assertNotEquals(task.hashCode(), task1.hashCode());
    }

    /**
     * Tests if the clone method creates a new Task object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Task task = new Task("Pintar chão");
        Task clone = task.clone();
        assertEquals(task, clone);
    }
}