package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Date;
import java.util.List;

public class TaskAgenda implements Comparable<TaskAgenda> {
    private TaskToDoList taskToDoList;
    private Date startDate;
    private int expectDuration;
    private Team team;

    /**
     * Constructs a task agenda with the specified parameters.
     *
     * @param taskToDoList   The task to be scheduled.
     * @param expectDuration The expected duration of the task.
     * @param startDate      The start date of the task.
     */
    public TaskAgenda(TaskToDoList taskToDoList, int expectDuration, Date startDate) {
        this.taskToDoList = taskToDoList;
        taskToDoList.setStatusToPlanned();
        this.startDate = startDate;
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

    public Date getStartDate() {
        return startDate;
    }

    /**
     * Verifies if the task is associated with any of the provided green spaces.
     *
     * @param greenSpaceList The list of green spaces to check against.
     * @return True if the task is associated with any of the provided green spaces; false otherwise.
     */
    public boolean verifyGreenSpace(List<GreenSpace> greenSpaceList) {
        if (verifyNullTeam()) {
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

        return taskToDoList == taskAgenda.taskToDoList && startDate == taskAgenda.startDate && expectDuration == taskAgenda.expectDuration && team == taskAgenda.team;
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

    /**
     * Checks if this TaskAgenda has the specified Team.
     *
     * @param team the Team to check against
     * @return true if this TaskAgenda has the specified Team, false otherwise
     */
    public boolean hasTaskByTeam(Team team) {
        return this.team.equals(team);
    }

    /**
     * Checks if a given start date is before the end date.
     *
     * @param startDate The start date to be checked.
     * @param endDate   The end date to be checked.
     * @return True if the start date is before the end date; otherwise, false. Returns false if either the start date or the end date is null.
     */
    public boolean isBetweenDates(Date startDate, Date endDate) {
        return startDate != null && endDate != null && this.startDate.after(startDate) && this.startDate.before(endDate);
    }

    /**
     * Compares this TaskAgenda with another TaskAgenda for ordering based on their start dates.
     *
     * @param other the TaskAgenda to be compared
     * @return a negative integer, zero, or a positive integer as this TaskAgenda is before, at the same time, or after the specified TaskAgenda.
     */
    public int compareTo(TaskAgenda other) {
        return this.startDate.compareTo(other.startDate);
    }
}
