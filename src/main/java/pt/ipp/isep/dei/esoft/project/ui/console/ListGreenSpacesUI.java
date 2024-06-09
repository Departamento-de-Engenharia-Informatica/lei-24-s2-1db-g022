package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceDto;

import java.util.List;

/**
 * User interface for listing green spaces.
 *
 * @author Group22
 */
public class ListGreenSpacesUI implements Runnable {

    private final ListGreenSpacesController controller;

    /**
     * Constructs a ListGreenSpacesUI object with a new ListGreenSpacesController.
     */
    public ListGreenSpacesUI() {

        controller = new ListGreenSpacesController();
    }

    /**
     * Gets the ListGreenSpacesController associated with this UI.
     *
     * @return The ListGreenSpacesController instance.
     */
    private ListGreenSpacesController getController() {

        return controller;
    }

    /**
     * Runs the user interface to list green spaces and their areas.
     */
    @Override
    public void run() {

        List<GreenSpaceDto> greenSpaceListDto = getController().getGreenSpaceManagerGSpaceSorted();

        for (GreenSpaceDto g : greenSpaceListDto) {

            System.out.println("\nParque: " + g.getGreenSpaceName());
            System.out.println("Área: " + g.getArea());
        }
    }
}
