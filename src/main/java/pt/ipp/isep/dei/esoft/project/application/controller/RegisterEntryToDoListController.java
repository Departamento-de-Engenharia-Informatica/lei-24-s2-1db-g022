package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskMapper;
import pt.ipp.isep.dei.esoft.project.domain.model.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The RegisterEntryToDoListController class manages the registration of entries in the To do List.
 *
 * @author Group22
 */
public class RegisterEntryToDoListController {

    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private TaskRepository taskRepository;
    private ToDoList toDoList;

    /**
     * Constructs a ListGreenSpacesController object with a default AuthenticationRepository, CollaboratorRepository, GreenSpaceRepository, TaskRepository and ToDoList instances.
     */
    public RegisterEntryToDoListController() {

        getAuthenticationRepository();
        getCollaboratorRepository();
        getGreenSpaceRepository();
        getTaskRepository();
        getToDoList();
    }

    /**
     * Constructs a ListGreenSpacesController object with a specified AuthenticationRepository, CollaboratorRepository, GreenSpaceRepository, TaskRepository and ToDoList instances.
     * @param authenticationRepository The AuthenticationRepository instance to use.
     * @param collaboratorRepository The CollaboratorRepository instance to use.
     * @param greenSpaceRepository The GreenSpaceRepository instance to use.
     * @param taskRepository The TaskRepository instance to use.
     * @param toDoList The ToDoList instance to use.
     */
    public RegisterEntryToDoListController(AuthenticationRepository authenticationRepository, CollaboratorRepository collaboratorRepository, GreenSpaceRepository greenSpaceRepository, TaskRepository taskRepository, ToDoList toDoList) {

        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.taskRepository = taskRepository;
        this.toDoList = toDoList;
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
     * Retrieves the GreenSpaceRepository instance.
     * If not initialized, it gets the GreenSpaceRepository from the Repositories singleton.
     * @return The GreenSpaceRepository instance.
     */
    private GreenSpaceRepository getGreenSpaceRepository() {

        if (greenSpaceRepository == null) {

            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }

        return greenSpaceRepository;
    }

    /**
     * Retrieves the TaskRepository instance.
     * If not initialized, it gets the TaskRepository from the Repositories singleton.
     * @return The TaskRepository instance.
     */
    private TaskRepository getTaskRepository() {

        if (taskRepository == null) {

            Repositories repositories = Repositories.getInstance();
            taskRepository = repositories.getTaskRepository();
        }

        return taskRepository;
    }

    /**
     * Retrieves the ToDoList instance.
     * If not initialized, it gets the ToDoList from the Repositories singleton.
     * @return The ToDoList instance.
     */
    private ToDoList getToDoList() {

        if (toDoList == null) {

            Repositories repositories = Repositories.getInstance();
            toDoList = repositories.getToDoList();
        }

        return toDoList;
    }

    /**
     * Retrieves a list of GreenSpaceDto objects managed by the current GreenSpaceManager.
     *
     * @return A list of GreenSpaceDto objects representing the GreenSpaces managed by the GreenSpaceManager.
     */
    public List<GreenSpaceDto> getGreenSpaceManagerGSpace() {

        List<GreenSpace> greenSpaceList = new ArrayList<>();

        GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

        if (collaboratorOptional.isPresent()) {
            GreenSpaceManager gsm = (GreenSpaceManager) collaboratorOptional.get();
            greenSpaceList = gsm.getListGreenSpaces().getGreenSpaceList();
        }

        GreenSpaceMapper gsm = new GreenSpaceMapper();

        return gsm.toDTO(greenSpaceList);
    }

    /**
     * Retrieves a list of TaskDto objects representing all tasks available in the system.
     *
     * @return A list of TaskDto objects containing information about each task.
     */
    public List<TaskDto> getAllTasks() {

        List<Task> taskList = getTaskRepository().getAllTaskDescriptions();

        TaskMapper tmp = new TaskMapper();

        return tmp.toDTO(taskList);
    }

    /**
     * Registers a new entry in the to-do list for a task to be performed in a specific green space.
     *
     * @param greenSpaceName          The name of the green space where the task is to be performed.
     * @param taskDescription         The description of the task to be registered.
     * @param urgency                 The urgency level of the task.
     * @param aproxExpectedDuration   The approximate expected duration of the task.
     * @return An Optional containing the registered TaskToDoList object if the task and green space exist, else empty.
     */
    public Optional<TaskToDoList> registerEntryToDoList(String greenSpaceName, String taskDescription, String urgency, int aproxExpectedDuration) {

        Optional<Task> task = Optional.empty();
        Optional<GreenSpace> greenSpace = Optional.empty();
        Optional<TaskToDoList> taskTdl = Optional.empty();

        task = getTaskRepository().getTaskByDescription(taskDescription);
        greenSpace = getGreenSpaceRepository().getGreenSpaceByName(greenSpaceName);

        if (task.isPresent() && greenSpace.isPresent()) {

            taskTdl = getToDoList().registerEntryToDoList(greenSpace.get(), task.get(), urgency, aproxExpectedDuration);
        }

        getToDoList().ver();
        return taskTdl;
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
