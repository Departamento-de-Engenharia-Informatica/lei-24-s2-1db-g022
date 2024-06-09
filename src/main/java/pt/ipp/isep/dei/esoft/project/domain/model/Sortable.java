package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.List;

/**
 * Represents an interface for sorting collections of GreenSpace objects.
 *
 * @author Group22
 */
public interface Sortable {

    /**
     * Sorts a list of GreenSpace objects according to a specific sorting algorithm.
     *
     * @param greenSpaceList The list of GreenSpace objects to be sorted.
     * @return The sorted list of GreenSpace objects.
     */
    List<GreenSpace> getSortedGreenSpaces(List<GreenSpace> greenSpaceList);
}
