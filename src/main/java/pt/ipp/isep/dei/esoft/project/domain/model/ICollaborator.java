package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.List;

/**
 * Represents a collaborator.
 *
 * @author Group22
 */
public interface ICollaborator {
    String getName();

    List<Skill> getSkillList();

    /**
     * Checks if the Collaborator has the same email as the given collaborator.
     *
     * @param collaborator The collaborator to compare emails with.
     * @return true if the Collaborator has the same email as the given collaborator, false otherwise.
     */
    boolean hasEqualsEmail(ICollaborator collaborator);

    /**
     * Checks if the GreenSpaceManager has the same phone number as the given collaborator.
     *
     * @param collaborator The collaborator to compare phone numbers with.
     * @return true if the GreenSpaceManager has the same phone number as the given collaborator, false otherwise.
     */
    boolean hasEqualsPhoneNumber(ICollaborator collaborator);

    /**
     * Checks if the Collaborator has the same tax number as the given collaborator.
     *
     * @param collaborator The collaborator to compare tax numbers with.
     * @return true if the Collaborator has the same tax number as the given collaborator, false otherwise.
     */
    boolean hasEqualsTaxNumber(ICollaborator collaborator);

    /**
     * Retrieves the document associated with this collaborator.
     *
     * @return The document associated with this collaborator.
     */
    Document getDocument();

    /**
     * Creates and returns a copy of this collaborator.
     *
     * @return A copy of this collaborator.
     */
    ICollaborator clone();

    /**
     * Checks if the Collaborator has the given email.
     *
     * @param email The email to check against the Collaborator's email.
     * @return true if the Collaborator has the given email, false otherwise.
     */
    boolean hasEmail(String email);

}
