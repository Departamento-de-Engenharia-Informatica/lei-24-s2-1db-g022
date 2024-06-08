package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Collections;
import java.util.List;

public class BubbleSort implements Sortable {

    public List<GreenSpace> getSortedGreenSpaces(List<GreenSpace> greenSpaceList) {

        int n = greenSpaceList.size();

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (!greenSpaceList.get(j).isAreaLarger(greenSpaceList.get(j+1).getArea())) {

                    Collections.swap(greenSpaceList, j, j + 1);
                }
            }
        }

        return greenSpaceList;
    }
}
