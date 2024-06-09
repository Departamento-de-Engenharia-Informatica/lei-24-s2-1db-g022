package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a postal code, consisting of a localization and a postal code number.
 *
 * @author Group22
 */
public class PostCode {
    private String localization;
    private String postCodeNumber;

    /**
     * Initializes a new PostCode instance with the given post code number and localization.
     *
     * @param postCodeNumber The postal code number.
     * @param localization   The localization associated with the postal code.
     * @throws IllegalArgumentException If the post code number is null, empty, or has an incorrect format.
     */
    public PostCode(String postCodeNumber, String localization) {
        this.localization = localization;
        if (validateNullString(postCodeNumber) && validatePostCode(postCodeNumber)) {
            this.postCodeNumber = postCodeNumber;
        } else {
            throw new IllegalArgumentException("Address post code cannot be null or empty or have an incorrect format.");
        }
    }

    /**
     * Initializes a new PostCode instance with the given post code number.
     *
     * @param postCodeNumber The postal code number.
     * @throws IllegalArgumentException If the post code number is null, empty, or has an incorrect format.
     */
    public PostCode(String postCodeNumber) {
        if (validateNullString(postCodeNumber) && validatePostCode(postCodeNumber)) {
            this.postCodeNumber = postCodeNumber;
        } else {
            throw new IllegalArgumentException("Address post code cannot be null or empty or have an incorrect format.");
        }

    }

    /**
     * Validates if the given string is not null or empty.
     *
     * @param value The string to validate.
     * @return True if the string is not null or empty; false otherwise.
     */
    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    /**
     * Validates if the given string matches the format of a postal code (e.g., "1234-123").
     *
     * @param value The string to validate.
     * @return True if the string matches the postal code format; false otherwise.
     */
    private boolean validatePostCode(String value) {
        String regex = "\\d{4}-\\d{3}";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(value).matches();
    }

    /**
     * Gets the localization associated with the postal code.
     *
     * @return The localization associated with the postal code.
     */
    public String getLocalization() {
        return localization;
    }

    /**
     * Gets the postal code number.
     *
     * @return The postal code number.
     */
    public String getPostCodeNumber() {
        return postCodeNumber;
    }

    /**
     * Returns a string representation of the postal code.
     *
     * @return A string representation of the postal code.
     */
    @Override
    public String toString() {
        return "PostCode{" +
                "localization='" + localization + '\'' +
                ", postCodeNumber='" + postCodeNumber + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the o argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostCode)) return false;
        PostCode postCode = (PostCode) o;
        return Objects.equals(localization, postCode.localization) && Objects.equals(postCodeNumber, postCode.postCodeNumber);
    }

    /**
     * Returns a hash code value for the postal code.
     *
     * @return A hash code value for the postal code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(localization, postCodeNumber);
    }
}
