package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Date;
import java.util.List;

public class TaskAgenda {
    private TaskToDoList taskToDoList;
    private Date startDate;
    private Date endDate;
    private int expectDuration;
    private Team team;

    /**
     * Constructs a task agenda with the specified parameters.
     *
     * @param taskToDoList   The task to be scheduled.
     * @param expectDuration The expected duration of the task.
     * @param startDate      The start date of the task.
     * @param endDate        The end date of the task.
     */
    public TaskAgenda(TaskToDoList taskToDoList, int expectDuration, Date startDate, Date endDate) {
        this.taskToDoList = taskToDoList;
        taskToDoList.setStatusToPlanned();
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectDuration = expectDuration;
        this.team = null;
    }

    /**
     * Returns a string representation of the TaskAgenda object.
     *
     * @return a string representation of the TaskAgenda object.
     */
    @Override
    public String toString() {
        return "TaskAgenda{" +
                "taskToDoList=" + taskToDoList +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", expectDuration=" + expectDuration +
                '}';
    }

    /**
     * Retrieves the task associated with this agenda.
     *
     * @return The task associated with this agenda.
     */
    public TaskToDoList getTaskToDoList() {
        return taskToDoList;
    }

    /**
     * Verifies if the task is associated with any of the provided green spaces.
     *
     * @param greenSpaceList The list of green spaces to check against.
     * @return True if the task is associated with any of the provided green spaces; false otherwise.
     */
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

    /**
     * Checks if the team variable is null.
     *
     * @return True if the team variable is null, false otherwise.
     */
    private boolean verifyNullTeam() {
        return team == null;
    }

    /**
     * Verifies if the task agenda contains a task with the given reference.
     *
     * @param taskReference The reference of the task to check.
     * @return True if the task agenda contains a task with the given reference; false otherwise.
     */
    public boolean verifyHasTaskReference(String taskReference) {

        return taskToDoList.hasRef(taskReference);
    }

    /**
     * Checks if this TaskAgenda is equal to another TaskAgenda.
     *
     * @param taskAgenda The TaskAgenda to compare with.
     * @return True if the TaskAgendas are equal, false otherwise.
     */
    public boolean equalsTask(TaskAgenda taskAgenda) {
        if (this == taskAgenda) return true;

        return taskToDoList == taskAgenda.taskToDoList && startDate == taskAgenda.startDate && endDate == taskAgenda.endDate && expectDuration == taskAgenda.expectDuration && team == taskAgenda.team;
    }

    /**
     * Updates the team assigned to this task agenda.
     *
     * @param team The new team assigned to the task agenda.
     * @return True if the team was successfully updated; false otherwise.
     */
    public boolean updateTeam(Team team) {
        this.team = team;
        return true;
    }
}
