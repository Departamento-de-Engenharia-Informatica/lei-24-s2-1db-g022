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

public class AssignTeamAgendaController {
    private AuthenticationRepository authenticationRepository;
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository;
    private Agenda agenda;


    public AssignTeamAgendaController() {
        getAuthenticationRepository();
        getCollaboratorRepository();
        getTeamRepository();
        getAgenda();
    }

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

    public List<TeamDto> getAllTeams() {
        List<Team> teams = getTeamRepository().getTeamList();

        TeamMapper mapper = new TeamMapper();
        return mapper.toDTO(teams);
    }


    public List<TaskAgendaDto> getAllTaskByGSM() {

        List<TaskAgenda> listTaskAgenda = new ArrayList<>();

        GreenSpaceManager greenSpaceManagerEmail = getGSMFromSession();
        Optional<ICollaborator> collaboratorOptional = getCollaboratorRepository().getCollaboratorByEmail(greenSpaceManagerEmail.getEmail());

        if (collaboratorOptional.isPresent()) {

            GreenSpaceManager greenSpaceManager = (GreenSpaceManager) collaboratorOptional.get();
            List<GreenSpace> greenSpaceList = getListGreenSpace(greenSpaceManager);

            if (!greenSpaceList.isEmpty()) {
                listTaskAgenda = getAllTaskByGreenSpace(greenSpaceList);
            }
        }

        TaskAgendaMapper mapper = new TaskAgendaMapper();

        return mapper.toDTOTaskToDoList(listTaskAgenda);
    }

    private List<TaskAgenda> getAllTaskByGreenSpace(List<GreenSpace> greenSpaceList) {

        return getAgenda().getAllTaskByGreenSpace(greenSpaceList);
    }

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

    private boolean sendEmailTeam(Team team, String msg) {
        return team.sendEmail(msg);
    }

    private boolean assignTeamTask(Team team, TaskAgenda taskAgenda) {
        return getAgenda().assignTeamTask(team, taskAgenda);

    }
}
