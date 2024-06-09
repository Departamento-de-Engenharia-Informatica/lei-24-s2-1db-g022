package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a MediumPark entity that implements the GreenSpace interface.
 *
 * @author Group22
 */
public class MediumPark implements GreenSpace {
    private String name;
    private int area;
    Address address;

    /**
     * Constructs a new MediumPark with the specified parameters.
     *
     * @param greenSpaceName The name of the medium park.
     * @param area           The area of the medium park.
     * @param streetName     The street name of the medium park's address.
     * @param doorNumber     The door number of the medium park's address.
     * @param postCodeNumber The postal code number of the medium park's address.
     * @param localization   The localization of the medium park's address.
     * @throws IllegalArgumentException if the area is zero or negative.
     */
    public MediumPark(String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
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
     * Gets the name of the medium park.
     *
     * @return The name of the medium park.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the area of the medium park.
     *
     * @return The area of the medium park.
     */
    @Override
    public int getArea() {
        return this.area;
    }

    /**
     * Gets the address of the medium park.
     *
     * @return The address of the medium park.
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
     * This method considers two medium parks as equal if they have the same name, area, and address.
     *
     * @param o The reference object with which to compare.
     * @return True if this medium park is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediumPark)) return false;
        MediumPark that = (MediumPark) o;
        return area == that.area && Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    /**
     * Returns a hash code value for the medium park.
     * This method computes a hash code based on the name, area, and address of the medium park.
     *
     * @return A hash code value for this medium park.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, area, address);
    }

    /**
     * Returns a string representation of the medium park.
     * This method returns a string containing the name, area, and address of the medium park.
     *
     * @return A string representation of the medium park.
     */
    @Override
    public String toString() {
        return "MediumPark{" +
                "name='" + name + '\'' +
                ", area=" + area +
                ", address=" + address +
                '}';
    }

    /**
     * Checks if the area of the GreenSpace is mediumr than the given area.
     *
     * @param area The area to compare with the GreenSpace's area.
     * @return true if the GreenSpace's area is mediumr than the given area, false otherwise.
     */
    @Override
    public boolean isAreaLarger(int area) {

        return this.area > area;
    }
}
