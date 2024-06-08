package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

public class TaskToDoList {

    private GreenSpace greenSpace;
    private Task task;
    private String urgency;
    private int aproxExpectedDuration;
    private String status;
    private String taskRef;

    public TaskToDoList(GreenSpace greenSpace, Task task, String urgency, int aproxExpectedDuration, String taskRef) {

        this.greenSpace = greenSpace;
        this.task = task;
        this.urgency = urgency;

        if (validateNullInt(aproxExpectedDuration)) {

            this.aproxExpectedDuration = aproxExpectedDuration;
        } else {

            throw new IllegalArgumentException("Approximate Expected Duration cannot be zero or negative numbers.");
        }
        this.status = "Pending";
        this.taskRef = taskRef;
    }

    private boolean validateNullInt(int value) {

        return !(value <= 0);
    }

    public boolean hasRef(String ref) {

        return this.taskRef.equals(ref);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskToDoList)) return false;
        TaskToDoList that = (TaskToDoList) o;
        return aproxExpectedDuration == that.aproxExpectedDuration && Objects.equals(greenSpace, that.greenSpace) && Objects.equals(task, that.task) && Objects.equals(urgency, that.urgency) && Objects.equals(status, that.status) && Objects.equals(taskRef, that.taskRef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(greenSpace, task, urgency, aproxExpectedDuration, status, taskRef);
    }

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
}
