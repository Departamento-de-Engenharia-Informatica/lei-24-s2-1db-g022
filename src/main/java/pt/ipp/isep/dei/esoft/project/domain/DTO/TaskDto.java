package pt.ipp.isep.dei.esoft.project.domain.DTO;

/**
 * A Data Transfer Object (DTO) representing a Task entity.
 *
 * @author Group22
 */
public class TaskDto {

    private String taskDescription;

    /**
     * Constructs a TaskDto object with the given task description.
     *
     * @param taskDescription The description of the task.
     */
    public TaskDto(String taskDescription) {

        this.taskDescription = taskDescription;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskDescription() {

        return taskDescription;
    }
}
