package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEntryToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskDto;
import pt.ipp.isep.dei.esoft.project.domain.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contains unit tests for the TaskRepository class.
 * Validates the functionality of the createTask method.
 *
 * @author Group22
 */
public class TaskRepositoryTest {

    /**
     * Tests if the createTask method successfully creates a task.
     */
    @Test
    void TestCreateTask() {
        TaskRepository taskRepository = new TaskRepository();

        Optional<Task> result = taskRepository.createTask("Podar Árvores");

        assertTrue(result.isPresent());
    }

    /**
     * Tests if creating a task with a duplicate name fails.
     */
    @Test
    void ensureCreateTaskDuplicateFails() {
        TaskRepository taskRepository = new TaskRepository();

        taskRepository.createTask("Podar Árvores");

        Optional<Task> result = taskRepository.createTask("Podar Árvores");

        assertTrue(result.isEmpty());
    }

    /**
     * Tests the functionality of retrieving all tasks from the task repository.
     */
    @Test
    void getAllTasksTest(){
        TaskDto tDto = new TaskDto("Pintar chão");
        TaskDto tDto1 = new TaskDto("Podar Árvores");

        List<TaskDto> expected = new ArrayList<>();

        expected.add(tDto);
        expected.add(tDto1);

        TaskRepository tR = Repositories.getInstance().getTaskRepository();
        RegisterEntryToDoListController registerEntry = new RegisterEntryToDoListController();

        tR.createTask("Pintar chão");
        tR.createTask("Podar Árvores");

        List<TaskDto> result = new ArrayList<>();

        result = registerEntry.getAllTasks();

        assertEquals(expected, result);
    }
}
