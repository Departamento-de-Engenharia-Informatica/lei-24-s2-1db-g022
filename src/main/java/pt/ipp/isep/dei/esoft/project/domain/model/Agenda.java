package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Agenda {
    private TasksAgenda tasksAgenda;

    public Agenda() {
        this.tasksAgenda = new TasksAgenda();
    }

    public Optional<TaskAgenda> addNewEntryToAgenda(TaskToDoList taskToDoList, Date startDate, Date endDate, int expectedDuration) {
        Optional<TaskAgenda> taskAgendaOptional = Optional.empty();

        taskAgendaOptional = tasksAgenda.addTaskAgendaToList(taskToDoList, startDate, endDate, expectedDuration);

        return taskAgendaOptional;
    }

    public void ver() {
        tasksAgenda.ver();
    }

    public List<TaskAgenda> getAllTaskByGreenSpace(List<GreenSpace> greenSpaceList) {
        return tasksAgenda.getAllTaskByGreenSpace(greenSpaceList);

    }

    public Optional<TaskAgenda> getTaskByReference(String taskReference) {
        
        return tasksAgenda.verifyHasTaskReference(taskReference);
    }

    public boolean assignTeamTask(Team team, TaskAgenda taskAgenda) {
        return tasksAgenda.assignTeamTask(team, taskAgenda);
    }
}
