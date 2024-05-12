package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the controller for generating teams.
 * It manages interactions between the UI and the repositories to generate and register teams.
 */
public class GenerateTeamController {
    private TeamRepository teamRepository;
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;

    /**
     * Constructor for the GenerateTeamController class.
     * Initializes the skill, collaborator, and team repositories.
     */
    public GenerateTeamController() {
        getSkillRepository();
        getCollaboratorRepository();
        getTeamRepository();
    }

    /**
     * Retrieves the list of skills from the skill repository.
     *
     * @return The list of skills.
     */
    public List<Skill> getSkillList() {
        SkillRepository skillRepository = getSkillRepository();
        return skillRepository.getSkillList();
    }

    /**
     * Retrieves the team repository instance.
     * If the repository is not initialized, it initializes it first.
     *
     * @return The team repository instance.
     */
    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    /**
     * Retrieves the skill repository instance.
     * If the repository is not initialized, it initializes it first.
     *
     * @return The skill repository instance.
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Retrieves the collaborator repository instance.
     * If the repository is not initialized, it initializes it first.
     *
     * @return The collaborator repository instance.
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Generates proposal teams based on the provided parameters.
     *
     * @param minSizeTeam The minimum size of the team.
     * @param maxSizeTeam The maximum size of the team.
     * @param skillList   The list of skill names required for team generation.
     * @return A list of proposed teams.
     */
    public List<Team> generateProposalTeams(int minSizeTeam, int maxSizeTeam, List<String> skillList) {
        List<Collaborator> collaboratorsList = new ArrayList<>();
        List<Team> teamListProposal = new ArrayList<>();
        List<Skill> newSkillList = getAllSkillsByName(skillList);

        collaboratorsList = getCollaboratorsBySkill(newSkillList);

        if (!collaboratorsList.isEmpty()) {
            // Generate Proposal Teams
            teamListProposal = generateProposalTeamList(minSizeTeam, maxSizeTeam, newSkillList, collaboratorsList);
        }
        return teamListProposal;
    }

    /**
     * Generates a list of proposal teams based on the provided parameters.
     *
     * @param minSize          The minimum size of the team.
     * @param maxSize          The maximum size of the team.
     * @param skillList        The list of skills required for team generation.
     * @param collaboratorList The list of collaborators with the required skills.
     * @return A list of proposed teams.
     */
    private List<Team> generateProposalTeamList(int minSize, int maxSize, List<Skill> skillList, List<Collaborator> collaboratorList) {
        List<Team> teamListProposal = new ArrayList<>();
        teamListProposal = getTeamRepository().generateProposalTeam(minSize, maxSize, skillList, collaboratorList);

        return teamListProposal;
    }

    /**
     * Retrieves collaborators with the provided skills.
     *
     * @param skillList The list of skills.
     * @return A list of collaborators with the provided skills.
     */
    private List<Collaborator> getCollaboratorsBySkill(List<Skill> skillList) {
        return getCollaboratorRepository().getCollaboratorsBySkills(skillList);
    }

    /**
     * Retrieves skills by their names.
     *
     * @param skillListNames The list of skill names.
     * @return A list of skills.
     */
    private List<Skill> getAllSkillsByName(List<String> skillListNames) {
        List<Skill> skillList = new ArrayList<>();

        for (String skillName : skillListNames) {
            skillList.add(getSkillRepository().getSkillByName(skillName));
        }
        return skillList;
    }

    /**
     * Registers a proposed team.
     *
     * @param selectTeam The team to be registered.
     * @return true if the team is successfully registered, false otherwise.
     */
    public boolean registerProposalTeam(Team selectTeam) {
        return getTeamRepository().registerProposalTeam(selectTeam);
    }
}
