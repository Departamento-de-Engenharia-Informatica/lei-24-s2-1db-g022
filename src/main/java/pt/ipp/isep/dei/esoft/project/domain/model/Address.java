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

    /**
     * Constructs an Address object with the specified attributes.
     *
     * @param streetName The name of the street.
     * @param doorNumber The door number.
     * @param postCodeNumber The postal code number.
     * @param localization The localization.
     * @throws IllegalArgumentException If the door number is zero or negative.
     */
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

    /**
     * Creates and returns a new Address object with the same attributes as this Address.
     *
     * @return A new Address object.
     */
    public Address clone() {
        return new Address(this.streetName, this.postCode.getPostCodeNumber(), this.doorNumber);
    }


    /**
     * Validates a string value to ensure it is not null or empty.
     *
     * @param value The string value to validate.
     * @return {@code true} if the string value is not null or empty; {@code false} otherwise.
     */
    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    /**
     * Validates an integer value to ensure it is greater than zero.
     *
     * @param value The integer value to validate.
     * @return {@code true} if the integer value is greater than zero; {@code false} otherwise.
     */
    private boolean validateNullInt(int value) {
        return !(value <= 0);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The object to compare for equality.
     * @return {@code true} if this object is equal to the other object; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return doorNumber == address.doorNumber && Objects.equals(streetName, address.streetName) && Objects.equals(postCode, address.postCode);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(streetName, postCode, doorNumber);
    }
}
