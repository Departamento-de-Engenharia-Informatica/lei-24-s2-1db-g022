package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskAgendaDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskAgendaMapper;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TeamDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TeamMapper;
import pt.ipp.isep.dei.esoft.project.domain.model.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for assigning teams to task agendas.
 *
 * @author Group22
 *
 */
public class AssignTeamAgendaController {

    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository;
    private Agenda agenda;

    /**
     * Constructs an instance of AssignTeamAgendaController.
     *
     * This constructor initializes the controller without explicitly setting repository and agenda instances.
     * It appears to only invoke getter methods for these instances without utilizing their returned values.
     * This might not be the intended behavior.
     */
    public AssignTeamAgendaController() {
        getAuthenticationRepository();
        getCollaboratorRepository();
        getTeamRepository();
        getAgenda();
    }

    /**
     * Constructs an instance of AssignTeamAgendaController.
     *
     * @param authenticationRepository The repository for authentication.
     * @param collaboratorRepository The repository for collaborators.
     * @param teamRepository The repository for teams.
     * @param agenda The agenda instance.
     */
    public AssignTeamAgendaController(AuthenticationRepository authenticationRepository, CollaboratorRepository collaboratorRepository, TeamRepository teamRepository, Agenda agenda) {
        this.authenticationRepository = authenticationRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.teamRepository = teamRepository;
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
     * Retrieves the TeamRepository instance.
     * If not initialized, it gets the TeamRepository from the Repositories singleton.
     *
     * @return The TeamRepository instance.
     */
    private TeamRepository getTeamRepository() {

        if (teamRepository == null) {

            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }

        return teamRepository;
    }

    /**
     * Retrieves all teams.
     *
     * @return A list of DTOs representing the teams.
     */
    public List<TeamDto> getAllTeams() {
        // Retrieve the list of teams from the repository
        List<Team> teams = getTeamRepository().getTeamList();

        // Map Team objects to DTOs using TeamMapper
        TeamMapper mapper = new TeamMapper();
        return mapper.toDTO(teams);
    }


    /**
     * Retrieves all task agendas associated with the green spaces managed by the currently logged-in green space manager.
     *
     * @return A list of DTOs representing the task agendas.
     */
    public List<TaskAgendaDto> getAllTaskByGSM() {
        List<TaskAgenda> listTaskAgenda = new ArrayList<>();

        // Retrieve the GreenSpaceManager from the session
        GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();

        // Retrieve the collaborator from the repository using the GreenSpaceManager's email
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

        if (collaboratorOptional.isPresent()) {
            // Convert the collaborator to GreenSpaceManager
            GreenSpaceManager greenSpaceManager = (GreenSpaceManager) collaboratorOptional.get();

            // Retrieve the list of green spaces managed by the GreenSpaceManager
            List<GreenSpace> greenSpaceList = getListGreenSpace(greenSpaceManager);

            if (!greenSpaceList.isEmpty()) {
                // Retrieve all task agendas associated with the green spaces
                listTaskAgenda = getAllTaskByGreenSpace(greenSpaceList);
            }
        }

        // Map TaskAgenda objects to DTOs using TaskAgendaMapper
        TaskAgendaMapper mapper = new TaskAgendaMapper();
        return mapper.toDTOTaskToDoList(listTaskAgenda);
    }

    /**
     * Retrieves all tasks associated with the specified list of green spaces.
     *
     * @param greenSpaceList The list of green spaces for which tasks will be retrieved.
     * @return A list of task agendas associated with the provided green spaces.
     */
    private List<TaskAgenda> getAllTaskByGreenSpace(List<GreenSpace> greenSpaceList) {

        return getAgenda().getAllTaskByGreenSpace(greenSpaceList);
    }

    /**
     * Retrieves a list of green spaces from the specified green space manager.
     *
     * @param greenSpaceManager The green space manager from which the list of green spaces will be retrieved.
     * @return A list of green spaces.
     */
    private List<GreenSpace> getListGreenSpace(GreenSpaceManager greenSpaceManager) {
        return greenSpaceManager.getListGreenSpaces().getGreenSpaceList();
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
     * Assigns a task referenced by its ID to a team.
     *
     * @param teamId The ID of the team to which the task will be assigned.
     * @param taskReference The reference of the task to be assigned.
     * @return {@code true} if the task was successfully assigned to the team and an email notification was sent; {@code false} otherwise.
     */
    public boolean assignTeamTask(int teamId, String taskReference) {
        Optional<Team> teamOptional = getTeamRepository().getTeamById(teamId);

        if (teamOptional.isPresent()) {
            Optional<TaskAgenda> taskAgendaOptional = getAgenda().getTaskByReference(taskReference);

            if (taskAgendaOptional.isPresent()) {
                if (assignTeamTask(teamOptional.get(), taskAgendaOptional.get())) {
                    return sendEmailTeam(teamOptional.get(), "You were given this task [" + taskAgendaOptional.get().getTaskToDoList().getTaskRef() + "] - \"" + taskAgendaOptional.get().getTaskToDoList().getGreenSpace().getName() + " " + taskAgendaOptional.get().getTaskToDoList().getTask().getTaskDescription() + "\"");
                }

            }

        }

        return false;
    }

    /**
     * Sends an email to the specified team.
     *
     * @param team The team to whom the email will be sent.
     * @param msg The message to be sent.
     * @return {@code true} if the email was successfully sent; {@code false} otherwise.
     */
    private boolean sendEmailTeam(Team team, String msg) {
        return team.sendEmail(msg);
    }

    /**
     * Assigns a task agenda to the specified team.
     *
     * @param team The team to which the task agenda will be assigned.
     * @param taskAgenda The task agenda to be assigned.
     * @return {@code true} if the task agenda was successfully assigned to the team; {@code false} otherwise.
     */
    private boolean assignTeamTask(Team team, TaskAgenda taskAgenda) {
        return getAgenda().assignTeamTask(team, taskAgenda);
    }
}
