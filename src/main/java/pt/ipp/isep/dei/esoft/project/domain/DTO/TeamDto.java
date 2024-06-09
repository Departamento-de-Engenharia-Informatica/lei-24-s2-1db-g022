package pt.ipp.isep.dei.esoft.project.domain.DTO;

import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;

import java.util.ArrayList;
import java.util.List;

public final class TeamDto {

    private final int teamId;
    private final List<CollaboratorDto> teamCollaborator;

    public TeamDto(int teamId, List<Collaborator> collaboratorNames) {

        this.teamId = teamId;
        teamCollaborator = new ArrayList<>();
        for (Collaborator collaborator : collaboratorNames) {
            teamCollaborator.add(new CollaboratorDto(collaborator.getName(), collaborator.getEmail()));
        }

    }

    public List<CollaboratorDto> getTeamCollaborator() {
        return teamCollaborator;
    }

    public int getTeamId() {
        return teamId;
    }
}
