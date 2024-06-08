package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

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
}
