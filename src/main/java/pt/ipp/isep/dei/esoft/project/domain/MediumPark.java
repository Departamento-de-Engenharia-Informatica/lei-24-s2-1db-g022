package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class MediumPark implements GreenSpace {
    private String name;
    private int area;
    Address address;

    public MediumPark(String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
        this.name = greenSpaceName;
        this.area = area;
        this.address = new Address(streetName, doorNumber, postCodeNumber, localization);
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
