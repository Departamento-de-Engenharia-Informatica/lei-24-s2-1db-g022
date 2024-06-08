package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.TestAlgorithmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The QamUI class represents the user interface for Software Quality Assessment Team Managers.
 * It provides functionality for displaying and interacting with QamUI menu options.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class ColUI implements Runnable {

    /**
     * Constructs a QamUI object.
     */
    public ColUI() {
    }

    /**
     * Runs the Software Quality Assessment Team Manager user interface.
     * Displays menu options and handles user input.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Collaborator MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
