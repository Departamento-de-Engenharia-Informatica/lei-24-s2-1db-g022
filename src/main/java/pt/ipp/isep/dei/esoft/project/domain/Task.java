package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Task {

    private String taskDescription;

    public Task(String taskDescription) {

        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {

        return taskDescription;
    }

    public boolean hasDescription(String taskDescription){

        return this.taskDescription.equals(taskDescription);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;

        return Objects.equals(taskDescription, task.taskDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(taskDescription);
    }

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
