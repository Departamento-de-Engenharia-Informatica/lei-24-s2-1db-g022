package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.model.Address;
import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.model.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for registering new collaborators.
 *
 * @author Group22
 */
public class RegisterCollaboratorController {
    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;
    private AddressRepository addressRepository;

    /**
     * Constructs a RegisterCollaboratorController object with a default CollaboratorRepository, JobRepository and AddressRepository instances.
     */
    public RegisterCollaboratorController() {
        getCollaboratorRepository();
        getJobRepository();
        getAddressRepository();
    }

    /**
     * Constructs a RegisterCollaboratorController object with a specified CollaboratorRepository instance.
     *
     * @param collaboratorRepository The CollaboratorRepository instance to use.
     */
    public RegisterCollaboratorController(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    /**
     * Retrieves the AddressRepository instance.
     * If not initialized, it gets the AddressRepository from the Repositories singleton.
     *
     * @return The AddressRepository instance.
     */
    private AddressRepository getAddressRepository() {
        if (addressRepository == null) {
            Repositories repositories = Repositories.getInstance();
            addressRepository = repositories.getAddressRepository();
        }
        return addressRepository;
    }

    /**
     * Retrieves the CollaboratorRepository instance.
     * If not initialized, it gets the CollaboratorRepository from the Repositories singleton.
     *
     * @return The CollaboratorRepository instance.
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Retrieves the JobRepository instance.
     * If not initialized, it gets the JobRepository from the Repositories singleton.
     *
     * @return The JobRepository instance.
     */
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    /**
     * Registers a new collaborator with the provided details.
     *
     * @param name          The name of the collaborator.
     * @param jobName       The name of the job/position of the collaborator.
     * @param dateOfBirth   The date of birth of the collaborator.
     * @param admissionDate The date of admission/joining of the collaborator.
     * @param streetName    The street name of the collaborator's address.
     * @param postCode      The postal code of the collaborator's address.
     * @param doorNumber    The door number of the collaborator's address.
     * @param phoneNumber   The phone number of the collaborator.
     * @param email         The email address of the collaborator.
     * @param taxpayer      The taxpayer number of the collaborator.
     * @param docType       The type of document (e.g., Passport or Citizen Card) of the collaborator.
     * @param number        The document number of the collaborator.
     * @return An Optional containing the newly registered collaborator if the registration is successful,
     * otherwise an empty Optional.
     */
    public Optional<Collaborator> registerCollaborator(String name, String jobName, Date dateOfBirth, Date admissionDate, String streetName, String postCode, int doorNumber, String phoneNumber, String email, int taxpayer, String docType, int number) {
        Optional<Address> newAddress;

        Optional<Collaborator> newCollaborator = Optional.empty();

        Job job = getJobByName(jobName);

        newAddress = registerAddress(streetName, doorNumber, postCode);

        if (newAddress.isPresent()) {
            newCollaborator = getCollaboratorRepository().registerCollaborator(name, dateOfBirth, admissionDate, newAddress.get(), phoneNumber, email, taxpayer, docType, number, job);
        }

        if (newCollaborator.isPresent()) {
            AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

            authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);
            authenticationRepository.addUserWithRole(newCollaborator.get().getName(), newCollaborator.get().getEmail(), "col", AuthenticationController.ROLE_COLLABORATOR);
        }

        return newCollaborator;
    }

    /**
     * Registers a new address with the provided details.
     *
     * @param streetName The street name of the address.
     * @param doorNumber The door number of the address.
     * @param codPost    The postal code of the address.
     * @return An Optional containing the newly registered address if the registration is successful, otherwise an empty Optional.
     */
    private Optional<Address> registerAddress(String streetName, int doorNumber, String codPost) {
        Optional<Address> newAddress;
        newAddress = getAddressRepository().registerAddress(streetName, codPost, doorNumber);
        return newAddress;
    }

    /**
     * Retrieves the list of all available jobs from the job repository.
     *
     * @return The list of all available jobs.
     */
    public List<Job> getJobList() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobList();
    }

    /**
     * Retrieves the job with the specified name from the job repository.
     *
     * @param jobName The name of the job to retrieve.
     * @return The job with the specified name, or null if not found.
     */

    private Job getJobByName(String jobName) {
        Job jobByName;
        jobByName = getJobRepository().getJobByName(jobName);
        return jobByName;
    }

}
