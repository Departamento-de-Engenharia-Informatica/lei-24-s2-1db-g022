package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a task.
 *
 * @author Group22
 */
public class Task {

    private String taskDescription;

    /**
     * Constructs a new Task object with the given task description.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {

        if (validateNullString(taskDescription)) {

            this.taskDescription = taskDescription;
        } else {

            throw new IllegalArgumentException("Task description cannot be null or empty.");
        }
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
     * Validates if the provided string is not null or empty.
     *
     * @param value The string to validate.
     * @return True if the string is not null or empty, false otherwise.
     */
    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    /**
     * Checks if the task has the given description.
     *
     * @param taskDescription The description to check against the task's description.
     * @return true if the task has the given description, false otherwise.
     */
    public boolean hasDescription(String taskDescription) {

        return this.taskDescription.equals(taskDescription);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;

        return Objects.equals(taskDescription, task.taskDescription);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(taskDescription);
    }

    /**
     * Returns a string representation of the object. In general, the toString method returns a string that "textually represents" this object.
     * The result should be a concise but informative representation that is easy for a person to read.
     *
     * @return A string representation of the Task object.
     */
    @Override
    public String toString() {

        return "Task{" +
                "taskDescription='" + taskDescription + '\'' +
                '}';
    }

    /**
     * Clones the task.
     *
     * @return A clone of the current task instance.
     */
    public Task clone() {

        return new Task(this.taskDescription);
    }
}
