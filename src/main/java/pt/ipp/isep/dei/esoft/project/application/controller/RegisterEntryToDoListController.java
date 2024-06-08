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

public class RegisterEntryToDoListController {

    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private TaskRepository taskRepository;
    private ToDoList toDoList;

    public RegisterEntryToDoListController() {

        getAuthenticationRepository();
        getCollaboratorRepository();
        getGreenSpaceRepository();
        getTaskRepository();
        getToDoList();
    }

    public RegisterEntryToDoListController(AuthenticationRepository authenticationRepository, CollaboratorRepository collaboratorRepository, GreenSpaceRepository greenSpaceRepository, TaskRepository taskRepository, ToDoList toDoList) {

        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.greenSpaceRepository = greenSpaceRepository;
        this.taskRepository = taskRepository;
        this.toDoList = toDoList;
    }

    private AuthenticationRepository getAuthenticationRepository() {

        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }

        return authenticationRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {

        if (collaboratorRepository == null) {

            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }

        return collaboratorRepository;
    }

    private GreenSpaceRepository getGreenSpaceRepository() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
        return greenSpaceRepository;
    }

    private TaskRepository getTaskRepository() {

        if (taskRepository == null) {

            Repositories repositories = Repositories.getInstance();
            taskRepository = repositories.getTaskRepository();
        }

        return taskRepository;
    }

    private ToDoList getToDoList() {

        if (toDoList == null) {

            Repositories repositories = Repositories.getInstance();
            toDoList = repositories.getToDoList();
        }

        return toDoList;
    }

    public List<GreenSpaceDto> getGreenSpaceManagerGSpace() {

        List<GreenSpace> greenSpaceList = new ArrayList<>();

        GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

        if (collaboratorOptional.isPresent()) {
            GreenSpaceManager gsm = (GreenSpaceManager) collaboratorOptional.get();
            greenSpaceList = gsm.getGreenSpaces().getGreenSpaceList();
        }

        GreenSpaceMapper gsm = new GreenSpaceMapper();

        return gsm.toDTO(greenSpaceList);
    }

    public List<TaskDto> getAllTasks() {

        List<Task> taskList = getTaskRepository().getAllTaskDescriptions();

        TaskMapper tmp = new TaskMapper();

        return tmp.toDTO(taskList);
    }

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

    private GreenSpaceManager getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GreenSpaceManager(email.getEmail());
    }
}
