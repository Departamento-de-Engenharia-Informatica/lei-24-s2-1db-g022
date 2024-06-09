package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddNewEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskToDoListDto;
import pt.ipp.isep.dei.esoft.project.domain.model.TaskAgenda;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AddNewEntryToAgendaUI implements Runnable {

    private final AddNewEntryToAgendaController controller;
    private String taskToDoListReference;
    private String greenSpaceName;
    private Date startDate;
    private Date endDate;
    private int expectedDuration;

    public AddNewEntryToAgendaUI() {
        controller = new AddNewEntryToAgendaController();
    }

    private AddNewEntryToAgendaController getController() {
        return controller;
    }

    @Override
    public void run() {

        boolean register = false;

        System.out.println("\n\n--- Add New Entry In Agenda ------------------------");

        greenSpaceName = displayAndSelectGreenSpaceList();
        taskToDoListReference = displayAndSelectTaskToDoList();

        while (!register) {
            try {
                requestData();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            displayData();

            if (confirmationData()) {
                register = submitData();
            }
        }

    }

    private boolean submitData() {

        boolean success = true;

        Optional<TaskAgenda> taskAgenda = getController().addNewEntryToAgenda(taskToDoListReference, startDate, endDate, expectedDuration);

        if (taskAgenda.isPresent()) {
            System.out.println("\nNew Entry added a Agenda with success !");
            return success;
        } else {
            System.out.println("\nEntry not added to Agenda !");
            return !success;
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
        System.out.printf("\nGreenSpace: %s", greenSpaceName);
        System.out.printf("\nTask: %s", taskToDoListReference);
        System.out.printf("\nStart Date: %s", DateFormat.getDateTimeInstance().format(startDate));
        System.out.printf("\nEnd Date: %s", DateFormat.getDateTimeInstance().format(endDate));
        System.out.printf("\nExpected Duration: %d", expectedDuration);

    }

    private void requestData() throws ParseException {
        startDate = requestStartDate();
        endDate = requestEndDate();
        expectedDuration = requestExpectedDuration();
    }

    private Date requestStartDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Start Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    private Date requestEndDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("End Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    private int requestExpectedDuration() {

        Scanner input = new Scanner(System.in);
        System.out.println("Expected Duration: ");

        return input.nextInt();
    }

    private String displayAndSelectTaskToDoList() {
        List<TaskToDoListDto> taskToDoListDtoList = getController().getTaskToDoList(greenSpaceName);

        int listSize = taskToDoListDtoList.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            displayTaskToDoListOptions(taskToDoListDtoList);

            System.out.println("Select a Task: ");
            answer = input.nextInt();
        }

        return taskToDoListDtoList.get(answer - 1).getTaskToDoListReference();
    }

    private void displayTaskToDoListOptions(List<TaskToDoListDto> greenSpaceDtoList) {

        int i = 1;
        for (TaskToDoListDto gDto : greenSpaceDtoList) {

            System.out.println("  " + i + " - [" + gDto.getTaskToDoListReference() + "] " + gDto.getTaskToDoListGreenSpaceName() + " - " + gDto.getTaskToDoListDescription());
            i++;
        }
    }

    private String displayAndSelectGreenSpaceList() {

        List<GreenSpaceDto> greenSpaceListDto = getController().getGreenSpaceManager();

        int listSize = greenSpaceListDto.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            displayGreenSpaceOptions(greenSpaceListDto);

            System.out.println("Select a Green Space Name: ");
            answer = input.nextInt();
        }

        return greenSpaceListDto.get(answer - 1).getGreenSpaceName();
    }

    private void displayGreenSpaceOptions(List<GreenSpaceDto> greenSpaceDtoList) {

        int i = 1;
        for (GreenSpaceDto gDto : greenSpaceDtoList) {

            System.out.println("  " + i + " - " + gDto.getGreenSpaceName());
            i++;
        }
    }

}
