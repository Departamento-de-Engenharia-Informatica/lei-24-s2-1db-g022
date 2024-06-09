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

/**
 * Controller for Register GreenSpace.
 *
 * @author Group22
 */
public class RegisterGreenSpaceController {

    private GreenSpaceRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;


    /**
     * Constructs an instance of RegisterGreenSpaceController.
     * Repository instances are obtained from the Repositories class.
     */
    public RegisterGreenSpaceController() {
        getGreenSpaceRepository();
        getAuthenticationRepository();
        getCollaboratorRepository();
    }

    /**
     * Constructs an instance of RegisterGreenSpaceController.
     * Allows receiving the repositories as parameters for testing purposes.
     *
     * @param greenSpaceRepository     The repository for green spaces.
     * @param authenticationRepository The repository for authentication.
     */
    public RegisterGreenSpaceController(GreenSpaceRepository greenSpaceRepository, AuthenticationRepository authenticationRepository,CollaboratorRepository collaboratorRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    /**
     * Retrieves the greenSpace repository instance.
     * If the repository is not initialized, it initializes it first.
     *
     * @return The greenSpace repository instance.
     */
    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    /**
     * Retrieves the collaborator repository instance.
     * If the repository is not initialized, it initializes it first.
     *
     * @return The collaborator repository instance.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Retrieves the collaborator repository instance.
     * If the repository is not initialized, it initializes it first.
     *
     * @return The collaborator repository instance.
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Registers a new GreenSpace with the given details and associates it with the GreenSpaceManager
     * from the session if the collaborator exists.
     *
     * @param greenSpaceType the type of the green space
     * @param greenSpaceName the name of the green space
     * @param area           the area of the green space
     * @param streetName     the street name where the green space is located
     * @param doorNumber     the door number where the green space is located
     * @param postCodeNumber the post code number of the green space's location
     * @param localization   the localization of the green space
     * @return an Optional containing the registered GreenSpace if successful, or an empty Optional otherwise
     */
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
            return greenSpaceOptional;
        }

        return Optional.empty();
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
