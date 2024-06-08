package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class MediumPark implements GreenSpace {
    private String name;
    private int area;
    Address address;

    public MediumPark(String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
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
        if (!(o instanceof MediumPark)) return false;
        MediumPark that = (MediumPark) o;
        return area == that.area && Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area, address);
    }

    @Override
    public String toString() {
        return "MediumPark{" +
                "name='" + name + '\'' +
                ", area=" + area +
                ", address=" + address +
                '}';
    }
}
