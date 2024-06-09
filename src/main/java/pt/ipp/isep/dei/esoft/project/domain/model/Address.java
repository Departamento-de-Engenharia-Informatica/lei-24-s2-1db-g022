package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * The Address class represents a physical address with a street name, postal code, and door number.
 * It provides methods to access and manipulate these attributes.
 *
 * @author Group22
 */
public class Address {

    private String streetName;
    private PostCode postCode;
    private int doorNumber;

    /**
     * Constructs an Address object with the specified street name, postal code, and door number.
     *
     * @param streetName The street name.
     * @param doorNumber The door number.
     */
    public Address(String streetName, String postCodeNumber, int doorNumber) {

        if (validateNullString(streetName)) {
            this.streetName = streetName;
        } else {
            throw new IllegalArgumentException("Address street name cannot be null or empty.");
        }

        if (validateNullInt(doorNumber)) {
            this.doorNumber = doorNumber;
        } else {
            throw new IllegalArgumentException("Address door number cannot be null or empty.");
        }

        this.postCode = new PostCode(postCodeNumber);

    }

    public Address(String streetName, int doorNumber, String postCodeNumber, String localization) {
        this.streetName = streetName;
        if (validateNullInt(doorNumber)) {
            this.doorNumber = doorNumber;
        } else {
            throw new IllegalArgumentException("DoorNumber cannot be zero or negative numbers.");
        }
        this.postCode = new PostCode(postCodeNumber, localization);
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
    public PostCode getPostCode() {
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

    public Address clone() {
        return new Address(this.streetName, this.postCode.getPostCodeNumber(), this.doorNumber);
    }

    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    private boolean validateNullInt(int value) {
        return !(value <= 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return doorNumber == address.doorNumber && Objects.equals(streetName, address.streetName) && Objects.equals(postCode, address.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, postCode, doorNumber);
    }
}
