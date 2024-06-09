package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data transfer object (DTO) for a team.
 *
 * @author Group22
 */
public final class TeamDto {

    private final int teamId;
    private List<CollaboratorDto> teamCollaborator;

    /**
     * Constructs a TeamDto object with the specified team ID and list of collaborators.
     *
     * @param teamId            The ID of the team.
     * @param collaboratorNames The list of collaborators in the team.
     */
    public TeamDto(int teamId, List<Collaborator> collaboratorNames) {
        this.teamId = teamId;
        teamCollaborator = new ArrayList<>();
        for (Collaborator collaborator : collaboratorNames) {
            teamCollaborator.add(new CollaboratorDto(collaborator.getName(), collaborator.getEmail()));
        }
    }

    /**
     * Retrieves the list of collaborators in the team.
     *
     * @return The list of collaborators in the team.
     */
    public List<CollaboratorDto> getTeamCollaborator() {
        return teamCollaborator;
    }

    /**
     * Retrieves the ID of the team.
     *
     * @return The ID of the team.
     */
    public int getTeamId() {
        return teamId;
    }
}
