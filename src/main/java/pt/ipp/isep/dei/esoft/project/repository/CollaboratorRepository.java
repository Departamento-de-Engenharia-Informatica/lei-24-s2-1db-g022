package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing Collaborator objects.
 */
public class CollaboratorRepository {
    private final List<ICollaborator> collaboratorList;

    /**
     * Constructor for the CollaboratorRepository class.
     * Initializes the list of collaborators.
     */
    public CollaboratorRepository() {
        collaboratorList = new ArrayList<>();
    }

    /**
     * Adds a collaborator to the bootstrap list.
     *
     * @param collaborator The collaborator to be added.
     */
    public void addCollaboratorBootstrap(ICollaborator collaborator) {
        collaboratorList.add(collaborator.clone());
    }

    /**
     * Registers a new collaborator.
     *
     * @param name          The name of the collaborator.
     * @param dateOfBirth   The date of birth of the collaborator.
     * @param admissionDate The admission date of the collaborator.
     * @param address       The address of the collaborator.
     * @param phoneNumber   The phone number of the collaborator.
     * @param email         The email address of the collaborator.
     * @param docType       The type of document of the collaborator.
     * @param number        The document number of the collaborator.
     * @param job           The job of the collaborator.
     * @return An Optional containing the registered collaborator if successful, empty otherwise.
     */
    public Optional<Collaborator> registerCollaborator(String name, Date dateOfBirth, Date admissionDate, Address address, String phoneNumber, String email, int taxpayer, String docType, int number, Job job) {
        Optional<Collaborator> optionalCollaborator = Optional.empty();

        Collaborator collaborator = new Collaborator(name, dateOfBirth, admissionDate, phoneNumber, email, address, taxpayer, docType, number, job);
        if (addCollaborator(collaborator)) {
            optionalCollaborator = Optional.of(collaborator);
        }

        return optionalCollaborator;
    }

    /**
     * Adds a collaborator to the repository if it passes the validation checks.
     *
     * @param collaborator The collaborator to add.
     * @return True if the collaborator was successfully added, false otherwise.
     */
    private boolean addCollaborator(Collaborator collaborator) {
        boolean success = false;
        if (validateCollaborator(collaborator)) {
            success = collaboratorList.add(collaborator.clone());
        }
        return success;
    }

    /**
     * Validates if a collaborator is not already present in the repository.
     *
     * @param collaborator The collaborator to validate.
     * @return True if the collaborator is not present, false otherwise.
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        for (ICollaborator collaborator1 : collaboratorList) {
            if (collaborator1 instanceof Collaborator) {
                if (collaborator1.hasEqualsEmail(collaborator) || collaborator1.hasEqualsPhoneNumber(collaborator) || collaborator1.hasEqualsTaxNumber(collaborator) || collaborator1.getDocument().duplicateNumber(collaborator.getDocument())) {
                    return false;
                }
            }
        }
        return !collaboratorList.contains(collaborator);
    }

    /**
     * Retrieves a list of collaborators by their skills.
     *
     * @param skillList The list of skills to filter collaborators by.
     * @return The list of collaborators matching the given skills.
     */
    public ArrayList<Collaborator> getCollaboratorsBySkills(List<Skill> skillList) {
        ArrayList<Collaborator> newCollaboratorList = new ArrayList<>();

        for (Skill newSkill : skillList) {
            for (ICollaborator collaborator : collaboratorList) {
                if(collaborator instanceof Collaborator){
                    if (((Collaborator) collaborator).hasCollaboratorSkill(newSkill) && !newCollaboratorList.contains((Collaborator) collaborator)) {
                        newCollaboratorList.add((Collaborator) collaborator);
                    }
                }
            }
        }

        return newCollaboratorList;
    }

    /**
     * Retrieves a collaborator by ID.
     *
     * @param collaboratorName The ID of the collaborator to retrieve.
     * @return An optional containing the collaborator with the specified ID, or empty if not found.
     */
    public Optional<Collaborator> getCollaboratorByName(String collaboratorName) {
        for (ICollaborator collaborator : collaboratorList) {
            if (collaborator.getName().equals(collaboratorName)) {
                return Optional.of((Collaborator) collaborator);
            }
        }
        return Optional.empty();
    }

    public Optional<ICollaborator> getCollaboratorByEmail(String email) {
        for (ICollaborator collaborator : collaboratorList) {
            if (collaborator.hasEmail(email)) {
                return Optional.of(collaborator);
            }

        }
        return Optional.empty();
    }
}

