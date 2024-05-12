package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing Team objects.
 */
public class TeamRepository {
    private final List<Team> teamList;

    /**
     * Constructor for the TeamRepository class.
     * Initializes the list of teams.
     */
    public TeamRepository() {
        teamList = new ArrayList<>();
    }

    /**
     * Generates proposal teams based on specified criteria.
     *
     * @param minSize          The minimum size required for a team.
     * @param maxSizeTeam      The maximum size allowed for a team.
     * @param skillList        The list of skills required for the team.
     * @param collaboratorList The list of available collaborators.
     * @return The list of proposal teams generated.
     */
    public List<Team> generateProposalTeam(int minSize, int maxSizeTeam, List<Skill> skillList, List<Collaborator> collaboratorList) {
        List<Team> teamListProposal = new ArrayList<>();
        removeCollaboratorsHasTeam(collaboratorList);
        while (collaboratorList.size() >= minSize) {
            List<Collaborator> idealCollaboratorList = new ArrayList<>();
            int i = 0;

            // Populate idealCollaboratorList
            while (i < collaboratorList.size() && idealCollaboratorList.size() < maxSizeTeam) {
                Collaborator collaborator = collaboratorList.get(i);
                addIdealCollaboratorList(idealCollaboratorList, collaborator);
                i++;
            }

            // Create team proposal if idealCollaboratorList meets the criteria
            if (idealCollaboratorList.size() >= minSize) {
                Team teamProposal = new Team(idealCollaboratorList);
                addListTeamProposal(teamListProposal, teamProposal);

                // Remove collaborators from collaboratorList
                removeCollaboratorList(collaboratorList, idealCollaboratorList);
            }
        }

        return teamListProposal;
    }

    /**
     * Removes collaborators from the provided list if they are associated with any team in the team list.
     *
     * @param collaboratorList The list of collaborators to check and remove from.
     */
    private void removeCollaboratorsHasTeam(List<Collaborator> collaboratorList) {
        for (Team team : teamList) {
            collaboratorList.removeIf(team::collaboratorHasTeam);
        }
    }

    /**
     * Removes a list of collaborators from the given collaborator list.
     *
     * @param collaboratorList The list of collaborators to remove from.
     * @param team             The list of collaborators to remove.
     */
    private void removeCollaboratorList(List<Collaborator> collaboratorList, List<Collaborator> team) {
        collaboratorList.removeAll(team);
    }

    /**
     * Adds a team to the list of team proposals.
     *
     * @param teamListProposal The list of team proposals.
     * @param team             The team to add to the list of proposals.
     */
    private void addListTeamProposal(List<Team> teamListProposal, Team team) {
        teamListProposal.add(team);
    }

    /**
     * Adds a collaborator to the list of ideal collaborators.
     *
     * @param idealCollaboratorList The list of ideal collaborators.
     * @param collaborator          The collaborator to add to the list of ideal collaborators.
     */
    private void addIdealCollaboratorList(List<Collaborator> idealCollaboratorList, Collaborator collaborator) {
        idealCollaboratorList.add(collaborator);
    }

    /**
     * Registers a proposal team.
     *
     * @param selectTeam The team to be registered.
     * @return True if the team is successfully registered, false otherwise.
     */
    public boolean registerProposalTeam(Team selectTeam) {
        return addTeam(selectTeam);
    }

    private boolean addTeam(Team team) {
        return teamList.add(team);
    }
}
