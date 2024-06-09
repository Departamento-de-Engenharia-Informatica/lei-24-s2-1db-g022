package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Represents an agenda containing tasks and their associated details.
 *
 * @author Group22
 */
public class Agenda {
    private TasksAgenda tasksAgenda;

    /**
     * Constructs an instance of Agenda with an empty TasksAgenda.
     */
    public Agenda() {
        this.tasksAgenda = new TasksAgenda();
    }

    /**
     * Adds a new entry to the agenda.
     *
     * @param taskToDoList     The task to be added.
     * @param startDate        The start date of the task.
     * @param expectedDuration The expected duration of the task.
     * @return An Optional containing the newly added TaskAgenda, or empty if addition fails.
     */
    public Optional<TaskAgenda> addNewEntryToAgenda(TaskToDoList taskToDoList, Date startDate, int expectedDuration) {
        return tasksAgenda.addTaskAgendaToList(taskToDoList, startDate, expectedDuration);
    }

    /**
     * Retrieves all tasks associated with the specified list of green spaces.
     *
     * @param greenSpaceList The list of green spaces for which tasks will be retrieved.
     * @return A list of task agendas associated with the provided green spaces.
     */
    public List<TaskAgenda> getAllTaskByGreenSpace(List<GreenSpace> greenSpaceList) {
        return tasksAgenda.getAllTaskByGreenSpace(greenSpaceList);
    }

    /**
     * Retrieves a task agenda by its reference.
     *
     * @param taskReference The reference of the task agenda to retrieve.
     * @return An Optional containing the task agenda if found, or empty if not found.
     */
    public Optional<TaskAgenda> getTaskByReference(String taskReference) {
        return tasksAgenda.verifyHasTaskReference(taskReference);
    }

    /**
     * Assigns a task agenda to a team.
     *
     * @param team       The team to which the task agenda will be assigned.
     * @param taskAgenda The task agenda to be assigned.
     * @return {@code true} if the task agenda was successfully assigned to the team, {@code false} otherwise.
     */
    public boolean assignTeamTask(Team team, TaskAgenda taskAgenda) {
        return tasksAgenda.assignTeamTask(team, taskAgenda);
    }

    /**
     * Retrieves the list of task agendas assigned to a specific team.
     *
     * @param team the team for which to retrieve the task agendas.
     * @return a list of task agendas assigned to the specified team.
     */
    public List<TaskAgenda> getTaskListByTeam(Team team) {
        return tasksAgenda.getTaskListByTeam(team);
    }

    /**
     * Filters the list of task agendas by status and date range.
     *
     * @param taskListTeam the list of task agendas to be filtered.
     * @param status       the status to filter the tasks by.
     * @param startDate    the start date of the date range to filter the tasks.
     * @param endDate      the end date of the date range to filter the tasks.
     * @return a list of task agendas that match the specified status and fall within the specified date range.
     */
    public List<TaskAgenda> filterTasksByStatusDate(List<TaskAgenda> taskListTeam, String status, Date startDate, Date endDate) {
        return tasksAgenda.filterTasksByStatusDate(taskListTeam, status, startDate, endDate);
    }

    /**
     * Sorts the list of task agendas by date.
     *
     * @param filteredTaskListTeam the list of task agendas to be sorted.
     * @return a list of task agendas sorted by date.
     */
    public List<TaskAgenda> sortTaskAgendaDate(List<TaskAgenda> filteredTaskListTeam) {
        return tasksAgenda.sortTaskAgendaByDate(filteredTaskListTeam);
    }
}
