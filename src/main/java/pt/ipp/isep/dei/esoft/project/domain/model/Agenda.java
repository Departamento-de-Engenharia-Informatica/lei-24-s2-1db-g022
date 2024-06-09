package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Date;
import java.util.Optional;

public class Agenda {
    private TasksAgenda tasksAgenda;

    public Agenda() {
        this.tasksAgenda = new TasksAgenda();
    }

    public Optional<TaskAgenda> addNewEntryToAgenda(TaskToDoList taskToDoList, Date startDate, Date endDate, int expectedDuration) {
        Optional<TaskAgenda> taskAgendaOptional = Optional.empty();

        taskAgendaOptional = tasksAgenda.addTaskAgendaToList(taskToDoList,startDate,endDate,expectedDuration);

        return taskAgendaOptional;
    }

    public void ver(){
        tasksAgenda.ver();
    }
}
