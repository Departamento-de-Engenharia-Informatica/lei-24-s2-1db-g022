package pt.ipp.isep.dei.esoft.project.domain;

import java.util.regex.Pattern;

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

        if(validateNullString(streetName)){
            this.streetName = streetName;
        }else{
            throw new IllegalArgumentException("Address street name cannot be null or empty.");
        }

        if(validateNullInt(doorNumber)){
            this.doorNumber = doorNumber;
        }else{
            throw new IllegalArgumentException("Address door number cannot be null or empty.");
        }

        if(validateNullString(postCode) && validatePostCode(postCode)){
            this.postCode = postCode;
        }else{
            throw new IllegalArgumentException("Address post code cannot be null or empty or have an incorrect format.");
        }
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

    public Address clone(){
        return new Address(this.streetName,this.postCode,this.doorNumber);
    }

    private boolean validatePostCode(String value){
        String regex = "\\d{4}-\\d{3}";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(value).matches();
    }
    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    private boolean validateNullInt(int value) {
        return !(value <= 0);
    }
}
