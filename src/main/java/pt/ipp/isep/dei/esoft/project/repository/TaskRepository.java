package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing tasks.
 *
 * @author Group22
 */
public class TaskRepository {
    private final List<Task> taskList;

    /**
     * Constructs a new TaskRepository object.
     */
    public TaskRepository() {

        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the repository.
     *
     * @param task The task to add.
     * @return True if the task is successfully added, false otherwise.
     */
    private boolean addTask(Task task) {

        boolean success = false;

        if (validateTask(task)) {

            success = taskList.add(task.clone());
        }

        return success;
    }

    /**
     * Creates a new task with the specified name and adds it to the repository.
     *
     * @param taskDescription The name of the task.
     * @return An optional containing the created task if successful, empty optional otherwise.
     */
    public Optional<Task> createTask(String taskDescription) {

        Optional<Task> optionalTask = Optional.empty();
        Task task = new Task(taskDescription);

        if (addTask(task)) {

            optionalTask = Optional.of(task);
        }

        return optionalTask;
    }

    /**
     * Validates if the task is not already present in the repository.
     *
     * @param task The task to validate.
     * @return True if the task is valid (not already present), false otherwise.
     */
    private boolean validateTask(Task task) {

        return !taskList.contains(task);
    }

    /**
     * Retrieves an unmodifiable list of all tasks.
     *
     * @return An unmodifiable list of all tasks.
     */
    public List<Task> getAllTaskDescriptions() {

        return List.copyOf(taskList);
    }

    /**
     * Retrieves a task by its description from the repository.
     *
     * @param taskDescription The description of the task to retrieve.
     * @return An Optional containing the task if found, otherwise an empty Optional.
     */
    public Optional<Task> getTaskByDescription(String taskDescription) {

        for (Task t : taskList) {

            if (t.hasDescription(taskDescription)) {

                return Optional.of(t);
            }
        }

        return Optional.empty();
    }
}
