package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

/**
 * @author Group22
 */
public class Repositories {

    private static Repositories instance;
    private final AuthenticationRepository authenticationRepository;
    private final JobRepository jobRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;
    private final BrandRepository brandRepository;
    private final VehicleRepository vehicleRepository;
    private final AddressRepository addressRepository;
    private final TeamRepository teamRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final TaskRepository taskRepository;
    private final ToDoList toDoList;


    /**
     * Constructs a Repositories object with default repositories.
     */
    private Repositories() {

        authenticationRepository = new AuthenticationRepository();
        jobRepository = new JobRepository();
        skillRepository = new SkillRepository();
        brandRepository = new BrandRepository();
        vehicleRepository = new VehicleRepository();
        addressRepository = new AddressRepository();
        collaboratorRepository = new CollaboratorRepository();
        teamRepository = new TeamRepository();
        greenSpaceRepository = new GreenSpaceRepository();
        taskRepository = new TaskRepository();
        toDoList = new ToDoList();
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
     * Retrieves the team repository.
     *
     * @return the team repository
     */
    public TeamRepository getTeamRepository() {
        return teamRepository;
    }


    /**
     * Retrieves the skill repository.
     *
     * @return the skill repository
     */
    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    /**
     * Retrieves the brand repository.
     *
     * @return the brand repository
     */
    public BrandRepository getBrandRepository() {
        return brandRepository;
    }

    /**
     * Retrieves the vehicle repository.
     *
     * @return the vehicle repository
     */
    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    /**
     * Retrieves the collaborator repository.
     *
     * @return the collaborator repository
     */
    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    /**
     * Retrieves the address repository.
     *
     * @return the address repository
     */
    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    /**
     * Retrieves the task repository.
     *
     * @return the task repository
     */
    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    /**
     * Retrieves the To Do List.
     *
     * @return the To Do List
     */
    public ToDoList getToDoList() {
        return toDoList;
    }
}