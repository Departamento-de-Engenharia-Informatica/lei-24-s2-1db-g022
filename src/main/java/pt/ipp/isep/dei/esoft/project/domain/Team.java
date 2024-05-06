package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

/**
 * @author Group22
 */
public class Team {
    private List<Collaborator> collaboratorList;

    public Team(List<Collaborator> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }

    public List<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    @Override
    public String toString() {
        return "Team{" +
                "collaboratorList=" + collaboratorList +
                '}';
    }
}
