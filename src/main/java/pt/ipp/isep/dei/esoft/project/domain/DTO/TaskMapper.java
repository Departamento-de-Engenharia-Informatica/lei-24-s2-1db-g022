package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class responsible for converting Task entities to TaskDto objects.
 *
 * @author Group22
 */
public class TaskMapper {

    /**
     * Default constructor for TaskMapper.
     */
    public TaskMapper() {
    }

    /**
     * Converts a list of Task entities to a list of TaskDto objects.
     *
     * @param list The list of Task entities to be converted.
     * @return A list of TaskDto objects.
     */
    public List<TaskDto> toDTO(List<Task> list) {

        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task t : list) {

            taskDtoList.add(toDto(t));
        }

        return taskDtoList;
    }

    /**
     * Converts a single Task entity to a TaskDto object.
     *
     * @param obj The Task entity to be converted.
     * @return A TaskDto object.
     */
    private TaskDto toDto(Task obj) {

        return new TaskDto(obj.getTaskDescription());
    }

}
