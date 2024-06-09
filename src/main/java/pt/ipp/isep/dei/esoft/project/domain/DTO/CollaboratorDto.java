package pt.ipp.isep.dei.esoft.project.domain.DTO;

/**
 * Represents a data transfer object (DTO) for a collaborator.
 *
 * @author Group22
 */
public final class CollaboratorDto {
    private final String collaboratorEmailAndName;

    /**
     * Constructs a CollaboratorDto object with the specified collaborator names and email.
     *
     * @param collaboratorNames The names of the collaborator.
     * @param collaboratorEmail The email of the collaborator.
     */
    public CollaboratorDto(String collaboratorNames, String collaboratorEmail) {
        collaboratorEmailAndName = collaboratorNames + " - " + collaboratorEmail;
    }

    /**
     * Retrieves the email and name of the collaborator.
     *
     * @return The email and name of the collaborator.
     */
    public String getCollaboratorEmailAndName() {
        return collaboratorEmailAndName;
    }
}

