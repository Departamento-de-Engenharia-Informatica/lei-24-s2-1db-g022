package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a document.
 *
 * @author Group22
 */
public class Document {
    private String docType;
    private int number;

    /**
     * Constructs a Document object with the specified document type and number.
     *
     * @param docType The type of the document.
     * @param number  The number of the document.
     * @throws IllegalArgumentException If the document type is null or empty,
     *                                  or if the document number is less than or equal to zero,
     *                                  or if the document number has an incorrect format.
     */
    public Document(String docType, int number) {

        this.number = number;

        if (validateNullString(docType)) {
            this.docType = docType;
        } else {
            throw new IllegalArgumentException("Document type cannot be null or empty.");
        }

        if (validateNullInt(number) && validateDocNumber(number)) {
            this.number = number;
        } else {
            throw new IllegalArgumentException("Document Number cannot be null or empty or have an incorrect format.");
        }
    }

    /**
     * Constructs a Document object with the specified document type.
     *
     * @param docType The type of the document.
     */
    public Document(String docType) {
        this.docType = docType;
    }

    /**
     * Retrieves the document type.
     *
     * @return The document type.
     */
    public String getDocType() {
        return docType;
    }

    /**
     * Retrieves the document number.
     *
     * @return The document number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Checks if this document is equal to another object.
     *
     * @param object The object to compare to.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Document)) return false;
        Document document = (Document) object;
        return number == document.number && Objects.equals(docType, document.docType);
    }

    /**
     * Generates a hash code for this document.
     *
     * @return The hash code for this document.
     */
    @Override
    public int hashCode() {
        return Objects.hash(docType, number);
    }


    /**
     * Converts this document to a string representation.
     *
     * @return A string representation of this document.
     */
    @Override
    public String toString() {
        return "Document{" +
                "docType='" + docType + '\'' +
                ", number=" + number +
                '}';
    }

    /**
     * Validates a document number to ensure it matches the correct format.
     *
     * @param value The document number to validate.
     * @return {@code true} if the document number has the correct format; {@code false} otherwise.
     */
    private boolean validateDocNumber(int value) {
        String regex = "\\b\\d{9}\\b";

        Pattern pattern = Pattern.compile(regex);

        String value2 = String.valueOf(value);

        return pattern.matcher(value2).matches();
    }

    /**
     * Validates a string to ensure it is not null or empty.
     *
     * @param value The string to validate.
     * @return {@code true} if the string is not null or empty; {@code false} otherwise.
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
     * Checks if this document has the same number as another document.
     *
     * @param document The document to compare to.
     * @return {@code true} if the documents have the same number; {@code false} otherwise.
     */
    public boolean duplicateNumber(Document document) {
        return this.number == document.number;
    }

    /**
     * Creates a clone of this document.
     *
     * @return A clone of this document.
     */
    public Document clone() {
        return new Document(this.docType, this.number);
    }
}
