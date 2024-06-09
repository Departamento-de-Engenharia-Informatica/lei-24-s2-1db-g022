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

    public List<TaskAgenda> getAllTaskByGreenSpace(List<GreenSpace> greenSpaceList) {
        List<TaskAgenda> listTaskAgenda = new ArrayList<>();

        for (TaskAgenda ts : taskAgendaList) {
            if (ts.verifyGreenSpace(greenSpaceList)) {
                listTaskAgenda.add(ts);
            }
        }
        return listTaskAgenda;
    }

    public Optional<TaskAgenda> verifyHasTaskReference(String taskReference) {

        for (TaskAgenda t : taskAgendaList) {
            if (t.verifyHasTaskReference(taskReference)) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    public boolean assignTeamTask(Team team, TaskAgenda taskAgenda){
        for (TaskAgenda tA : taskAgendaList){
            if (tA.equalsTask(taskAgenda)){
                tA.updateTeam(team);
                return true;
            }
        }
        return false;
    }
}
