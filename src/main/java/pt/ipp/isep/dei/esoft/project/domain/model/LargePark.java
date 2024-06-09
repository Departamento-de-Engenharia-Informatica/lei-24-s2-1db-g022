package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a LargePark entity that implements the GreenSpace interface.
 *
 * @author Group22
 */
public class LargePark implements GreenSpace {
    private String name;
    private int area;
    Address address;

    /**
     * Constructs a new LargePark object with the specified parameters.
     *
     * @param greenSpaceName The name of the large park.
     * @param area           The area of the large park.
     * @param streetName     The street name where the large park is located.
     * @param doorNumber     The door number of the large park.
     * @param postCodeNumber The postal code number of the large park.
     * @param localization   The localization of the large park.
     * @throws IllegalArgumentException If the area is zero or negative.
     */
    public LargePark(String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
        this.name = greenSpaceName;
        if (validateNullInt(area)) {
            this.area = area;
        } else {
            throw new IllegalArgumentException("Area cannot be zero or negative numbers.");
        }

        this.address = new Address(streetName, doorNumber, postCodeNumber, localization);
    }

    /**
     * Validates if the given integer value is not zero or negative.
     *
     * @param value The integer value to validate.
     * @return True if the value is not zero or negative, false otherwise.
     */
    private boolean validateNullInt(int value) {
        return !(value <= 0);
    }

    /**
     * Gets the name of the large park.
     *
     * @return The name of the large park.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the area of the large park.
     *
     * @return The area of the large park.
     */
    @Override
    public int getArea() {
        return this.area;
    }

    /**
     * Gets the address of the large park.
     *
     * @return The address of the large park.
     */
    @Override
    public Address getAddress() {
        return this.address;
    }

    /**
     * Checks if the GreenSpace has the given name.
     *
     * @param greenSpaceName The name to check against the GreenSpace's name.
     * @return true if the GreenSpace has the given name, false otherwise.
     */
    @Override
    public boolean hasName(String greenSpaceName) {

        return this.name.equals(greenSpaceName);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * This method considers two large parks as equal if they have the same name, area, and address.
     *
     * @param o The reference object with which to compare.
     * @return True if this large park is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LargePark)) return false;
        LargePark largePark = (LargePark) o;
        return area == largePark.area && Objects.equals(name, largePark.name) && Objects.equals(address, largePark.address);
    }

    /**
     * Returns a hash code value for the large park.
     * This method computes a hash code based on the name, area, and address of the large park.
     *
     * @return A hash code value for this large park.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, area, address);
    }

    /**
     * Returns a string representation of the large park.
     * This method returns a string containing the name, area, and address of the large park.
     *
     * @return A string representation of the large park.
     */
    @Override
    public String toString() {
        return "LargePark{" +
                "name='" + name + '\'' +
                ", area=" + area +
                ", address=" + address +
                '}';
    }

    /**
     * Checks if the area of the GreenSpace is larger than the given area.
     *
     * @param area The area to compare with the GreenSpace's area.
     * @return true if the GreenSpace's area is larger than the given area, false otherwise.
     */
    @Override
    public boolean isAreaLarger(int area) {

        return this.area > area;
    }
}
