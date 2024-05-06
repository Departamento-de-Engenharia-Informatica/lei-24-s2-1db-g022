package pt.ipp.isep.dei.esoft.project.repository;

/**
 * @author Group22
 */
public class Repositories {

    private static Repositories instance;
    private final AuthenticationRepository authenticationRepository;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;

    /**
     * Constructs a Repositories object with default repositories.
     */
    private Repositories() {

        authenticationRepository = new AuthenticationRepository();
        jobRepository = new JobRepository();
        skillRepository = new SkillRepository();
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

    /**
     * Retrieves the skill repository.
     *
     * @return the skill repository
     */
    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

}