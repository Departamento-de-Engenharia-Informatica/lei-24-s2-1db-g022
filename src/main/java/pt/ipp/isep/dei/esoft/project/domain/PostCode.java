package pt.ipp.isep.dei.esoft.project.domain;

import java.util.regex.Pattern;

public class PostCode {
    private String localization;
    private String postCodeNumber;

    public PostCode(String postCodeNumber, String localization) {
        this.localization = localization;
        if (validateNullString(postCodeNumber) && validatePostCode(postCodeNumber)) {
            this.postCodeNumber = postCodeNumber;
        } else {
            throw new IllegalArgumentException("Address post code cannot be null or empty or have an incorrect format.");
        }
    }

    public PostCode(String postCodeNumber) {
        if (validateNullString(postCodeNumber) && validatePostCode(postCodeNumber)) {
            this.postCodeNumber = postCodeNumber;
        } else {
            throw new IllegalArgumentException("Address post code cannot be null or empty or have an incorrect format.");
        }

    }

    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    private boolean validatePostCode(String value) {
        String regex = "\\d{4}-\\d{3}";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(value).matches();
    }

    public String getLocalization() {
        return localization;
    }

    public String getPostCodeNumber() {
        return postCodeNumber;
    }

    @Override
    public String toString() {
        return "PostCode{" +
                "localization='" + localization + '\'' +
                ", postCodeNumber='" + postCodeNumber + '\'' +
                '}';
    }
}
