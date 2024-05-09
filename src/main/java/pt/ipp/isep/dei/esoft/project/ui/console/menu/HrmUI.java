package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterJobUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterSkillUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The HrmUI class represents the user interface for Human Resources Managers.
 * It provides functionality for displaying and interacting with HRM menu options.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class HrmUI implements Runnable {

    /**
     * Constructs an HrmUI object.
     */
    public HrmUI() {
    }

    /**
     * Runs the Human Resources Manager user interface.
     * Displays menu options and handles user input.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("US01 - Register Skill.", new RegisterSkillUI()));
        options.add(new MenuItem("US02 - Register Job.", new RegisterJobUI()));
        options.add(new MenuItem("US03 - Register Collaborator.", new RegisterCollaboratorUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Human Resources Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
