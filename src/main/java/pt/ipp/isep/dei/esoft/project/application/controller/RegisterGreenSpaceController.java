package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpaceManager;
import pt.ipp.isep.dei.esoft.project.domain.model.ICollaborator;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

public class RegisterGreenSpaceController {

    private GreenSpaceRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;


    //Repository instances are obtained from the Repositories class
    public RegisterGreenSpaceController() {
        getGreenSpaceRepository();
        getAuthenticationRepository();
        getCollaboratorRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public RegisterGreenSpaceController(GreenSpaceRepository greenSpaceRepository, AuthenticationRepository authenticationRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = new CollaboratorRepository();
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    public Optional<GreenSpace> registerGreenSpace(String greenSpaceType, String greenSpaceName, int area, String streetName, int doorNumber, String postCodeNumber, String localization) {
        Optional<GreenSpace> greenSpaceOptional = Optional.empty();

        GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

        if (collaboratorOptional.isPresent()) {
            greenSpaceOptional = getGreenSpaceRepository().registerGreenSpace(greenSpaceType, greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);
        }
        boolean addListSucess = false;

        if (greenSpaceOptional.isPresent()) {
            GreenSpaceManager gsm = (GreenSpaceManager) collaboratorOptional.get();
            addListSucess = gsm.addListGreenSpace(greenSpaceOptional.get());
        }

        if (addListSucess) {
            getGreenSpaceRepository().ver();
            return greenSpaceOptional;
        }

        return Optional.empty();
    }

    private GreenSpaceManager getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GreenSpaceManager(email.getEmail());
    }
}
