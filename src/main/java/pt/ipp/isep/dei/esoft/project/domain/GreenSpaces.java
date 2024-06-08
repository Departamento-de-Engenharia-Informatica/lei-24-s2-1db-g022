package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class GreenSpaces {
    List<GreenSpace> greenSpacesList;

    public GreenSpaces() {
        greenSpacesList = new ArrayList<>();
    }

    public boolean addListGreenSpaces(GreenSpace greenSpace) {
        return this.greenSpacesList.add(greenSpace);
    }

    public List<GreenSpace> getGreenSpaceList() {

        return greenSpacesList;
    }

    public void addListBootstrapGreenSpaces(GreenSpace greenSpace) {
        this.greenSpacesList.add(greenSpace);
    }

}
