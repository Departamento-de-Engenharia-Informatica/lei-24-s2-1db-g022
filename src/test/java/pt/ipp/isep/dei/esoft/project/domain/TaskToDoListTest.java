package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEntryToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.model.Garden;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.model.Task;
import pt.ipp.isep.dei.esoft.project.domain.model.TaskToDoList;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TaskToDoListTest class contains unit tests for the TaskToDoList class.
 * It validates the behavior of the TaskToDoList class methods.
 * Each test method focuses on a specific aspect or scenario related to the TaskToDoList class functionality.
 *
 * @author Group22
 */
class TaskToDoListTest {

    /**
     * Tests if a ttdl is created successfully with a valid name.
     */
    @Test
    void ensureTaskToDoListIsCreatedSuccessfully() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");

        TaskToDoList ttdl = new TaskToDoList(garden,task,"low",15,"REF1");
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a ttdl with a null name.
     */
    @Test
    void ensureTaskToDoListIsNotNull() {

        assertThrows(IllegalArgumentException.class, () -> new TaskToDoList(null,null,null,0,null));
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a ttdl with an aprox duration equals  to zero or below.
     */
    @Test
    void testTaskToDoListHasNegativeOrZeroDuration() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new TaskToDoList(garden,task,"low",0,"REF1"));
    }

    /**
     * Tests if two references to the same object are considered equal.
     */
    @Test
    void testEqualsSameObject() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");

        TaskToDoList ttdl = new TaskToDoList(garden,task,"low",15,"REF1");

        assertEquals(ttdl, ttdl);
    }

    /**
     * Tests if a TaskToDoList object is not equal to an object of a different class.
     */
    @Test
    void testEqualsDifferentClass() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");

        TaskToDoList ttdl = new TaskToDoList(garden,task,"low",15,"REF1");

        assertNotEquals(ttdl, new Object());
    }

    /**
     * Tests if a TaskToDoList object is not equal to null.
     */
    @Test
    void testEqualsNull() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");

        TaskToDoList ttdl = new TaskToDoList(garden,task,"low",15,"REF1");

        assertNotEquals(ttdl, null);
    }

    /**
     * Tests if two different TaskToDoList objects with different names are not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");
        Task task1 = new Task("Pintar Paredes");

        TaskToDoList ttdl = new TaskToDoList(garden,task,"low",15,"REF1");
        TaskToDoList ttdl1 = new TaskToDoList(garden,task1,"low",15,"REF2");


        assertNotEquals(ttdl, ttdl1);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");

        TaskToDoList ttdl = new TaskToDoList(garden,task,"low",15,"REF1");

        assertEquals(ttdl.hashCode(), ttdl.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");
        Task task1 = new Task("Pintar Paredes");

        TaskToDoList ttdl = new TaskToDoList(garden,task,"low",15,"REF1");
        TaskToDoList ttdl1 = new TaskToDoList(garden,task1,"low",15,"REF2");

        assertNotEquals(ttdl.hashCode(), ttdl1.hashCode());
    }

    /**
     * Tests if the clone method creates a new TaskToDoList object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        Task task = new Task("Pintar chão");

        TaskToDoList ttdl = new TaskToDoList(garden,task,"low",15,"REF1");

        TaskToDoList clone = ttdl.clone();

        assertEquals(ttdl, clone);
    }

    @Test
    void testNewEntryTaskToDoList(){

        GreenSpaceRepository gR = Repositories.getInstance().getGreenSpaceRepository();
        TaskRepository tR = Repositories.getInstance().getTaskRepository();

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        gR.addGreenSpaceBootstrap(garden);

        Task task = new Task("Pintar chão");
        tR.createTask("Pintar chão");

        TaskToDoList expected = new TaskToDoList(garden,task,"low",15,"REF1");

        RegisterEntryToDoListController register = new RegisterEntryToDoListController();

        Optional<TaskToDoList> taskTdl =register.registerEntryToDoList("Jardim Porto","Pintar chão","low",15);
        TaskToDoList atual = taskTdl.get();

        assertEquals(expected, atual);
    }
}