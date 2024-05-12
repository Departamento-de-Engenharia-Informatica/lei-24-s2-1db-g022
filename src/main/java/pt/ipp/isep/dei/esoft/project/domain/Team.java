package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

/**
 * The Team class represents a team of collaborators.
 * It contains a list of collaborators who are part of the team.
 *
 * @author Group22
 */
public class Team {

    private List<Collaborator> collaboratorList;

    /**
     * Constructs a Team object with the specified list of collaborators.
     *
     * @param collaboratorList The list of collaborators who are part of the team.
     */
    public Team(List<Collaborator> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }


    /**
     * Gets the list of collaborators in the team.
     *
     * @return The list of collaborators in the team.
     */
    public List<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    /**
     * Returns a string representation of the team.
     *
     * @return A string representation of the team.
     */
    @Override
    public String toString() {
        return "Team{" +
                "collaboratorList=" + collaboratorList +
                '}';
    }
}
