package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.RegisterCheckUpUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterVehicleUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The VfmUI class represents the user interface for Vehicle and Equipment Fleet Managers.
 * It provides functionality for displaying and interacting with VFM menu options.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class VfmUI implements Runnable {

    /**
     * Constructs a VfmUI object.
     */
    public VfmUI() {
    }

    /**
     * Runs the Vehicle and Equipment Fleet Manager user interface.
     * Displays menu options and handles user input.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("US06 - Register Vehicle.", new RegisterVehicleUI()));
        options.add(new MenuItem("US07 - Register Check Up Vehicle.", new RegisterCheckUpUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Vehicle and Equipment Fleet Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
