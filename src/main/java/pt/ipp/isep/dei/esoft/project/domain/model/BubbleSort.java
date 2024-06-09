package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of the Bubble Sort algorithm for sorting GreenSpace objects.
 *
 * @author Group22
 */
public class BubbleSort implements Sortable {

    /**
     * Sorts a list of GreenSpace objects based on their area in ascending order using the Bubble Sort algorithm.
     *
     * @param greenSpaceList The list of GreenSpace objects to be sorted.
     * @return The sorted list of GreenSpace objects.
     */
    public List<GreenSpace> getSortedGreenSpaces(List<GreenSpace> greenSpaceList) {

        int n = greenSpaceList.size();

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (!greenSpaceList.get(j).isAreaLarger(greenSpaceList.get(j + 1).getArea())) {

                    Collections.swap(greenSpaceList, j, j + 1);
                }
            }
        }

        return greenSpaceList;
    }
}
