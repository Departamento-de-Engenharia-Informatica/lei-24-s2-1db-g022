package pt.ipp.isep.dei.esoft.project.domain.model;

/**
 * Represents a green space, providing methods to access basic information such as name, area, and address.
 * It also includes methods to check if the green space has a specific name or if its area is larger than a given value.
 *
 * @author Group22
 */
public interface GreenSpace {
    String getName();

    int getArea();

    Address getAddress();

    /**
     * Checks if the GreenSpace has the given name.
     *
     * @param greenSpaceName The name to check against the GreenSpace's name.
     * @return true if the GreenSpace has the given name, false otherwise.
     */
    boolean hasName(String greenSpaceName);

    /**
     * Checks if the area of the GreenSpace is larger than the given area.
     *
     * @param area The area to compare with the GreenSpace's area.
     * @return true if the GreenSpace's area is larger than the given area, false otherwise.
     */
    boolean isAreaLarger(int area);

    /**
     * Indicates whether some other object is "equal to" this GreenSpace.
     *
     * @param greenSpace the reference object with which to compare.
     * @return true if this GreenSpace is the same as the obj argument; false otherwise.
     */
    boolean equals(Object greenSpace);
}
