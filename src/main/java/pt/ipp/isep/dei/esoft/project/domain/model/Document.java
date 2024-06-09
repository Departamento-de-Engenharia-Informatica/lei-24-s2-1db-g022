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

    public Document(String docType, int number) {

        this.number = number;

        if (validateNullString(docType)){
            this.docType = docType;
        }else {
            throw new IllegalArgumentException("Document type cannot be null or empty.");
        }

        if(validateNullInt(number) && validateDocNumber(number)){
            this.number = number;
        }else{
            throw new IllegalArgumentException("Document Number cannot be null or empty or have an incorrect format.");
        }
    }

    public Document(String docType) {
        this.docType = docType;
    }

    public String getDocType() {
        return docType;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Document)) return false;
        Document document = (Document) object;
        return number == document.number && Objects.equals(docType, document.docType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docType, number);
    }

    @Override
    public String toString() {
        return "Document{" +
                "docType='" + docType + '\'' +
                ", number=" + number +
                '}';
    }
    private boolean validateDocNumber(int value){
        String regex = "\\b\\d{9}\\b";

        Pattern pattern = Pattern.compile(regex);

        String value2 = String.valueOf(value);

        return pattern.matcher(value2).matches();
    }

    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    private boolean validateNullInt(int value) {
        return !(value <= 0);
    }

    public boolean duplicateNumber(Document document) {
        return this.number == document.number;
    }

    public Document clone() {
        return new Document(this.docType, this.number);
    }
}
