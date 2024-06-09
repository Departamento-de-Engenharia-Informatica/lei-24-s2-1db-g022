package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.DTO.CollaboratorDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskAgendaDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TeamDto;

import java.util.List;
import java.util.Scanner;

public class AssignTeamAgendaUi implements Runnable {

    private AssignTeamAgendaController controller;

    private int teamId;

    private String taskReference;

    public AssignTeamAgendaUi() {
        controller = new AssignTeamAgendaController();
    }

    private AssignTeamAgendaController getController() {
        return controller;
    }

    @Override
    public void run() {
        boolean register = false;

        System.out.println("\n\n--- Assign Team To Agenda ------------------------");

        taskReference = displayAndSelectTaskAgenda();
        teamId = displayAndSelectTeam();

        while (!register) {
            displayData();

            if (confirmationData()) {
                submitData();
            }
        }

    }

    private void submitData() {


        boolean success = getController().assignTeamTask(teamId, taskReference);

        if (success) {
            System.out.println("\nAssign Team Task Successfully Submitted! !");
        } else {
            System.out.println("\nAssign Team Task has not been Submitted! !");
        }
    }

    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    private void displayData() {
        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\nTask: %s", taskReference);
        System.out.printf("\nteamID: %s", teamId);
    }

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

    private void displayTaskAgendaOptions(List<TaskAgendaDto> listAgendaDto) {

        int i = 1;
        for (TaskAgendaDto gDto : listAgendaDto) {

            System.out.println("  " + i + " - [" + gDto.getTaskToDoListReference() + "] - " + gDto.getTaskToDoListGreenSpaceName() + " " + gDto.getTaskToDoListDescription());
            i++;
        }
    }

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
