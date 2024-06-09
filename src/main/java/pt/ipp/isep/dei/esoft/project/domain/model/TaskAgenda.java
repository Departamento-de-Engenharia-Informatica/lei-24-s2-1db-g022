package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Date;

public class TaskAgenda {
    private TaskToDoList taskToDoList;
    private Date startDate;
    private Date endDate;
    private int expectDuration;

    public TaskAgenda(TaskToDoList taskToDoList, int expectDuration, Date startDate, Date endDate) {
        this.taskToDoList = taskToDoList;
        taskToDoList.setStatusToPlanned();
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectDuration = expectDuration;
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
}
