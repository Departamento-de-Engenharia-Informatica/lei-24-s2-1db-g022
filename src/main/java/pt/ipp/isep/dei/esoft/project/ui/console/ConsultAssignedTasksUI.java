package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ConsultAssignedTasksController;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskAgendaDto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a user interface for Consulting Assigned Tasks.
 *
 * @author Group22
 */
public class ConsultAssignedTasksUI implements Runnable {
    private final ConsultAssignedTasksController controller;
    private String filterStatus;
    private Date startDate;
    private Date endDate;
    private List<String> listStatus;

    /**
     * Constructor for the ConsultAssignedTasksUI class.
     * Initializes a new instance of ConsultAssignedTasksController.
     */
    public ConsultAssignedTasksUI() {
        controller = new ConsultAssignedTasksController();
    }

    /**
     * Retrieves the controller for consulting assigned tasks.
     *
     * @return the ConsultAssignedTasksController instance.
     */
    private ConsultAssignedTasksController getController() {
        return controller;
    }


    /**
     * Runs the user interface for consulting assigned tasks.
     * This method displays a header, requests status data,
     * attempts to request and display task data, and allows for data submission
     * upon confirmation. In case of a ParseException, it throws a RuntimeException.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Consulting Tasks ------------------------");
        requestStatusData();
        try {
            requestData();
            displayData();
            if (confirmationData()) {
                submitData();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Submits the entered data to add a new entry to the agenda.
     * Calls the controller to add the entry and prints success or failure messages accordingly.
     *
     * @return True if the entry is added successfully, false otherwise.
     */
    private void submitData() {

        List<TaskAgendaDto> taskAgendaDto = getController().getTasksBetweenTwoDates(startDate, endDate, filterStatus);

        for (TaskAgendaDto task : taskAgendaDto) {
            System.out.println("[" + task.getTaskToDoListReference() + "] " + task.getTaskToDoListGreenSpaceName() + " " + task.getTaskToDoListDescription() + " - " + task.getTaskToDoListStartDate());
        }

    }

    /**
     * Asks the user to confirm entered data.
     *
     * @return True if the user confirms with "Y" or "y", false otherwise.
     */
    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    /**
     * Displays the entered data.
     */
    private void displayData() {
        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\nStatus Filter: %s", filterStatus);
        System.out.printf("\nStart Date: %s", startDate);
        System.out.printf("\nEnd Date: %s", endDate);

    }

    private void requestData() throws ParseException {
        startDate = requestStartDate();
        endDate = requestEndDate();
    }

    /**
     * Requests and parses the start date for the agenda entry.
     *
     * @return The parsed start date.
     * @throws ParseException If an error occurs while parsing the input date.
     */
    private Date requestEndDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("End Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    /**
     * Requests and parses the start date for the agenda entry.
     *
     * @return The parsed start date.
     * @throws ParseException If an error occurs while parsing the input date.
     */
    private Date requestStartDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Start Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    /**
     * Requests the type of the green space.
     */
    private void requestStatusData() {
        filterStatus = displayAndSelectStatusTask();
    }

    /**
     * Displays the available types of green space and prompts the user to select one.
     *
     * @return The selected type of green space.
     */
    private String displayAndSelectStatusTask() {
        addStatus();
        int listSize = listStatus.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayStatusTaskOptions();
            System.out.println("Select a Status: ");
            answer = input.nextInt();
        }

        return listStatus.get(answer - 1);
    }

    /**
     * Displays the options for selecting the type of green space.
     */
    private void displayStatusTaskOptions() {
        int i = 0;
        for (String status : listStatus) {
            ++i;
            System.out.println("[" + i + "] " + status);
        }
    }

    /**
     * Adds predefined types of green spaces to the list.
     */
    private void addStatus() {
        listStatus = new ArrayList<>();
        listStatus.add("Planned");
        listStatus.add("Postponed");
        listStatus.add("Canceled");
        listStatus.add("Done");
    }
}
