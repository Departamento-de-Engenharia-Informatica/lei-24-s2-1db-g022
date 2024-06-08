package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class LargePark implements GreenSpace {
    private String name;
    private int area;
    Address address;

    public LargePark(String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
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

    @Override
    public boolean hasName(String greenSpaceName){

        return this.name.equals(greenSpaceName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LargePark)) return false;
        LargePark largePark = (LargePark) o;
        return area == largePark.area && Objects.equals(name, largePark.name) && Objects.equals(address, largePark.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area, address);
    }

    @Override
    public String toString() {
        return "LargePark{" +
                "name='" + name + '\'' +
                ", area=" + area +
                ", address=" + address +
                '}';
    }
}
