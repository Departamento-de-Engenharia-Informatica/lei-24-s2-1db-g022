package pt.ipp.isep.dei.esoft.project.domain.DTO;

public final class TaskAgendaDto {

    private final String taskReference;
    private final String description;
    private final String greenSpaceName;

    public TaskAgendaDto(String taskReference, String description, String greenSpaceName) {
        this.taskReference = taskReference;
        this.description = description;
        this.greenSpaceName = greenSpaceName;
    }

    public String getTaskToDoListReference() {
        return taskReference;
    }

    public String getTaskToDoListDescription() {
        return description;
    }

    public String getTaskToDoListGreenSpaceName() {
        return greenSpaceName;
    }
}
