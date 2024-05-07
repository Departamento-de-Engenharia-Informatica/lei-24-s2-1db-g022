package pt.ipp.isep.dei.esoft.project_exemplo.ui;

import pt.ipp.isep.dei.esoft.project_exemplo.ui.console.menu.MainMenuUI;

public class Main {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}