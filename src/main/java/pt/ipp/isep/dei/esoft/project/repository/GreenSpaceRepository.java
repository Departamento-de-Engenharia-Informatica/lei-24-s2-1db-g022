package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Garden;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.LargePark;
import pt.ipp.isep.dei.esoft.project.domain.MediumPark;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpaceRepository {

    private final List<GreenSpace> greenSpaceList;

    public GreenSpaceRepository() {
        greenSpaceList = new ArrayList<>();
    }

    public Optional<GreenSpace> registerGreenSpace(String greenSpaceType, String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
        Optional<GreenSpace> optionalValue = Optional.empty();
        GreenSpace newGreenSpace = null;
        switch (greenSpaceType) {
            case "Garden":
                newGreenSpace = new Garden(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
                break;
            case "Medium Park":
                newGreenSpace = new MediumPark(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
                break;
            case "Large Park":
                newGreenSpace = new LargePark(greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
                break;
        }

        if (addGreenGreenSpace(newGreenSpace)) {
            optionalValue = Optional.of(newGreenSpace);
        }

        return optionalValue;
    }

    private boolean addGreenGreenSpace(GreenSpace greenSpace) {
        boolean success = false;
        if (validateAllData(greenSpace)) {
            success = greenSpaceList.add(greenSpace);
        }

        return success;
    }

    private boolean validateAllData(GreenSpace greenSpace) {
        return !greenSpaceList.contains(greenSpace);
    }

    public void ver(){
        for (GreenSpace greenSpace : this.greenSpaceList) {
            System.out.println(greenSpace.toString());
        }
    }
}