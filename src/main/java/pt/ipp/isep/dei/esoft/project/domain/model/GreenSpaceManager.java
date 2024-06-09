package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a GreenSpaceManager, which is a type of collaborator responsible for managing green spaces.
 *
 * @author Group22
 */
public class GreenSpaceManager implements ICollaborator {
    private String name;
    private Date dateOfBirth;
    private Date admissionDate;
    private String phoneNumber;
    private String email;
    private Address address;
    private Document document;
    private Job job;
    private int taxpayer;
    private final List<Skill> skillList;
    private GreenSpaces greenSpaces;

    public GreenSpaceManager(GreenSpaces greenSpaces, int taxpayer, Job job, Document document, Address address, String email, String phoneNumber, Date admissionDate, Date dateOfBirth, String name) {
        this.greenSpaces = greenSpaces;
        this.skillList = new ArrayList<>();
        this.taxpayer = taxpayer;
        this.job = job;
        this.document = document;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.admissionDate = admissionDate;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
    }

    public GreenSpaceManager(String email) {
        this.email = email;
        this.skillList = new ArrayList<>();
        this.greenSpaces = new GreenSpaces();
    }


    public GreenSpaceManager(String name, Date dateOfBirth, Date admissionDate, String phoneNumber, String email, Address address, int taxpayer, String docType, int number, Job job) {
        this.document = new Document(docType, number);
        this.address = address;
        this.job = job;

        if (validateNullDate(dateOfBirth) && validateNullDate(admissionDate)) {
            this.dateOfBirth = dateOfBirth;
            this.admissionDate = admissionDate;
        } else {

            throw new IllegalArgumentException("Collaborator date of birth or admission date cannot be null or empty.");
        }

        if (validateNullString(name) && validateName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Collaborator name cannot be null or empty or numbers.");
        }

        if (validateEmail(email) && validateNullString(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Collaborator email cannot be null or empty or have an incorrect format.");
        }

        if (validateNullString(phoneNumber) && validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Collaborator phone number cannot be null or empty or have an incorrect format.");
        }

        if (validateNullInt(taxpayer) && validateTaxPayer(taxpayer)) {
            this.taxpayer = taxpayer;
        } else {
            throw new IllegalArgumentException("Collaborator TaxPayer cannot be null or empty or have an incorrect format.");

        }

        this.skillList = new ArrayList<>();
        this.greenSpaces = new GreenSpaces();
    }


    /**
     * Gets the name of the collaborator.
     *
     * @return The name of the collaborator.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of birth of the collaborator.
     *
     * @return The date of birth of the collaborator.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets the admission date of the collaborator.
     *
     * @return The admission date of the collaborator.
     */
    public Date getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Gets the phone number of the collaborator.
     *
     * @return The phone number of the collaborator.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Document getDocument() {
        return document;
    }

    /**
     * Gets the email address of the collaborator.
     *
     * @return The email address of the collaborator.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the address of the collaborator.
     *
     * @return The address of the collaborator.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets the job position of the collaborator.
     *
     * @return The job position of the collaborator.
     */
    public Job getJob() {
        return job;
    }


    /**
     * Gets the list of skills of the collaborator.
     *
     * @return The list of skills of the collaborator.
     */
    public List<Skill> getSkillList() {
        return List.copyOf(skillList);
    }

    private boolean validateNullInt(int value) {
        return !(value <= 0);
    }

    private boolean validateTaxPayer(int value) {
        String regex = "\\b\\d{9}\\b";

        Pattern pattern = Pattern.compile(regex);

        String value2 = String.valueOf(value);

        return pattern.matcher(value2).matches();
    }

    /**
     * Validates if the provided phone number matches the expected format.
     *
     * @param value The phone number to validate.
     * @return True if the phone number matches the format, false otherwise.
     */
    private boolean validatePhoneNumber(String value) {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(value).matches();
    }


    private boolean validateName(String value) {
        String regex = "^[a-zA-Z]{1,6}$"; // Matches up to six letters (upper or lower case)
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).matches();
    }


