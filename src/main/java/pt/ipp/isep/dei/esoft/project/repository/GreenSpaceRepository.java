package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.model.Garden;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.model.LargePark;
import pt.ipp.isep.dei.esoft.project.domain.model.MediumPark;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing green spaces.
 *
 * @author Group22
 */
public class GreenSpaceRepository {

    private final List<GreenSpace> greenSpaceList;

    /**
     * Constructs a new GreenSpaceRepository with an empty list of green spaces.
     */
    public GreenSpaceRepository() {
        greenSpaceList = new ArrayList<>();
    }

    /**
     * Registers a new green space with the specified details.
     *
     * @param greenSpaceType The type of green space (e.g., "Garden", "Medium Park", "Large Park").
     * @param greenSpaceName The name of the green space.
     * @param area           The area of the green space.
     * @param streetName     The street name where the green space is located.
     * @param doorNumber     The door number of the green space.
     * @param postCodeNumber The postal code number of the green space.
     * @param localization   The localization of the green space.
     * @return An Optional containing the registered green space if successful, or empty if registration fails.
     */
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

    /**
     * Adds a new green space to the repository if all data is valid.
     *
     * @param greenSpace The green space to add to the repository.
     * @return true if the green space is successfully added, false otherwise.
     */
    private boolean addGreenGreenSpace(GreenSpace greenSpace) {
        boolean success = false;
        if (validateAllData(greenSpace)) {
            success = greenSpaceList.add(greenSpace);
        }

        return success;
    }

    /**
     * Adds a green space to the repository during bootstrap.
     *
     * @param greenSpace The green space to add to the repository.
     */
    public void addGreenSpaceBootstrap(GreenSpace greenSpace) {
        greenSpaceList.add(greenSpace);
    }

    /**
     * Validates if the given green space is not already present in the repository.
     *
     * @param greenSpace The green space to validate.
     * @return true if the green space is not already present in the repository, false otherwise.
     */
    private boolean validateAllData(GreenSpace greenSpace) {
        return !greenSpaceList.contains(greenSpace);
    }

    /**
     * Retrieves a green space by its name from the repository.
     *
     * @param greenSpaceName The name of the green space to retrieve.
     * @return An Optional containing the green space if found, otherwise an empty Optional.
     */
    public Optional<GreenSpace> getGreenSpaceByName(String greenSpaceName) {

        for (GreenSpace g : greenSpaceList) {

            if (g.hasName(greenSpaceName)) {

                return Optional.of(g);
            }
        }

        return Optional.empty();
    }
}