package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaces {
    List<GreenSpace> greenSpacesList;

    public GreenSpaces() {
        this.greenSpacesList = new ArrayList<>();
    }

    public boolean addListGreenSpaces(GreenSpace greenSpace) {
        return this.greenSpacesList.add(greenSpace);
    }

    public void ver() {
        for (GreenSpace greenSpace : greenSpacesList) {
            System.out.println(greenSpace.toString());
        }
    }
}
