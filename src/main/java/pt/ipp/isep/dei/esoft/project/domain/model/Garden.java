package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a Garden entity that implements the GreenSpace interface.
 *
 * @author Group22
 */
public class Garden implements GreenSpace {
    private String name;
    private int area;
    Address address;

    /**
     * Constructs a Garden object with the specified name, area, and address details.
     *
     * @param greenSpaceName The name of the garden.
     * @param area           The area of the garden.
     * @param streetName     The street name of the garden's address.
     * @param doorNumber     The door number of the garden's address.
     * @param postCodeNumber The post code number of the garden's address.
     * @param localization   The localization of the garden's address.
     * @throws IllegalArgumentException If the area is zero or negative.
     */
    public Garden(String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
        this.name = greenSpaceName;
        if (validateNullInt(area)) {
            this.area = area;
        } else {
            throw new IllegalArgumentException("Area cannot be zero or negative numbers.");
        }

        this.address = new Address(streetName, doorNumber, postCodeNumber, localization);
    }

    private boolean validateNullInt(int value) {
        return !(value <= 0);
    }

    /**
     * Retrieves the name of the garden.
     *
     * @return The name of the garden.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the area of the garden.
     *
     * @return The area of the garden.
     */
    @Override
    public int getArea() {
        return this.area;
    }

    /**
     * Retrieves the address of the garden.
     *
     * @return The address of the garden.
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
     * Checks if this garden is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Garden)) return false;
        Garden garden = (Garden) o;
        return area == garden.area && Objects.equals(name, garden.name) && Objects.equals(address, garden.address);
    }

    /**
     * Generates a hash code for this garden.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, area, address);
    }

    /**
     * Retrieves a string representation of this garden.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Garden{" +
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
