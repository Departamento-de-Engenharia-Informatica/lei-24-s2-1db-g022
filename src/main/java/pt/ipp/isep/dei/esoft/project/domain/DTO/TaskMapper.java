package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public TaskMapper() {
    }

    public List<TaskDto> toDTO(List<Task> list) {

        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task t : list) {

            taskDtoList.add(toDto(t));
        }

        return taskDtoList;
    }

    public TaskDto toDto(Task obj) {

        return new TaskDto(obj.getTaskDescription());
    }

}
