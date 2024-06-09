package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.*;

public class TasksAgenda {
    private List<TaskAgenda> taskAgendaList;

    /**
     * Constructs a new TasksAgenda object with an empty list of TaskAgenda instances.
     */
    public TasksAgenda() {
        this.taskAgendaList = new ArrayList<>();
    }

    /**
     * Adds a new TaskAgenda to the list with the provided details.
     *
     * @param taskToDoList     The TaskToDoList associated with the TaskAgenda.
     * @param startDate        The start date of the TaskAgenda.
     * @param expectedDuration The expected duration of the TaskAgenda.
     * @return An Optional containing the newly added TaskAgenda if successfully added, otherwise empty.
     */
    public Optional<TaskAgenda> addTaskAgendaToList(TaskToDoList taskToDoList, Date startDate, int expectedDuration) {
        Optional<TaskAgenda> taskAgendaOptional = Optional.empty();

        taskAgendaOptional = Optional.of(new TaskAgenda(taskToDoList, expectedDuration, startDate));

        if (taskAgendaOptional.isPresent()) {
            this.taskAgendaList.add(taskAgendaOptional.get());
        }

        return taskAgendaOptional;
    }

    /**
     * Retrieves all TaskAgendas associated with the provided list of GreenSpaces.
     *
     * @param greenSpaceList The list of GreenSpaces to filter TaskAgendas.
     * @return A list of TaskAgendas associated with the provided GreenSpaces.
     */
    public List<TaskAgenda> getAllTaskByGreenSpace(List<GreenSpace> greenSpaceList) {
        List<TaskAgenda> listTaskAgenda = new ArrayList<>();

        for (TaskAgenda ts : taskAgendaList) {
            if (ts.verifyGreenSpace(greenSpaceList)) {
                listTaskAgenda.add(ts);
            }
        }
        return listTaskAgenda;
    }

    /**
     * Verifies if any TaskAgenda in the list contains the specified task reference.
     *
     * @param taskReference The task reference to search for.
     * @return An Optional containing the TaskAgenda with the specified reference if found, or an empty Optional otherwise.
     */
    public Optional<TaskAgenda> verifyHasTaskReference(String taskReference) {

        for (TaskAgenda t : taskAgendaList) {
            if (t.verifyHasTaskReference(taskReference)) {
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    /**
     * Assigns a team to a specific task agenda.
     *
     * @param team       The team to be assigned.
     * @param taskAgenda The task agenda to which the team will be assigned.
     * @return True if the team was successfully assigned to the task agenda, false otherwise.
     */
    public boolean assignTeamTask(Team team, TaskAgenda taskAgenda) {
        for (TaskAgenda tA : taskAgendaList) {
            if (tA.equalsTask(taskAgenda)) {
                tA.updateTeam(team);
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a list of TaskAgenda items that belong to the specified Team.
     *
     * @param team the Team for which to retrieve the TaskAgenda items
     * @return a list of TaskAgenda items associated with the specified Team
     */
    public List<TaskAgenda> getTaskListByTeam(Team team) {
        List<TaskAgenda> taskListTeam = new ArrayList<>();
        for (TaskAgenda tA : taskAgendaList) {
            if(tA.hasTaskByTeam(team)){
                taskListTeam.add(tA);
            }
        }
        return taskListTeam;
    }

    /**
     * Filters a list of TaskAgenda items by status and date range.
     *
     * @param taskListTeam the list of TaskAgenda items to filter
     * @param status the status to filter by
     * @param startDate the start date of the range to filter by
     * @param endDate the end date of the range to filter by
     * @return a list of TaskAgenda items that match the specified status and date range
     */
    public List<TaskAgenda> filterTasksByStatusDate(List<TaskAgenda> taskListTeam, String status, Date startDate, Date endDate) {

        List<TaskAgenda> filteredTaskListTeam = new ArrayList<>();
        for (TaskAgenda taskAgenda : taskListTeam) {
            if(taskAgenda.getTaskToDoList().hasStatus(status) && taskAgenda.isBetweenDates(startDate,endDate)){
                filteredTaskListTeam.add(taskAgenda);
            }
        }
        return filteredTaskListTeam;
    }

    /**
     * Sorts a list of TaskAgenda items by their start date using a simple sorting algorithm.
     *
     * @param filteredTaskListTeam the list of TaskAgenda items to sort
     * @return the sorted list of TaskAgenda items
     */
    public List<TaskAgenda> sortTaskAgendaByDate(List<TaskAgenda> filteredTaskListTeam){
        Collections.sort(filteredTaskListTeam);

        return filteredTaskListTeam;
    }
}
