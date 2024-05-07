package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Optional;
import java.util.Scanner;

/**
 * The RegisterSkillUI class represents the user interface for registering a skill.
 * It prompts the user to input a skill name and submits the data to create the skill.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class RegisterSkillUI implements Runnable {

    private final RegisterSkillController controller;
    private String skillName;

    /**
     * Constructs a RegisterSkillUI object with a new RegisterSkillController.
     */
    public RegisterSkillUI() {
        controller = new RegisterSkillController();
    }

    /**
     * Gets the RegisterSkillController associated with this UI.
     * @return The RegisterSkillController instance.
     */
    private RegisterSkillController getController() {
        return controller;
    }

    /**
     * Executes the user interface, prompting the user to input a skill name and submitting the data.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Register Skill ------------------------");

        boolean register = false;

        while (!register){
            requestData();

            register = submitData();
        }
    }

    /**
     * Submits the skill data to create the skill and displays the result.
     *
     * @return True if the skill is successfully created, false otherwise.
     */
    private boolean submitData() {

        boolean success = true;

        Optional<Skill> skill = getController().registerSkill(skillName);

        if (skill.isPresent()) {
            System.out.println("\nSkill successfully created!");
            return success;
        } else {
            System.out.println("\nSkill not created!");
            return !success;
        }
    }

    /**
     * Requests the skill name from the user.
     */
    private void requestData() {
        skillName = requestSkillName();
    }

    /**
     * Requests a skill name input from the user.
     * @return The skill name entered by the user.
     */
    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Skill Name: ");
        return input.nextLine();
    }
}



