package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.model.Skill;
import pt.ipp.isep.dei.esoft.project.domain.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the user interface for generating teams.
 * It interacts with the user through the console and utilizes a controller
 * to handle the business logic.
 */
public class GenerateTeamUI implements Runnable {
    private final GenerateTeamController controller;
    private int minSizeTeam;
    private int maxSizeTeam;
    private int skillNumberSize;
    List<String> skillListInput;
    private List<Skill> skillListOutput;
    private List<Team> teamListProposal;

    private Team selectTeam;

    /**
     * Constructor for the GenerateTeamUI class.
     * Initializes the controller for generating teams.
     */
    public GenerateTeamUI() {
        controller = new GenerateTeamController();
    }

    /**
     * Getter method for retrieving the GenerateTeamController instance.
     *
     * @return The GenerateTeamController instance.
     */
    private GenerateTeamController getController() {
        return controller;
    }


    /**
     * This method represents the main logic of the GenerateTeamUI class.
     * It collects necessary data, interacts with the user, and delegates tasks to other methods accordingly.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Generate Team ------------------------");
        requestDataLoop();
        getSkillList();

        skillListInput = new ArrayList<>();

        while (skillNumberSize > 0) {
            skillListInput.add(displayAndSelectSkillList());
            --skillNumberSize;
        }
        displayAllDataInfo();
        if (confirmationInfoTeam()) {
            if (!submitDataProposal()) {
                //Display Teams Proposal
                selectTeam = displayAndSelectTeamProposals();
                submitDataTeamSelect();
            } else {
                System.out.println("It was not possible to generate proposals for teams with the requested requirements.");
            }
        }
    }

    /**
     * This method submits the selected team data for registration.
     */
    private void submitDataTeamSelect() {
        boolean success = getController().registerProposalTeam(selectTeam);

        if (success) {
            System.out.println("\nTeam successfully created!");
        } else {
            System.out.println("\nTeam not created!");
        }
    }

    /**
     * This method displays the skill options and allows the user to select a skill.
     *
     * @return The name of the selected skill.
     */
    private String displayAndSelectSkillList() {
        int listSize = skillListOutput.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displaySkillOptions();
            System.out.println("Select a Skill: ");
            answer = input.nextInt();
        }

        return skillListOutput.get(answer - 1).getName();
    }

    /**
     * This method displays the available skill options.
     */
    private void displaySkillOptions() {
        int i = 1;

        for (Skill skill : skillListOutput) {
            System.out.println("  " + i + " - " + skill.getName());
            i++;
        }
    }

    /**
     * This method displays the team proposals and allows the user to select a team.
     *
     * @return The selected team.
     */
    private Team displayAndSelectTeamProposals() {
        int listSize = teamListProposal.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayProposalTeamOptions();
            System.out.println("Select a Team Proposal: ");
            answer = input.nextInt();
        }

        return teamListProposal.get(answer - 1);
    }

    /**
     * This method displays the options for team proposals.
     */
    private void displayProposalTeamOptions() {
        int i = 0;
        System.out.println("Size: " + teamListProposal.size());
        for (Team team : teamListProposal) {
            ++i;
            System.out.println("[" + i + "] Team...................");
            for (Collaborator collaborator : team.getCollaboratorList()) {
                System.out.println("Collaborator Name: " + collaborator.getName());
            }
        }
    }

    /**
     * This method submits the generated team data for proposal.
     *
     * @return true if there are no teams generated, false otherwise.
     */
    private boolean submitDataProposal() {
        teamListProposal = getController().generateProposalTeams(minSizeTeam, maxSizeTeam, skillListInput);

        return teamListProposal.isEmpty();
    }


    /**
     * This method prompts the user to confirm the entered data.
     *
     * @return true if the user confirms the data, false otherwise.
     */
    private boolean confirmationInfoTeam() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    /**
     * This method displays all the collected data information.
     */
    private void displayAllDataInfo() {
        System.out.println("\n\n--- Display Info .........................");

        displayInfoLoop();
        displayInputSkills();
    }

    /**
     * This method displays the input skills.
     */
    private void displayInputSkills() {
        System.out.println("\n\n--- Skills .........................");
        int i = 0;
        for (String skillName : skillListInput) {
            ++i;
            System.out.printf("\n[%d] %s", i, skillName);
        }
    }

    /**
     * This method displays the team information loop.
     */
    private void displayInfoLoop() {
        System.out.println("\n\n--- Team Info .........................");
        System.out.printf("\n Min Team Size: %d", minSizeTeam);
        System.out.printf("\n Max Team Size: %d", maxSizeTeam);
    }

    /**
     * This method retrieves the list of skills from the controller.
     */
    private void getSkillList() {
        skillListOutput = controller.getSkillList();
    }

    /**
     * This method initiates the request data loop to collect necessary information.
     */
    private void requestDataLoop() {
        minSizeTeam = requestMinTeamSize();
        maxSizeTeam = requestMaxTeamSize();
        skillNumberSize = requestSkillNumber();
    }


    /**
     * This method prompts the user to enter the number of skills required for team generation.
     *
     * @return The number of skills entered by the user.
     */
    private int requestSkillNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Skill Numbers: ");
        return input.nextInt();
    }

    /**
     * This method prompts the user to enter the minimum size of the team.
     *
     * @return The minimum size of the team entered by the user.
     */
    private int requestMinTeamSize() {
        Scanner input = new Scanner(System.in);
        System.out.println("Min Size Team: ");
        return input.nextInt();
    }

    /**
     * This method prompts the user to enter the maximum size of the team.
     *
     * @return The maximum size of the team entered by the user.
     */
    private int requestMaxTeamSize() {
        Scanner input = new Scanner(System.in);
        System.out.println("Max Size Team: ");
        return input.nextInt();
    }


}
