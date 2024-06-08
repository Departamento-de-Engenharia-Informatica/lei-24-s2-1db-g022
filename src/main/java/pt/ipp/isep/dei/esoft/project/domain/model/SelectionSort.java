package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Collections;
import java.util.List;

public class SelectionSort implements Sortable {

    public List<GreenSpace> getSortedGreenSpaces(List<GreenSpace> greenSpaceList) {

        int n = greenSpaceList.size();

        for (int i = 0; i < n - 1; i++) {

            int max_idx = i;

            for (int j = i + 1; j < n; j++) {

                if (greenSpaceList.get(j).isAreaLarger(greenSpaceList.get(max_idx).getArea())) {
                    max_idx = j;
                }
            }

            Collections.swap(greenSpaceList, i, max_idx);
        }

        return greenSpaceList;
    }
}
