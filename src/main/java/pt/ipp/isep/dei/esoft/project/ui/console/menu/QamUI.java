package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.TestAlgorithmUI;
import pt.ipp.isep.dei.esoft.project.ui.console.MinSpanTreeUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterVehicleUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
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
public class QamUI implements Runnable {

    /**
     * Constructs a QamUI object.
     */
    public QamUI() {
    }

    /**
     * Runs the Software Quality Assessment Team Manager user interface.
     * Displays menu options and handles user input.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("US14 - Test Algorithm.", new TestAlgorithmUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Software Quality Assessment Team Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
