package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.DTO.GreenSpaceDto;
import pt.ipp.isep.dei.esoft.project.domain.DTO.TaskDto;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEntryToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.model.TaskToDoList;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * User interface for registering entries in the to-do list.
 *
 * @author Group22
 */
public class RegisterEntryToDoListUI implements Runnable {

    private final RegisterEntryToDoListController controller;
    private String greenSpaceName;
    private String taskDescription;
    private String urgency;
    private int aproxExpectedDuration;

    /**
     * Constructs a RegisterCollaboratorUI object with a new RegistercollaboratorController.
     */
    public RegisterEntryToDoListUI() {

        controller = new RegisterEntryToDoListController();
    }

    /**
     * Gets the RegisterCollaboratorController associated with this UI.
     *
     * @return The RegisterCollaboratorController instance.
     */
    private RegisterEntryToDoListController getController() {

        return controller;
    }

    /**
     * Executes the user interface for adding a new entry to the to-do list.
     */
    @Override
    public void run() {

        boolean register = false;

        System.out.println("\n\n--- Add New Entry In To Do List ------------------------");

        greenSpaceName = displayAndSelectGreenSpaceList();
        taskDescription = displayAndSelectTaskList();
        urgency = displayAndSelectUrgency();

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
     * Prompts the user to confirm whether the entered data is correct or not.
     * Displays a message asking the user to input 'Y' or 'N' for confirmation.
     * Reads the user's input from the console and returns true if 'Y' or 'y' is entered,
     * indicating that the data is confirmed, and false otherwise.
     *
     * @return True if the user confirms the entered data, false otherwise.
     */
    private boolean confirmationData() {

        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    /**
     * Displays the collected information about the Entry in To Do List entered by the user.
     * Prints out the task description, urgency and approximate expected duration.
     */
    private void displayData() {

        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\nGreen Space Name: %s", greenSpaceName);
        System.out.printf("\nTask Description: %s", taskDescription);
        System.out.printf("\nUrgency: %s", urgency);
        System.out.printf("\nApproximate Expected Duration (in minutes): %s", aproxExpectedDuration);
    }

    /**
     * Submits the entry data to create the entry in to do list and displays the result.
     *
     * @return True if the entry is successfully added, false otherwise.
     */
    private boolean submitData() {

        boolean success = true;

        Optional<TaskToDoList> taskTdl = getController().registerEntryToDoList(greenSpaceName, taskDescription, urgency, aproxExpectedDuration);

        if (taskTdl.isPresent()) {

            System.out.println("\nEntry successfully added!");

            return success;
        } else {

            System.out.println("\nEntry not added!");

            return !success;
        }
    }

    /**
     * Displays the list of green spaces and prompts the user to select one.
     *
     * @return The name of the selected green space.
     */
    private String displayAndSelectGreenSpaceList() {

        List<GreenSpaceDto> greenSpaceDtoList = getController().getGreenSpaceManagerGSpace();

        int listSize = greenSpaceDtoList.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            displayGreenSpaceOptions(greenSpaceDtoList);

            System.out.println("Select a Green Space Name: ");
            answer = input.nextInt();
        }

        return greenSpaceDtoList.get(answer - 1).getGreenSpaceName();
    }

    /**
     * Displays the options for selecting a green space from the provided list.
     *
     * @param greenSpaceDtoList The list of green space DTOs to display as options.
     */
    private void displayGreenSpaceOptions(List<GreenSpaceDto> greenSpaceDtoList) {

        int i = 1;

        for (GreenSpaceDto gDto : greenSpaceDtoList) {

            System.out.println("  " + i + " - " + gDto.getGreenSpaceName());
            i++;
        }
    }

    /**
     * Displays the list of tasks and prompts the user to select one.
     *
     * @return The description of the selected task.
     */
    private String displayAndSelectTaskList() {

        List<TaskDto> taskDtoList = getController().getAllTasks();

        int listSize = taskDtoList.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            displayTaskOptions(taskDtoList);

            System.out.println("Select a Task Description: ");
            answer = input.nextInt();
        }

        return taskDtoList.get(answer - 1).getTaskDescription();
    }

    /**
     * Displays the options for selecting a task from the provided list.
     *
     * @param taskDtoList The list of task DTOs to display as options.
     */
    private void displayTaskOptions(List<TaskDto> taskDtoList) {

        int i = 1;

        for (TaskDto tDto : taskDtoList) {

            System.out.println("  " + i + " - " + tDto.getTaskDescription());
            i++;
        }
    }

    /**
     * Displays the list of urgency options and prompts the user to select one.
     *
     * @return The selected urgency level.
     */
    private String displayAndSelectUrgency() {

        List<String> urgencyList = new ArrayList<>();
        urgencyList.add("Low");
        urgencyList.add("Medium");
        urgencyList.add("High");

        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > 3) {

            displayUrgencyOptions(urgencyList);

            System.out.println("Select a Urgency: ");
            answer = input.nextInt();
        }

        return urgencyList.get(answer - 1);
    }

    /**
     * Displays the options for selecting an urgency level from the provided list.
     *
     * @param urgencyList The list of urgency levels to display as options.
     */
    private void displayUrgencyOptions(List<String> urgencyList) {

        int i = 1;
        for (String s : urgencyList) {

            System.out.println("  " + i + " - " + s);
            i++;
        }
    }

    /**
     * Requests approximate expected duration from the user.
     */
    private void requestData() throws ParseException {

        aproxExpectedDuration = requestaproxExpectedDuration();
    }

    /**
     * Requests the user to input the approximate expected duration of a task in minutes.
     *
     * @return The approximate expected duration inputted by the user.
     */
    private int requestaproxExpectedDuration() {

        Scanner input = new Scanner(System.in);
        System.out.println("Approximate Expected Duration (in minutes): ");

        return input.nextInt();
    }
}
