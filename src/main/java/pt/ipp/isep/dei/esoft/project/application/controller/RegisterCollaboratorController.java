package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RegisterCollaboratorController {
    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;
    private AddressRepository addressRepository;

    public RegisterCollaboratorController(){
        getCollaboratorRepository();
        getJobRepository();
        getAddressRepository();
    }


    public RegisterCollaboratorController(CollaboratorRepository collaboratorRepository){
        this.collaboratorRepository=collaboratorRepository;
    }

    private AddressRepository getAddressRepository(){
        if(addressRepository == null){
            Repositories repositories = Repositories.getInstance();
            addressRepository = repositories.getAddressRepository();
        }
        return addressRepository;
    }

    private CollaboratorRepository getCollaboratorRepository(){
        if(collaboratorRepository == null){
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private JobRepository getJobRepository(){
        if(jobRepository == null){
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }


    public Optional<Collaborator> registerCollaborator(String name, String jobName, Date dateOfBirth, Date admissionDate, String streetName, String postCode, int doorNumber, String phoneNumber, String email, int taxpayer,String docType, int number){
        Optional<Address> newAddress;

        Optional<Collaborator> newCollaborator = Optional.empty();

        Job job = getJobByName(jobName);

        newAddress = registerAddress(streetName,doorNumber,postCode);

        if(newAddress.isPresent()){
            newCollaborator = getCollaboratorRepository().registerCollaborator(name,dateOfBirth,admissionDate,newAddress.get(),phoneNumber,email,taxpayer,docType,number,job);
        }

        if(newCollaborator.isPresent()){
            AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

            authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);
            authenticationRepository.addUserWithRole(newCollaborator.get().getName(), newCollaborator.get().getEmail(), "col", AuthenticationController.ROLE_COLLABORATOR);
        }

        return newCollaborator;
    }

    private Optional<Address> registerAddress(String streetName, int doorNumber, String codPost ){
        Optional<Address> newAddress;
        newAddress = getAddressRepository().registerAddress(streetName,codPost,doorNumber);
        return newAddress;
    }

    //return the list of Job List
    public List<Job> getJobList() {
        JobRepository jobRepository = getJobRepository();
        return jobRepository.getJobList();
    }

    private Job getJobByName(String jobName){
        Job jobByName;
        jobByName = getJobRepository().getJobByName(jobName);
        return jobByName;
    }

}
