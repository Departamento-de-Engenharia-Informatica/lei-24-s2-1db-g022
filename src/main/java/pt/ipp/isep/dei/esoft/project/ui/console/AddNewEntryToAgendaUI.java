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

/**
 * Represents a user interface for adding a new entry to the agenda.
 *
 * @author Group22
 */
public class AddNewEntryToAgendaUI implements Runnable {

    private final AddNewEntryToAgendaController controller;
    private String taskToDoListReference;
    private String greenSpaceName;
    private Date startDate;
    private int expectedDuration;

    /**
     * Constructs a new instance of the AddNewEntryToAgendaUI class.
     * Initializes the controller for adding new entries to the agenda.
     */
    public AddNewEntryToAgendaUI() {
        controller = new AddNewEntryToAgendaController();
    }

    /**
     * Retrieves the controller for adding new entries to the agenda.
     *
     * @return The AddNewEntryToAgendaController instance used by this UI.
     */
    private AddNewEntryToAgendaController getController() {
        return controller;
    }

    /**
     * Runs the process for adding a new entry to the agenda.
     * Guides the user through selecting a green space and a task, entering additional data,
     * displaying the entered data, and confirming the submission.
     *
     * @throws RuntimeException If there is an error during execution.
     */
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

    /**
     * Submits the entered data to add a new entry to the agenda.
     * Calls the controller to add the entry and prints success or failure messages accordingly.
     *
     * @return True if the entry is added successfully, false otherwise.
     */
    private boolean submitData() {

        boolean success = true;

        Optional<TaskAgenda> taskAgenda = getController().addNewEntryToAgenda(taskToDoListReference, startDate, expectedDuration);

        if (taskAgenda.isPresent()) {
            System.out.println("\nNew Entry added a Agenda with success !");
            return success;
        } else {
            System.out.println("\nEntry not added to Agenda !");
            return !success;
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
        System.out.printf("\nGreenSpace: %s", greenSpaceName);
        System.out.printf("\nTask: %s", taskToDoListReference);
        System.out.printf("\nStart Date: %s", DateFormat.getDateTimeInstance().format(startDate));
        System.out.printf("\nExpected Duration: %d", expectedDuration);

    }

    /**
     * Requests and sets the start date, end date, and expected duration for the agenda entry.
     *
     * @throws ParseException If an error occurs while parsing the input dates.
     */
    private void requestData() throws ParseException {
        startDate = requestStartDate();
        expectedDuration = requestExpectedDuration();
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
     * Requests and retrieves the expected duration for the agenda entry.
     *
     * @return The expected duration.
     */
    private int requestExpectedDuration() {

        Scanner input = new Scanner(System.in);
        System.out.println("Expected Duration: ");

        return input.nextInt();
    }

    /**
     * Displays the list of task to-do options for the user to select from,
     * and returns the reference of the selected task to-do list.
     * This method fetches the task to-do lists from the controller,
     * displays them to the user, and allows the user to select one of them.
     * The method then returns the reference of the selected task to-do list.
     *
     * @return the reference of the selected task to-do list.
     */
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

    /**
     * Displays the list of task to-do lists and prompts the user to select one.
     *
     */
    private void displayTaskToDoListOptions(List<TaskToDoListDto> greenSpaceDtoList) {

        int i = 1;
        for (TaskToDoListDto gDto : greenSpaceDtoList) {

            System.out.println("  " + i + " - [" + gDto.getTaskToDoListReference() + "] " + gDto.getTaskToDoListGreenSpaceName() + " - " + gDto.getTaskToDoListDescription());
            i++;
        }
    }

    /**
     * Displays the list of green spaces and prompts the user to select one.
     *
     * @return The name of the selected green space.
     */
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

    /**
     * Displays the list of green space options.
     *
     * @param greenSpaceDtoList The list of green space DTOs.
     */
    private void displayGreenSpaceOptions(List<GreenSpaceDto> greenSpaceDtoList) {

        int i = 1;
        for (GreenSpaceDto gDto : greenSpaceDtoList) {

            System.out.println("  " + i + " - " + gDto.getGreenSpaceName());
            i++;
        }
    }

}
