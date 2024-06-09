package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.model.Skill;

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
     *
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

        while (!register) {
            requestData();
            displayData();

            if (confirmationData()) {
                register = submitData();
            }
        }
    }

    /**
     * Prompts the user to confirm whether the entered data is correct or not.
     * Displays a message asking the user to input 'Y' or 'N' for confirmation.
     * Reads the user's input from the console and returns true if 'Y' or 'y' is entered,
     * indicating that the data is confirmed, and false otherwise.
     *
     * @return True if the user confirms the entered data, false otherwise.
     */
    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    /**
     * Displays the collected information about the Skill entered by the user.
     * Prints out the skill name.
     */
    private void displayData() {
        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\nSkill Name: %s", skillName);
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
     *
     * @return The skill name entered by the user.
     */
    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Skill Name: ");
        return input.nextLine();
    }
}



