package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Garden implements GreenSpace {
    private String name;
    private int area;
    Address address;

    public Garden(String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
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
        return 0;
    }

    @Override
    public Address getAddress() {
        return null;
    }

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
}
