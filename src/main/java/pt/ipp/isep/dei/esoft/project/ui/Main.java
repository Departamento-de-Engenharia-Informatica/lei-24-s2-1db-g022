package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

/**
 * The Main class serves as the entry point for the project's user interface.
 * It initializes the application by running the bootstrap process and then displays the main menu.
 * The main menu provides options for navigating through different functionalities of the application.
 * The class contains a main method that orchestrates the initialization and execution of the application.
 *
 * @author Group22
 */
public class Main {

    /**
     * The main method is the entry point of the application.
     * It initializes the bootstrap process and then displays the main menu.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Run the bootstrap process to initialize the application
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        try {
            // Create and run the main menu user interface
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