    /**
     * Validates if the provided email address matches the expected format.
     *
     * @param value The email address to validate.
     * @return True if the email address matches the format, false otherwise.
     */
    private boolean validateEmail(String value) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(value).matches();
    }

    /**
     * Validates if the provided date is not null.
     *
     * @param value The date to validate.
     * @return True if the date is not null, false otherwise.
     */
    private boolean validateNullDate(Date value) {
        return !(value == null);
    }

    /**
     * Validates if the provided string is not null or empty.
     *
     * @param value The string to validate.
     * @return True if the string is not null or empty, false otherwise.
     */
    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    /**
     * Checks if the Collaborator has the same email as the given collaborator.
     *
     * @param collaborator The collaborator to compare emails with.
     * @return true if the Collaborator has the same email as the given collaborator, false otherwise.
     */
    @Override
    public boolean hasEqualsEmail(ICollaborator collaborator) {

        return this.email.equals(((GreenSpaceManager) collaborator).email);
    }

    /**
     * Checks if the GreenSpaceManager has the same phone number as the given collaborator.
     *
     * @param collaborator The collaborator to compare phone numbers with.
     * @return true if the GreenSpaceManager has the same phone number as the given collaborator, false otherwise.
     */
    @Override
    public boolean hasEqualsPhoneNumber(ICollaborator collaborator) {

        return this.phoneNumber.equals(((GreenSpaceManager) collaborator).phoneNumber);
    }

    /**
     * Checks if the Collaborator has the same tax number as the given collaborator.
     *
     * @param collaborator The collaborator to compare tax numbers with.
     * @return true if the Collaborator has the same tax number as the given collaborator, false otherwise.
     */
    @Override
    public boolean hasEqualsTaxNumber(ICollaborator collaborator) {

        return this.taxpayer == ((GreenSpaceManager) collaborator).taxpayer;
    }

    /**
     * Clones the brand.
     *
     * @return A clone of the current collaborator instance.
     */
    public GreenSpaceManager clone() {

        GreenSpaceManager clone = new GreenSpaceManager(this.greenSpaces, this.taxpayer, this.job, this.document, this.address, this.email, this.phoneNumber, this.admissionDate, this.dateOfBirth, this.name);

        for (Skill in : this.skillList) {
            clone.skillList.add(in.clone());
        }

        return clone;
    }

    /**
     * Checks if the Collaborator has the given email.
     *
     * @param email The email to check against the Collaborator's email.
     * @return true if the Collaborator has the given email, false otherwise.
     */
    @Override
    public boolean hasEmail(String email) {

        return this.email.equals(email);
    }

    /**
     * Checks if this collaborator is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GreenSpaceManager)) return false;
        GreenSpaceManager that = (GreenSpaceManager) o;
        return taxpayer == that.taxpayer && Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(admissionDate, that.admissionDate) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(document, that.document) && Objects.equals(job, that.job) && Objects.equals(skillList, that.skillList);
    }

    /**
     * Generates a hash code value for the GreenSpaceManager.
     *
     * @return A hash code value for the GreenSpaceManager.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, admissionDate, phoneNumber, email, address, document, job, taxpayer, skillList);
    }

    /**
     * Returns a string representation of the GreenSpaceManager.
     *
     * @return A string representation of the GreenSpaceManager.
     */
    @Override
    public String toString() {
        return "GreenSpaceManager{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", admissionDate=" + admissionDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", document=" + document +
                ", job=" + job +
                ", taxpayer=" + taxpayer +
                ", skillList=" + skillList +
                '}';
    }

    public boolean addListGreenSpace(GreenSpace greenSpace) {
        return this.greenSpaces.addListGreenSpaces(greenSpace);
    }


    /**
     * Retrieves the GreenSpaces object associated with this GreenSpaceManager.
     *
     * @return The GreenSpaces object associated with this GreenSpaceManager.
     */
    public GreenSpaces getListGreenSpaces() {

        return this.greenSpaces;
    }
}
