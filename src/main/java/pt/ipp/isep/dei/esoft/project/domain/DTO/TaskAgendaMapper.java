package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.TaskAgenda;
import pt.ipp.isep.dei.esoft.project.domain.model.TaskToDoList;

import java.util.ArrayList;
import java.util.List;

public class TaskAgendaMapper {

    public TaskAgendaMapper() {
    }

    public List<TaskAgendaDto> toDTOTaskToDoList(List<TaskAgenda> list) {

        List<TaskAgendaDto> taskAgendaList = new ArrayList<>();

        for (TaskAgenda t : list) {

            taskAgendaList.add(toDto(t.getTaskToDoList()));
        }

        return taskAgendaList;
    }

    private TaskAgendaDto toDto(TaskToDoList obj) {

        return new TaskAgendaDto(obj.getTaskRef(), obj.getTask().getTaskDescription(), obj.getGreenSpace().getName());
    }

}
