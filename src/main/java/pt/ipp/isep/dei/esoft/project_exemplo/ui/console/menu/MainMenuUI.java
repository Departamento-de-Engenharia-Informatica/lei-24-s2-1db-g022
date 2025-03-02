package pt.ipp.isep.dei.esoft.project_exemplo.ui.console.menu;

import pt.ipp.isep.dei.esoft.project_exemplo.ui.console.DevTeamUI;
import pt.ipp.isep.dei.esoft.project_exemplo.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project_exemplo.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class MainMenuUI implements Runnable {

    public MainMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- MAIN MENU --------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}