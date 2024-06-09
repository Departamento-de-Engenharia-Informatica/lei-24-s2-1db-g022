package pt.ipp.isep.dei.esoft.project.domain.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * The Team class represents a team of collaborators.
 * It contains a list of collaborators who are part of the team.
 *
 * @author Group22
 */
public class Team {

    private final List<Collaborator> collaboratorList;
    private int id;

    /**
     * Constructs a Team object with the specified list of collaborators.
     *
     * @param collaboratorList The list of collaborators who are part of the team.
     */
    public Team(List<Collaborator> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }

    /**
     * Generates the ID for the object.
     *
     * @param id The ID to be assigned to the object.
     */
    public void generateId(int id) {

        this.id = id;
    }

    /**
     * Retrieves the ID of the object.
     *
     * @return The ID of the object.
     */
    public int getId() {

        return id;
    }


    /**
     * Gets the list of collaborators in the team.
     *
     * @return The list of collaborators in the team.
     */
    public List<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    /**
     * Returns a string representation of the team.
     *
     * @return A string representation of the team.
     */
    @Override
    public String toString() {
        return "Team{" +
                "collaboratorList=" + collaboratorList +
                ", id=" + id +
                '}';
    }

    /**
     * Checks if the provided collaborator is in the list of collaborators.
     *
     * @param collaborator The collaborator to check.
     * @return True if the collaborator is in the list, otherwise false.
     */
    public boolean collaboratorHasTeam(Collaborator collaborator) {
        return (collaboratorList.contains(collaborator));
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(collaboratorList, team.collaboratorList);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by {@link HashMap}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(collaboratorList);
    }

    /**
     * Creates and returns a copy of this team. The copy contains the same collaborators as the original team.
     *
     * @return A new Team object that is a copy of this team.
     */
    public Team clone() {

        Team clone = new Team(this.collaboratorList);

        return clone;
    }

    /**
     * Verifies if the given team ID matches the ID of this team.
     *
     * @param teamId The team ID to verify against.
     * @return true if the given team ID matches the ID of this team, otherwise false.
     */
    public boolean verifyIdTeam(int teamId) {

        return this.id == teamId;
    }

    /**
     * Sends an email message to all collaborators in the team.
     *
     * @param msg The message content to be sent via email.
     * @return true if the email was successfully sent to all collaborators, otherwise false.
     * @throws RuntimeException If there is an error reading the email configuration file or instantiating the EmailService implementation.
     */
    public boolean sendEmail(String msg) {
        for (Collaborator collaborator : collaboratorList) {
            String email = collaborator.getEmail();

            Properties properties = new Properties();

            try {
                InputStream in = new FileInputStream("src\\main\\resources\\config.properties");

                properties.load(in);
                in.close();

                String classPath = properties.getProperty("email.service");
                Class<?> clazz = Class.forName(classPath);
                EmailService es = (EmailService) clazz.newInstance();


                boolean success = es.sendEmail(email, msg,2000);
                if (!success) {
                    return false;
                }

            } catch (Exception e) {

                throw new RuntimeException(e);
            }

        }
        return true;
    }
}
