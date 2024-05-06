package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;

/**
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


    public Collaborator() {
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getCc() {
        return cc;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Job getJob() {
        return job;
    }

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
                ", cc=" + cc +
                ", cardNumber=" + cardNumber +
                ", address=" + address +
                ", job=" + job +
                ", skill=" + skillList +
                '}';
    }
}
