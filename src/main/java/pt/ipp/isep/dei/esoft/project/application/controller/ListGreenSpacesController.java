package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpaceManager;
import pt.ipp.isep.dei.esoft.project.domain.model.ICollaborator;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListGreenSpacesController {

    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;

    public ListGreenSpacesController() {

        getAuthenticationRepository();
        getCollaboratorRepository();
    }

    public ListGreenSpacesController(AuthenticationRepository authenticationRepository, CollaboratorRepository collaboratorRepository) {

        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {

        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

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

    public List<GreenSpaceDto> getGreenSpaceManagerGSpaceSorted() {

        List<GreenSpace> greenSpaceList = new ArrayList<>();

        GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

        if (collaboratorOptional.isPresent()) {
            GreenSpaceManager gsm = (GreenSpaceManager) collaboratorOptional.get();
            greenSpaceList = gsm.getGreenSpaces().getSortedGreenspaces();
        }

        GreenSpaceMapper gsm = new GreenSpaceMapper();

        return gsm.toDTO(greenSpaceList);
    }

    private GreenSpaceManager getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GreenSpaceManager(email.getEmail());
    }
}
