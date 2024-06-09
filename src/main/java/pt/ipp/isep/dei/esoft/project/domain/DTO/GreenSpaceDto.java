package pt.ipp.isep.dei.esoft.project.domain.DTO;

/**
 * A Data Transfer Object (DTO) representing a GreenSpace entity.
 *
 * @author Group22
 */
public class GreenSpaceDto {

    private String greenSpaceName;
    private int area;

    /**
     * Constructs a GreenSpaceDto object with the given parameters.
     *
     * @param greenSpaceName The name of the green space.
     * @param area           The area of the green space.
     */
    public GreenSpaceDto(String greenSpaceName, int area) {

        this.greenSpaceName = greenSpaceName;
        this.area = area;
    }

    /**
     * Retrieves the name of the green space.
     *
     * @return The name of the green space.
     */
    public String getGreenSpaceName() {

        return greenSpaceName;
    }

    /**
     * Retrieves the area of the green space.
     *
     * @return The area of the green space.
     */
    public int getArea() {

        return area;
    }
}
