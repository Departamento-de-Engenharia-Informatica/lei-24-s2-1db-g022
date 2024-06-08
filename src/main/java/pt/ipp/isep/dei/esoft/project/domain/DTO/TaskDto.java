package pt.ipp.isep.dei.esoft.project.domain.DTO;

import java.util.Objects;

public class TaskDto {

    private String taskDescription;

    public TaskDto(String taskDescription) {

        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {

        return taskDescription;
    }
}
