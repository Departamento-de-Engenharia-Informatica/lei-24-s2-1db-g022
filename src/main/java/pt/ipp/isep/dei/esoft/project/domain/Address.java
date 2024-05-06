package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The Address class represents a physical address with a street name, postal code, and door number.
 * It provides methods to access and manipulate these attributes.
 *
 * @author Group22
 */
public class Address {

    private String streetName;
    private String postCode;
    private int doorNumber;

    /**
     * Constructs an Address object with the specified street name, postal code, and door number.
     *
     * @param streetName The street name.
     * @param postCode The postal code.
     * @param doorNumber The door number.
     */
    public Address(String streetName, String postCode, int doorNumber) {
        this.streetName = streetName;
        this.postCode = postCode;
        this.doorNumber = doorNumber;
    }

    /**
     * Gets the street name of the address.
     *
     * @return The street name.
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Gets the postal code of the address.
     *
     * @return The postal code.
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Gets the door number of the address.
     *
     * @return The door number.
     */
    public int getDoorNumber() {
        return doorNumber;
    }

    /**
     * Returns a string representation of the address.
     *
     * @return A string representation of the address.
     */
    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", doorNumber=" + doorNumber +
                '}';
    }
}
