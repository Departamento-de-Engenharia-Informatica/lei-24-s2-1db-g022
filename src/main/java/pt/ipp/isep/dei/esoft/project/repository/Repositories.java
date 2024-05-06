package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

/**
 * @author Group22
 */
public class Repositories {

    private static Repositories instance;
    private final AuthenticationRepository authenticationRepository;

    private Repositories() {

        authenticationRepository = new AuthenticationRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }
}