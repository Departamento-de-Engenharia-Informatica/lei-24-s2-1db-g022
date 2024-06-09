package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskAgendaDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskAgendaMapper;
import pt.ipp.isep.dei.esoft.project.domain.model.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The ConsultAssignedTasksController class manages the retrieval of tasks assigned to collaborators.
 *
 * @author Group22
 */
public class ConsultAssignedTasksController {
    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository;
    private Agenda agenda;

    /**
     * The ConsultAssignedTasksController class manages the retrieval of tasks assigned to collaborators.
     */
    public ConsultAssignedTasksController() {
        getAuthenticationRepository();
        getCollaboratorRepository();
        getTeamRepository();
        getAgenda();

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
     * Retrieves the CollaboratorRepository instance.
     * If not initialized, it gets the CollaboratorRepository from the Repositories singleton.
     *
     * @return The CollaboratorRepository instance.
     */
    private TeamRepository getTeamRepository() {

        if (teamRepository == null) {

            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }

        return teamRepository;
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
     * Retrieves a list of tasks assigned to the collaborator between two dates with a specified status.
     *
     * @param startDate    the start date of the period to filter tasks
     * @param endDate      the end date of the period to filter tasks
     * @param filterStatus the status to filter tasks
     * @return a list of TaskAgendaDto objects matching the specified criteria
     */
    public List<TaskAgendaDto> getTasksBetweenTwoDates(Date startDate, Date endDate, String filterStatus) {
        List<TaskAgenda> sortTaskListTeam = new ArrayList<>();

        Collaborator collaboratorEmail = getGSMFromSession();
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(collaboratorEmail.getEmail());

        if (collaboratorOptional.isPresent()) {
            Collaborator collaborator = (Collaborator) collaboratorOptional.get();

            Optional<Team> teamOptional = getTeamRepository().getTeambyCollaborator(collaborator);

            if (teamOptional.isPresent()) {
                List<TaskAgenda> taskListTeam = getAgenda().getTaskListByTeam(teamOptional.get());

                List<TaskAgenda> filteredTaskListTeam = getAgenda().filterTasksByStatusDate(taskListTeam, filterStatus, startDate, endDate);

                if(!filteredTaskListTeam.isEmpty()){
                    sortTaskListTeam = getAgenda().sortTaskAgendaDate(filteredTaskListTeam);
                }
            }
        }


        TaskAgendaMapper mapper = new TaskAgendaMapper();

        return mapper.toDTOTaskToDoList(sortTaskListTeam);
    }

    /**
     * Retrieves the GreenSpaceManager associated with the current user session.
     *
     * @return The GreenSpaceManager object associated with the current user session.
     */
    private Collaborator getGSMFromSession() {

        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();

        return new Collaborator(email.getEmail());
    }
}
