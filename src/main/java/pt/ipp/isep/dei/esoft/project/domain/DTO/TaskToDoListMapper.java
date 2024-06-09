package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.TaskToDoList;

import java.util.ArrayList;
import java.util.List;

public class TaskToDoListMapper {

    public TaskToDoListMapper() {
    }

    public List<TaskToDoListDto> toDTOTaskToDoList(List<TaskToDoList> list) {

        List<TaskToDoListDto> taskToDoListDtoList = new ArrayList<>();

        for (TaskToDoList t : list) {

            taskToDoListDtoList.add(toDto(t));
        }

        return taskToDoListDtoList;
    }

    public TaskToDoListDto toDto(TaskToDoList obj) {

        return new TaskToDoListDto(obj.getTaskRef(), obj.getTask().getTaskDescription(), obj.getGreenSpace().getName());
    }

}
