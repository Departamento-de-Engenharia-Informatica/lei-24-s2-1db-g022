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
    private final List<Collaborator> collaboratorList;

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
    public void addCollaboratorBootstrap(Collaborator collaborator) {
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
    public Optional<Collaborator> registerCollaborator(String name, Date dateOfBirth, Date admissionDate, Address address, String phoneNumber, String email, String docType, int number, Job job) {
        Optional<Document> optionalDocument = Optional.empty();
        Optional<Collaborator> optionalCollaborator = Optional.empty();

        optionalDocument = registerDocument(docType, number);

        if (optionalDocument.isPresent()) {
            Collaborator collaborator = new Collaborator(name, dateOfBirth, admissionDate, phoneNumber, email, address, optionalDocument.get(), job);
            if (addCollaborator(collaborator)) {
                optionalCollaborator = Optional.of(collaborator);
            }
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
        return !collaboratorList.contains(collaborator);
    }

    /**
     * Registers a new document with the given document type and number.
     *
     * @param docType The type of the document.
     * @param number  The number of the document.
     * @return An Optional containing the newly registered document, or empty if the document creation fails.
     */
    private Optional<Document> registerDocument(String docType, int number) {
        Optional<Document> newDocument = Optional.empty();
        newDocument = Optional.of(new Document(docType, number));
        return newDocument;
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
            for (Collaborator collaborator : collaboratorList) {
                if (collaborator.hasCollaboratorSkill(newSkill) && !newCollaboratorList.contains(collaborator)) {
                    newCollaboratorList.add(collaborator);
                }
            }
        }

        return newCollaboratorList;
    }
}

