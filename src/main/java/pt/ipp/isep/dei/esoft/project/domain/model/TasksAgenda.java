package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TasksAgenda {
    private List<TaskAgenda> taskAgendaList;

    public TasksAgenda() {
        this.taskAgendaList = new ArrayList<>();
    }

    public Optional<TaskAgenda> addTaskAgendaToList(TaskToDoList taskToDoList, Date startDate, Date endDate, int expectedDuration) {
        Optional<TaskAgenda> taskAgendaOptional = Optional.empty();

        taskAgendaOptional = Optional.of(new TaskAgenda(taskToDoList, expectedDuration, startDate, endDate));

        if (taskAgendaOptional.isPresent()) {
            this.taskAgendaList.add(taskAgendaOptional.get());
        }

        return taskAgendaOptional;
    }

    public void ver() {
        for (TaskAgenda taskAgenda : this.taskAgendaList) {
            System.out.println(taskAgenda.toString());
        }
    }
}
