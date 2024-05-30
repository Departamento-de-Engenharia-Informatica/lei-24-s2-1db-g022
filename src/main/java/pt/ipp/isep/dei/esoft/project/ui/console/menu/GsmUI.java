package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.MinRouteToApUI;
import pt.ipp.isep.dei.esoft.project.ui.console.MinSpanTreeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The GsmUI class represents the user interface for Green Spaces Managers.
 * It provides functionality for displaying and interacting with GSM menu options.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class GsmUI implements Runnable {

    /**
     * Constructs a GsmUI object.
     */
    public GsmUI() {
    }

    /**
     * Runs the Green Spaces Manager user interface.
     * Displays menu options and handles user input.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("US13 - Apply Algorithm.", new MinSpanTreeUI()));
        options.add(new MenuItem("US17 - Place sign to assembly point.", new MinRouteToApUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Green Spaces Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
