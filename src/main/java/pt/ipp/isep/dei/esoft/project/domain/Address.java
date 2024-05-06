package pt.ipp.isep.dei.esoft.project.domain;

/**
 * @author Group22
 */
public class Address {
    private String streetName;
    private String postCode;
    private int doorNumber;

    public Address(String streetName, String postCode, int doorNumber) {
        this.streetName = streetName;
        this.postCode = postCode;
        this.doorNumber = doorNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", doorNumber=" + doorNumber +
                '}';
    }
}
