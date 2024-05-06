package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

/**
 * @author Group22
 */
public class Repositories {

    private static Repositories instance;
    private final AuthenticationRepository authenticationRepository;
    private final JobRepository jobRepository;

    /**
     * Constructs a Repositories object with default repositories.
     */
    private Repositories() {

        authenticationRepository = new AuthenticationRepository();
        jobRepository = new JobRepository();
    }

    /**
     * Retrieves the singleton instance of Repositories.
     *
     * @return the singleton instance of Repositories
     */
    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    /**
     * Retrieves the authentication repository.
     *
     * @return the authentication repository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Retrieves the job repository.
     *
     * @return the job repository
     */
    public JobRepository getJobRepository() {
        return jobRepository;
    }
}