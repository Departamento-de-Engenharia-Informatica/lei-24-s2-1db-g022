package pt.ipp.isep.dei.esoft.project.domain.DTO;

import java.util.Date;

/**
 * Represents a data transfer object (DTO) for a task agenda.
 *
 * @author Group22
 */
public final class TaskAgendaDto {

    private final String taskReference;
    private final String description;
    private final String greenSpaceName;
    private final Date startDate;

    /**
     * Constructs a TaskAgendaDto object with the specified task reference, description, and green space name.
     *
     * @param taskReference  The reference of the task.
     * @param description    The description of the task.
     * @param greenSpaceName The name of the green space associated with the task.
     */
    public TaskAgendaDto(String taskReference, String description, String greenSpaceName) {
        this.taskReference = taskReference;
        this.description = description;
        this.greenSpaceName = greenSpaceName;
        this.startDate = null;
    }
    /**
     * Constructs a TaskAgendaDto object with the specified task reference, description, and green space name.
     *
     * @param taskReference  The reference of the task.
     * @param description    The description of the task.
     * @param greenSpaceName The name of the green space associated with the task.
     * @param startDate the date of the task
     */
    public TaskAgendaDto(String taskReference, String description, String greenSpaceName, Date startDate) {
        this.taskReference = taskReference;
        this.description = description;
        this.greenSpaceName = greenSpaceName;
        this.startDate = startDate;
    }

    /**
     * Retrieves the reference of the task.
     *
     * @return The reference of the task.
     */
    public String getTaskToDoListReference() {
        return taskReference;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskToDoListDescription() {
        return description;
    }

    /**
     * Retrieves the name of the green space associated with the task.
     *
     * @return The name of the green space associated with the task.
     */
    public String getTaskToDoListGreenSpaceName() {
        return greenSpaceName;
    }

    /**
     * Returns the start date of the task to-do list.
     *
     * @return the start date of the task to-do list
     */
    public Date getTaskToDoListStartDate() {
        return startDate;
    }

}
