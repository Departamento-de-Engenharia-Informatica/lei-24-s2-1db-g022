package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Date;
import java.util.List;

public class TaskAgenda {
    private TaskToDoList taskToDoList;
    private Date startDate;
    private Date endDate;
    private int expectDuration;
    private Team team;

    public TaskAgenda(TaskToDoList taskToDoList, int expectDuration, Date startDate, Date endDate) {
        this.taskToDoList = taskToDoList;
        taskToDoList.setStatusToPlanned();
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectDuration = expectDuration;
        this.team = null;
    }

    @Override
    public String toString() {
        return "TaskAgenda{" +
                "taskToDoList=" + taskToDoList +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", expectDuration=" + expectDuration +
                '}';
    }

    public TaskToDoList getTaskToDoList() {
        return taskToDoList;
    }

    public boolean verifyGreenSpace(List<GreenSpace> greenSpaceList) {
        if (verifyNullTeam()) {
            System.out.println("daodsas");
            for (GreenSpace greenSpace : greenSpaceList) {
                if (taskToDoList.equalsGreenSpace(greenSpace)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean verifyNullTeam() {
        return team == null;
    }

    public boolean verifyHasTaskReference(String taskReference) {

        return taskToDoList.hasRef(taskReference);
    }

    public boolean equalsTask(TaskAgenda taskAgenda){
        if (this == taskAgenda) return true;

        return taskToDoList == taskAgenda.taskToDoList && startDate == taskAgenda.startDate && endDate == taskAgenda.endDate && expectDuration == taskAgenda.expectDuration && team == taskAgenda.team;
    }

    public boolean updateTeam(Team team) {
        this.team = team;
        return true;
    }
}
