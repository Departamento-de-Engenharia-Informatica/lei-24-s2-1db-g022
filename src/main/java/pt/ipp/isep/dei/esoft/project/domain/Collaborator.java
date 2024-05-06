package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;

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
    private int phoneNumber;
    private String email;
    private int cc;
    private int cardNumber;
    private Address address;
    private Job job;
    private List<Skill> skillList;


    /**
     * Constructs a Collaborator object with default values.
     */
    public Collaborator() {
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
    public int getPhoneNumber() {
        return phoneNumber;
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
     * Gets the identification card number of the collaborator.
     *
     * @return The identification card number of the collaborator.
     */
    public int getCc() {
        return cc;
    }

    /**
     * Gets the card number of the collaborator.
     *
     * @return The card number of the collaborator.
     */
    public int getCardNumber() {
        return cardNumber;
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

    /**
     * Returns a string representation of the collaborator.
     *
     * @return A string representation of the collaborator.
     */
    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", admissionDate=" + admissionDate +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", cc=" + cc +
                ", cardNumber=" + cardNumber +
                ", address=" + address +
                ", job=" + job +
                ", skill=" + skillList +
                '}';
    }
}
