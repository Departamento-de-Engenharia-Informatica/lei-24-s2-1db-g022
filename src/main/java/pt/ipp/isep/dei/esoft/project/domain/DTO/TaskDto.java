package pt.ipp.isep.dei.esoft.project.domain.DTO;

import java.util.Objects;

/**
 * A Data Transfer Object (DTO) representing a Task entity.
 *
 * @author Group22
 */
public final class TaskDto {

    private final String taskDescription;

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

    /**
     * Checks if this TaskDto is equal to another object.
     *
     * @param o the object to compare this TaskDto against
     * @return true if the given object represents a TaskDto equivalent to this instance, false otherwise
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof TaskDto)) return false;
        TaskDto taskDto = (TaskDto) o;

        return Objects.equals(taskDescription, taskDto.taskDescription);
    }

    /**
     * Returns a hash code value for this TaskDto.
     *
     * @return a hash code value for this TaskDto
     */
    @Override
    public int hashCode() {

        return Objects.hashCode(taskDescription);
    }
}
