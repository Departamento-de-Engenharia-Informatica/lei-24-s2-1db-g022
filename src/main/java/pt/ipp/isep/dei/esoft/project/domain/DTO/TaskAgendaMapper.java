package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.TaskAgenda;
import pt.ipp.isep.dei.esoft.project.domain.model.TaskToDoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps TaskAgenda objects to TaskAgendaDto objects.
 *
 * @author Group22
 */
public class TaskAgendaMapper {

    /**
     * Constructs a TaskAgendaMapper object.
     */
    public TaskAgendaMapper() {
    }

    /**
     * Converts a list of TaskAgenda objects to a list of TaskAgendaDto objects.
     *
     * @param list The list of TaskAgenda objects to be converted.
     * @return A list of TaskAgendaDto objects.
     */
    public List<TaskAgendaDto> toDTOTaskToDoList(List<TaskAgenda> list) {
        List<TaskAgendaDto> taskAgendaList = new ArrayList<>();

        for (TaskAgenda t : list) {
            taskAgendaList.add(toDto(t.getTaskToDoList()));
        }

        return taskAgendaList;
    }

    /**
     * Converts a TaskToDoList object to a TaskAgendaDto object.
     *
     * @param obj The TaskToDoList object to be converted.
     * @return A TaskAgendaDto object.
     */
    private TaskAgendaDto toDto(TaskToDoList obj) {
        return new TaskAgendaDto(obj.getTaskRef(), obj.getTask().getTaskDescription(), obj.getGreenSpace().getName());
    }
}
