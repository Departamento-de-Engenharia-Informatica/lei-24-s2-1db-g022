package pt.ipp.isep.dei.esoft.project.domain.DTO;

import java.util.Objects;

/**
 * A Data Transfer Object (DTO) representing a GreenSpace entity.
 *
 * @author Group22
 */
public final class GreenSpaceDto {

    private final String greenSpaceName;
    private final int area;

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

    /**
     * Checks if this GreenSpaceDto is equal to another object.
     *
     * @param o the object to compare this GreenSpaceDto against
     * @return true if the given object represents a GreenSpaceDto equivalent to this instance, false otherwise
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof GreenSpaceDto)) return false;
        GreenSpaceDto that = (GreenSpaceDto) o;

        return area == that.area && Objects.equals(greenSpaceName, that.greenSpaceName);
    }

    /**
     * Returns a hash code value for this GreenSpaceDto.
     *
     * @return a hash code value for this GreenSpaceDto
     */
    @Override
    public int hashCode() {

        return Objects.hash(greenSpaceName, area);
    }
}
