package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The ToDoListTest class contains unit tests for the ToDoList class.
 * It validates the behavior of the ToDoList class methods.
 * Each test method focuses on a specific aspect or scenario related to the ToDoList class functionality.
 *
 * @author Group22
 */
class ToDoListTest {

    /**
     * Tests if the registerEntryToDoList method successfully creates an entry in the ToDoList.
     */
    @Test
    void TestRegisterEntryToDoList() {

        ToDoList toDoList = new ToDoList();

        GreenSpace garden = new Garden("Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        Task task = new Task("Pintar chão");

        Optional<TaskToDoList> result = toDoList.registerEntryToDoList(garden, task, "low", 15);

        assertTrue(result.isPresent());
    }

    /**
     * Tests the generation of unique references for tasks added to the ToDoList.
     */
    @Test
    void TestGenerateUniqueReference() {

        ToDoList toDoList = new ToDoList();

        List<String> expected = new ArrayList<>();

        expected.add("REF1");
        expected.add("REF2");

        List<String> result = new ArrayList<>();

        GreenSpace garden = new Garden("Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        Task task = new Task("Pintar chão");
        Task task1 = new Task("Pintar paredes");

        Optional<TaskToDoList> entry = toDoList.registerEntryToDoList(garden, task, "low", 15);
        Optional<TaskToDoList> entry1 = toDoList.registerEntryToDoList(garden, task1, "low", 15);


        result.add(entry.get().getTaskRef());
        result.add(entry1.get().getTaskRef());

        assertEquals(expected, result);
    }

    /**
     * Tests retrieving tasks by GreenSpace from the ToDoList.
     */
    @Test
    void TestGetTaskByGreenSpace() {

        ToDoList toDoList = new ToDoList();

        GreenSpace garden = new Garden("Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        Task task = new Task("Pintar chão");

        TaskToDoList entry = toDoList.registerEntryToDoList(garden, task, "low", 15).get();

        List<TaskToDoList> expected = new ArrayList<>();

        expected.add(entry);

        List<TaskToDoList> result = toDoList.getTaskByGreenSpace(garden);

        assertEquals(expected, result);
    }

    /**
     * Tests retrieving a TaskToDoList by its reference from the ToDoList.
     */
    @Test
    void TestGetTaskToDoListByReference() {

        ToDoList toDoList = new ToDoList();

        GreenSpace garden = new Garden("Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        Task task = new Task("Pintar chão");

        TaskToDoList expected = toDoList.registerEntryToDoList(garden, task, "low", 15).get();


        TaskToDoList result = toDoList.getTaskToDoListByReference("REF1").get();

        assertEquals(expected, result);
    }
}