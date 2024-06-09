package pt.ipp.isep.dei.esoft.project.domain.DTO;

public final class CollaboratorDto {

    private final String collaboratorEmailAndName;

    public CollaboratorDto(String collaboratorNames, String collaboratorEmail) {

        collaboratorEmailAndName = collaboratorNames + " - " + collaboratorEmail;
    }

    public String getCollaboratorEmailAndName() {
        return collaboratorEmailAndName;
    }


}
