package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskToDoListDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskToDoListMapper;
import pt.ipp.isep.dei.esoft.project.domain.model.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for adding a new entry to the agenda.
 *
 * @author Group22
 */
public class AddNewEntryToAgendaController {
    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private ToDoList toDoList;
    private Agenda agenda;

    /**
     * Constructor to initialize repositories and agenda-related objects.
     */
    public AddNewEntryToAgendaController() {
        getAuthenticationRepository();
        getCollaboratorRepository();
        getGreenSpaceRepository();
        getToDoList();
        getAgenda();

    }

    /**
     * Constructs an instance of AddNewEntryToAgendaController.
     *
     * @param authenticationRepository The repository for authentication.
     * @param collaboratorRepository   The repository for collaborators.
     * @param greenSpaceRepository     The repository for green spaces.
     * @param toDoList                 The toDoList instance.
     * @param agenda                   The agenda instance.
     */
    public AddNewEntryToAgendaController(AuthenticationRepository authenticationRepository, CollaboratorRepository collaboratorRepository, GreenSpaceRepository greenSpaceRepository, ToDoList toDoList, Agenda agenda) {
        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.toDoList = toDoList;
        this.agenda = agenda;
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     * If not initialized, it gets the AuthenticationRepository from the Repositories singleton.
     *
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
     *
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
     *
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
     * Retrieves the ToDoList instance.
     * If not initialized, it gets the ToDoList from the Repositories singleton.
     *
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
     * Retrieves the Agenda instance.
     * If not initialized, it gets the Agenda from the Repositories singleton.
     *
     * @return The Agenda instance.
     */
    private Agenda getAgenda() {

        if (agenda == null) {

            Repositories repositories = Repositories.getInstance();
            agenda = repositories.getAgenda();
        }

        return agenda;
    }

    /**
     * Retrieves the list of green spaces managed by a green space manager.
     *
     * @return A list of GreenSpaceDto objects representing the managed green spaces.
     */
    public List<GreenSpaceDto> getGreenSpaceManager() {
        List<GreenSpace> greenSpaceList = new ArrayList<>();

        GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

        if (collaboratorOptional.isPresent()) {
            GreenSpaceManager gsm = (GreenSpaceManager) collaboratorOptional.get();
            greenSpaceList = gsm.getListGreenSpaces().getGreenSpaceList();
        }
        GreenSpaceMapper mapper = new GreenSpaceMapper();

        return mapper.toDTO(greenSpaceList);
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

    /**
     * Retrieves the list of tasks for a given green space.
     *
     * @param greenSpaceName The name of the green space.
     * @return A list of TaskToDoListDto objects representing the tasks for the specified green space.
     */
    public List<TaskToDoListDto> getTaskToDoList(String greenSpaceName) {
        List<TaskToDoListDto> taskToDoListDtoList = new ArrayList<>();

        Optional<GreenSpace> greenSpaceOptional = getGreenSpaceRepository().getGreenSpaceByName(greenSpaceName);

        if (greenSpaceOptional.isPresent()) {
            List<TaskToDoList> taskListGreenSpace = getToDoList().getTaskByGreenSpace(greenSpaceOptional.get());

            if (!taskListGreenSpace.isEmpty()) {
                TaskToDoListMapper mapper = new TaskToDoListMapper();
                taskToDoListDtoList = mapper.toDTOTaskToDoList(taskListGreenSpace);
            }

        }

        return taskToDoListDtoList;
    }

    /**
     * Adds a new entry to the agenda.
     *
     * @param taskToDoListReference The reference of the task to be added.
     * @param startDate             The start date of the entry.
     * @param expectedDuration      The expected duration of the task.
     * @return An Optional containing the added TaskAgenda if successful, empty otherwise.
     */
    public Optional<TaskAgenda> addNewEntryToAgenda(String taskToDoListReference, Date startDate, int expectedDuration) {
        Optional<TaskAgenda> optionalValue = Optional.empty();

        Optional<TaskToDoList> taskToDoListOptional = getToDoList().getTaskToDoListByReference(taskToDoListReference);

        if (taskToDoListOptional.isPresent()) {
            optionalValue = getAgenda().addNewEntryToAgenda(taskToDoListOptional.get(), startDate, expectedDuration);

            if (optionalValue.isPresent()) {
                getToDoList().removeOldEntryToDoList(taskToDoListOptional.get());
            }
        }

        return optionalValue;
    }
}
