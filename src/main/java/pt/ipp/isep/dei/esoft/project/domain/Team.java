package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

/**
 * The Team class represents a team of collaborators.
 * It contains a list of collaborators who are part of the team.
 *
 * @author Group22
 */
public class Team {

    private final List<Collaborator> collaboratorList;

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

    /**
     * Checks if the provided collaborator is in the list of collaborators.
     *
     * @param collaborator The collaborator to check.
     * @return True if the collaborator is in the list, otherwise false.
     */
    public boolean collaboratorHasTeam(Collaborator collaborator) {
        return (collaboratorList.contains(collaborator));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(collaboratorList, team.collaboratorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collaboratorList);
    }

    public Team clone() {

        Team clone = new Team(this.collaboratorList);

        return clone;
    }
}
