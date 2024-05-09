package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The Collaborator class represents a collaborator in an organization.
 * It contains personal and professional information such as name, date of birth, admission date, contact details,
 * address, job position, and skills.
 *
 * @author Group22
 */
public class Collaborator {

    private String name;
    private Date dateOfBirth;
    private Date admissionDate;
    private String phoneNumber;
    private String email;
    private Address address;
    private Document document;
    private Job job;
    private List<Skill> skillList;


    /**
     * Constructs a Collaborator object with default values.
     */
    public Collaborator() {
    }

    public Collaborator(String name, Date dateOfBirth, Date admissionDate, String phoneNumber, String email, Address address, Document document, Job job) {




        this.address = address;
        this.document = document;
        this.job = job;
        this.skillList=new ArrayList<>();

        if (validateNullDate(dateOfBirth) && validateNullDate(admissionDate)) {
            this.dateOfBirth = dateOfBirth;
            this.admissionDate = admissionDate;
        } else {

            throw new IllegalArgumentException("Collaborator date of birth or admission date cannot be null or empty.");
        }

        if (validateNullString(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Collaborator name, phone number and email cannot be null or empty.");
        }

        if(validateEmail(email) && validateNullString(email)){
            this.email = email;
        }else {
            throw new IllegalArgumentException("Collaborator email cannot be null or empty or have an incorrect format.");
        }

        if (validateNullString(phoneNumber) && validatePhoneNumber(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }else {
            throw new IllegalArgumentException("Collaborator phone number cannot be null or empty or have an incorrect format.");
        }
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
    public List<Skill> getSkill() {
        return skillList;
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", admissionDate=" + admissionDate +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", document=" + document +
                ", job=" + job +
                ", skillList=" + skillList +
                '}';
    }
    private boolean validatePhoneNumber(String value){
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(value).matches();
    }

    private boolean validateEmail(String value){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(value).matches();
    }

    private boolean validateNullDate(Date value) {
        return !(value == null);
    }

    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    /**
     * Clones the brand.
     *
     * @return A clone of the current brand instance.
     */
    public Collaborator clone() {

        Collaborator clone = new Collaborator(this.name,this.dateOfBirth,this.admissionDate,this.phoneNumber,this.email,this.address,this.document,this.job);

        for (Skill in : this.skillList) {

            clone.skillList.add(in.clone());
        }

        return clone;
    }
}
