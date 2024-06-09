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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getArea() {
        return this.area;
    }

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
    public boolean hasName(String greenSpaceName){

        return this.name.equals(greenSpaceName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Garden)) return false;
        Garden garden = (Garden) o;
        return area == garden.area && Objects.equals(name, garden.name) && Objects.equals(address, garden.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area, address);
    }

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
