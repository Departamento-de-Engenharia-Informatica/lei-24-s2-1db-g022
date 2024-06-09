package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.TaskToDoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps TaskToDoList objects to TaskToDoListDto objects.
 *
 * @author Group22
 *
 */
public class TaskToDoListMapper {

    /**
     * Constructs a TaskToDoListMapper object.
     */
    public TaskToDoListMapper() {
    }

    /**
     * Converts a list of TaskToDoList objects to a list of TaskToDoListDto objects.
     *
     * @param list The list of TaskToDoList objects to be converted.
     * @return A list of TaskToDoListDto objects.
     */
    public List<TaskToDoListDto> toDTOTaskToDoList(List<TaskToDoList> list) {
        List<TaskToDoListDto> taskToDoListDtoList = new ArrayList<>();

        for (TaskToDoList t : list) {
            taskToDoListDtoList.add(toDto(t));
        }

        return taskToDoListDtoList;
    }

    /**
     * Converts a TaskToDoList object to a TaskToDoListDto object.
     *
     * @param obj The TaskToDoList object to be converted.
     * @return A TaskToDoListDto object.
     */
    private TaskToDoListDto toDto(TaskToDoList obj) {
        return new TaskToDoListDto(obj.getTaskRef(), obj.getTask().getTaskDescription(), obj.getGreenSpace().getName());
    }
}
