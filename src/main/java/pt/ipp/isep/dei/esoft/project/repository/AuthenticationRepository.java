package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

/**
 * The AuthenticationRepository class provides methods for user authentication and authorization.
 * It interacts with the authentication facade to perform authentication and authorization operations.
 *
 * @author Group22
 */
public class AuthenticationRepository {

    private final AuthFacade authenticationFacade;

    /**
     * Constructs an AuthenticationRepository object.
     */
    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    /**
     * Performs user login with the specified email and password.
     *
     * @param email The email of the user.
     * @param pwd The password of the user.
     * @return True if the login is successful, false otherwise.
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Performs user logout.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Gets the current user session.
     *
     * @return The current user session.
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds a user role with the specified ID and description.
     *
     * @param id The ID of the user role.
     * @param description The description of the user role.
     * @return True if the user role is successfully added, false otherwise.
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a user with the specified name, email, password, and role ID.
     *
     * @param name The name of the user.
     * @param email The email of the user.
     * @param pwd The password of the user.
     * @param roleId The ID of the role assigned to the user.
     * @return True if the user is successfully added with the specified role, false otherwise.
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }
}
