package pt.ipp.isep.dei.esoft.project_exemplo.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project_exemplo.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project_exemplo.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class AuthenticationController {

    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    //private final ApplicationSession applicationSession;
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    public void doLogout() {
        authenticationRepository.doLogout();
    }
}