package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.DTO.CollaboratorDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskAgendaDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TeamDto;

import java.util.List;
import java.util.Scanner;

/**
 * Represents a user interface for assigning a team to an agenda.
 *
 * @author Group22
 */
public class AssignTeamAgendaUi implements Runnable {

    private AssignTeamAgendaController controller;

    private int teamId;

    private String taskReference;

    /**
     * Initializes the Assign Team Agenda UI with its corresponding controller.
     */
    public AssignTeamAgendaUi() {
        controller = new AssignTeamAgendaController();
    }

    /**
     * Retrieves the controller responsible for assigning teams to the agenda.
     *
     * @return The AssignTeamAgendaController object.
     */
    private AssignTeamAgendaController getController() {
        return controller;
    }

    /**
     * Executes the process of assigning a team to the agenda.
     */
    @Override
    public void run() {
        boolean register = false;

        System.out.println("\n\n--- Assign Team To Agenda ------------------------");

        taskReference = displayAndSelectTaskAgenda();
        teamId = displayAndSelectTeam();

        while (!register) {
            displayData();

            if (confirmationData()) {
                register = submitData();
            }
        }

    }

    /**
     * Submits the assigned team task data.
     */
    private boolean submitData() {

        boolean success = getController().assignTeamTask(teamId, taskReference);

        if (success) {
            System.out.println("\nAssign Team Task Successfully Submitted! !");
            return true;
        } else {
            System.out.println("\nAssign Team Task has not been Submitted! !");
            return false;
        }
    }

    /**
     * Requests confirmation of the entered data.
     *
     * @return true if the user confirms the data, false otherwise.
     */
    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    /**
     * Displays the information of the task reference and team ID.
     */
    private void displayData() {
        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\nTask: %s", taskReference);
        System.out.printf("\nteamID: %s", teamId);
    }

    /**
     * Displays the task agendas and allows the user to select one.
     *
     * @return The selected task agenda reference.
     */
    private String displayAndSelectTaskAgenda() {
        List<TaskAgendaDto> listAgendaDto = getController().getAllTaskByGSM();

        int listSize = listAgendaDto.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            displayTaskAgendaOptions(listAgendaDto);

            System.out.println("Select a Task Agenda: ");
            answer = input.nextInt();
        }

        return listAgendaDto.get(answer - 1).getTaskToDoListReference();
    }

    /**
     * Displays the options for task agendas.
     *
     * @param listAgendaDto The list of task agendas to display.
     */
    private void displayTaskAgendaOptions(List<TaskAgendaDto> listAgendaDto) {

        int i = 1;
        for (TaskAgendaDto gDto : listAgendaDto) {

            System.out.println("  " + i + " - [" + gDto.getTaskToDoListReference() + "] - " + gDto.getTaskToDoListGreenSpaceName() + " " + gDto.getTaskToDoListDescription());
            i++;
        }
    }

    /**
     * Displays the options for teams and allows the user to select a team.
     *
     * @return The ID of the selected team.
     */
    private int displayAndSelectTeam() {
        List<TeamDto> listTeamDto = getController().getAllTeams();

        int listSize = listTeamDto.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            displayTeamOptions(listTeamDto);

            System.out.println("Select a Team: ");
            answer = input.nextInt();
        }

        return listTeamDto.get(answer - 1).getTeamId();
    }

    /**
     * Displays the options for teams and their collaborators and allows the user to select a team.
     *
     * @param teamDtoList The list of team DTOs containing team information.
     */
    private void displayTeamOptions(List<TeamDto> teamDtoList) {

        int i = 1;
        for (TeamDto gDto : teamDtoList) {

            System.out.println("  " + i + " - ID[" + gDto.getTeamId() + "] Team");
            for (CollaboratorDto collaboratorDto : gDto.getTeamCollaborator()) {
                System.out.println("\t\tCollaborators: " + collaboratorDto.getCollaboratorEmailAndName());
            }
            i++;
        }
    }
}
