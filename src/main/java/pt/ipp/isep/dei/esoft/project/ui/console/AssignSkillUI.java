package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;
import java.util.Scanner;

public class AssignSkillUI implements Runnable {

    private final AssignSkillController controller = new AssignSkillController();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("Welcome to the Skill Assignment Interface");
        System.out.println("========================================");

        while (true) {
            System.out.println("List of available skills:");
            List<Skill> skills = controller.getAllSkills();
            if (skills.isEmpty()) {
                System.out.println("No skills available to assign.");
                return;
            }
            for (int i = 0; i < skills.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, skills.get(i).getName());
            }

            System.out.print("Enter the name of the collaborator to assign skills to (or type 'exit' to quit): ");
            String collaboratorName = scanner.nextLine();
            if ("exit".equalsIgnoreCase(collaboratorName)) {
                break;
            }

            System.out.print("Enter the skill name to assign (or type 'exit' to quit): ");
            String skillName = scanner.nextLine();
            if ("exit".equalsIgnoreCase(skillName)) {
                break;
            }

            boolean result = controller.assignSkillToCollaborator(collaboratorName, skillName);
            if (result) {
                System.out.println("Skill successfully assigned!");
            } else {
                System.out.println("Failed to assign skill. Please check the names and try again.");
            }

            System.out.println();
        }
        System.out.println("Exiting Skill Assignment Interface.");
    }
}