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

/**
 * The ListGreenSpacesController class manages the listing the Green Spaces.
 *
 * @author Group22
 */
public class ListGreenSpacesController {

    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;

    /**
     * Constructs a ListGreenSpacesController object with a default AuthenticationRepository and CollaboratorRepository instances.
     */
    public ListGreenSpacesController() {

        getAuthenticationRepository();
        getCollaboratorRepository();
    }

    /**
     * Constructs a ListGreenSpacesController object with a specified AuthenticationRepository and CollaboratorRepository instances.
     * @param authenticationRepository The AuthenticationRepository instance to use.
     * @param collaboratorRepository The CollaboratorRepository instance to use.
     */
    public ListGreenSpacesController(AuthenticationRepository authenticationRepository, CollaboratorRepository collaboratorRepository) {

        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     * If not initialized, it gets the AuthenticationRepository from the Repositories singleton.
     * @return The AuthenticationRepository instance.
     */
    private AuthenticationRepository getAuthenticationRepository() {

        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }

        return authenticationRepository;
    }

    /**
     * Retrieves the CollaboratorRepository instance.
     * If not initialized, it gets the CollaboratorRepository from the Repositories singleton.
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
     * Retrieves a sorted list of GreenSpaceDto objects managed by the current GreenSpaceManager.
     *
     * @return A list of GreenSpaceDto objects sorted according to the implemented sorting logic.
     */
    public List<GreenSpaceDto> getGreenSpaceManagerGSpaceSorted() {

        List<GreenSpace> greenSpaceList = new ArrayList<>();

        GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

        if (collaboratorOptional.isPresent()) {
            GreenSpaceManager gsm = (GreenSpaceManager) collaboratorOptional.get();
            greenSpaceList = gsm.getListGreenSpaces().getSortedGreenspaces();
        }

        GreenSpaceMapper gsm = new GreenSpaceMapper();

        return gsm.toDTO(greenSpaceList);
    }

    /**
     * Retrieves the GreenSpaceManager associated with the current user session.
     *
     * @return The GreenSpaceManager object associated with the current user session.
     */
    private GreenSpaceManager getGSMFromSession() {

        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();

        return new GreenSpaceManager(email.getEmail());
    }
}
