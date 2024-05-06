package pt.ipp.isep.dei.esoft.project.ui.console;

/**
 * The DevTeamUI class represents the user interface for displaying the development team members.
 * It provides functionality for displaying information about the development team.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class DevTeamUI implements Runnable {

    /**
     * Constructs a DevTeamUI object.
     */
    public DevTeamUI() {

    }

    /**
     * Runs the development team user interface.
     * Displays information about the development team members.
     */
    public void run() {
        System.out.println("\n");
        System.out.println("--- DEVELOPMENT TEAM -------------------");
        System.out.println("  Daniel Silva - 1170499@isep.ipp.pt");
        System.out.println("  Luigy Lima - 1191330@isep.ipp.pt");
        System.out.println("  Tomás Pereira - 1191337@isep.ipp.pt");
        System.out.println("  Diogo Almeida - 1200356@isep.ipp.pt");
        System.out.println("\n");
    }
}
