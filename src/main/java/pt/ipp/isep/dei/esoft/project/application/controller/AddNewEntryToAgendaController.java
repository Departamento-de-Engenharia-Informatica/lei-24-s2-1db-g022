package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskToDoListDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskToDoListMapper;
import pt.ipp.isep.dei.esoft.project.domain.model.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AddNewEntryToAgendaController {
    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    private GreenSpaceRepository greenSpaceRepository;
    private ToDoList toDoList;
    private Agenda agenda;

    public AddNewEntryToAgendaController() {
        getAuthenticationRepository();
        getCollaboratorRepository();
        getGreenSpaceRepository();
        getToDoList();
        getAgenda();

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

    private ToDoList getToDoList() {

        if (toDoList == null) {

            Repositories repositories = Repositories.getInstance();
            toDoList = repositories.getToDoList();
        }

        return toDoList;
    }

    private Agenda getAgenda() {

        if (agenda == null) {

            Repositories repositories = Repositories.getInstance();
            agenda = repositories.getAgenda();
        }

        return agenda;
    }

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

    private GreenSpaceManager getGSMFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new GreenSpaceManager(email.getEmail());
    }

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

    public Optional<TaskAgenda> addNewEntryToAgenda(String taskToDoListReference, Date startDate, Date endDate, int expectedDuration) {
        Optional<TaskAgenda> optionalValue = Optional.empty();

        Optional<TaskToDoList> taskToDoListOptional = getToDoList().getTaskToDoListByReference(taskToDoListReference);

        if (taskToDoListOptional.isPresent()) {
            optionalValue = getAgenda().addNewEntryToAgenda(taskToDoListOptional.get(), startDate, endDate, expectedDuration);

            if (optionalValue.isPresent()) {
                getToDoList().removeOldEntryToDoList(taskToDoListOptional.get());
                System.out.println("-------------Agenda-------------");
                getAgenda().ver();
                System.out.println("-------------ToDoList-------------");
                getToDoList().ver();
            }
        }

        return optionalValue;
    }
}
