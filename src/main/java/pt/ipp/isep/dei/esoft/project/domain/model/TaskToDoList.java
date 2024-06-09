package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a task to be completed in a specific green space.
 *
 * @author Group22
 */
public class TaskToDoList {

    private GreenSpace greenSpace;
    private Task task;
    private String urgency;
    private int aproxExpectedDuration;
    private String status;
    private String taskRef;

    /**
     * Constructs a new TaskToDoList object with the given parameters.
     *
     * @param greenSpace            The green space associated with the task.
     * @param task                  The task to be completed.
     * @param urgency               The urgency level of the task.
     * @param aproxExpectedDuration The approximate expected duration of the task in hours.
     * @param taskRef               The reference identifier of the task.
     * @throws IllegalArgumentException if the approximate expected duration is zero or negative.
     */
    public TaskToDoList(GreenSpace greenSpace, Task task, String urgency, int aproxExpectedDuration, String taskRef) {
        this.greenSpace = greenSpace;
        this.task = task;
        this.urgency = urgency;

        if (validatePositiveInt(aproxExpectedDuration)) {

            this.aproxExpectedDuration = aproxExpectedDuration;
        } else {

            throw new IllegalArgumentException("Approximate Expected Duration cannot be zero or negative numbers.");
        }

        this.status = "Pending";
        this.taskRef = taskRef;
    }

    /**
     * Gets the reference of this TaskToDoList.
     *
     * @return The reference of this TaskToDoList.
     */
    public String getTaskRef() {

        return taskRef;
    }

    /**
     * Gets the green space associated with this TaskToDoList.
     *
     * @return The green space associated with this TaskToDoList.
     */
    public GreenSpace getGreenSpace() {

        return greenSpace;
    }

    /**
     * Gets the task associated with this TaskToDoList.
     *
     * @return The task associated with this TaskToDoList.
     */
    public Task getTask() {

        return task;
    }

    /**
     * Checks if this TaskToDoList has the specified reference.
     *
     * @param ref the reference to check
     * @return true if this TaskToDoList's reference matches the given reference, false otherwise
     */
    public boolean hasRef(String ref) {

        return this.taskRef.equals(ref);
    }

    /**
     * Indicates whether some other object is "equal to" this one. Two TaskToDoList objects are considered equal if they
     * have the same green space, task, urgency, approximate expected duration, status, and task reference.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof TaskToDoList)) return false;
        TaskToDoList that = (TaskToDoList) o;

        return aproxExpectedDuration == that.aproxExpectedDuration && Objects.equals(greenSpace, that.greenSpace) && Objects.equals(task, that.task) && Objects.equals(urgency, that.urgency) && Objects.equals(status, that.status) && Objects.equals(taskRef, that.taskRef);
    }

    /**
     * Returns a hash code value for the TaskToDoList object. The hash code is computed based on the hash codes of its
     * green space, task, urgency, approximate expected duration, status, and task reference.
     *
     * @return A hash code value for the TaskToDoList object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(greenSpace, task, urgency, aproxExpectedDuration, status, taskRef);
    }

    /**
     * Returns a string representation of the TaskToDoList object. The string contains information about the green space,
     * task, urgency, approximate expected duration, status, and task reference.
     *
     * @return A string representation of the TaskToDoList object.
     */
    @Override
    public String toString() {

        return "TaskToDoList{" +
                "greenSpace=" + greenSpace +
                ", task=" + task +
                ", urgency='" + urgency + '\'' +
                ", aproxExpectedDuration=" + aproxExpectedDuration +
                ", status='" + status + '\'' +
                ", taskRef='" + taskRef + '\'' +
                '}';
    }

    /**
     * Clones the taskToDoList.
     *
     * @return A clone of the current taskToDoList instance.
     */
    public TaskToDoList clone() {

        return new TaskToDoList(this.greenSpace, this.task, this.urgency, this.aproxExpectedDuration, this.taskRef);
    }


    /**
     * Validates if the given integer value is positive.
     *
     * @param value The integer value to validate.
     * @return true if the value is positive, false otherwise.
     */
    private boolean validatePositiveInt(int value) {

        return value > 0;
    }

    /**
     * Sets the status of the TaskToDoList to "Planned".
     * <p>
     * This method updates the status field of the TaskToDoList object to "Planned".
     */
    public void setStatusToPlanned() {

        this.status = "Planned";
    }

    /**
     * Checks if the provided GreenSpace object is equal to the GreenSpace of this TaskToDoList
     * and if the status of this TaskToDoList is "Planned".
     *
     * @param greenSpace The GreenSpace object to compare.
     * @return True if the GreenSpace is equal and the status is "Planned", false otherwise.
     */
    public boolean equalsGreenSpace(GreenSpace greenSpace) {

        return this.greenSpace.equals(greenSpace) && hasStatus("Planned");
    }

    /**
     * Checks if this TaskToDoList has the specified status.
     *
     * @param status The status to check against.
     * @return True if this TaskToDoList has the specified status, false otherwise.
     */
    public boolean hasStatus(String status) {

        return this.status.equals(status);
    }
}
