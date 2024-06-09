package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Task;

import java.util.Optional;

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
}
