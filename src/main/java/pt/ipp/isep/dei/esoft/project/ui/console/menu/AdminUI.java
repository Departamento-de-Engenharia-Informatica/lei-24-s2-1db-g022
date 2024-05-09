package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The AdminUI class represents the user interface for administrators.
 * It provides functionality for displaying and interacting with administrative menu options.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class AdminUI implements Runnable {

    /**
     * Constructs an AdminUI object.
     */
    public AdminUI() {
    }

    /**
     * Runs the administrative user interface.
     * Displays menu options and handles user input.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("US01 - Register Skill.", new RegisterSkillUI()));
        options.add(new MenuItem("US02 - Register Job.", new RegisterJobUI()));
        options.add(new MenuItem("US03 - Register Collaborator.", new RegisterCollaboratorUI()));
        options.add(new MenuItem("US06 - Register Vehicle.", new RegisterVehicleUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
