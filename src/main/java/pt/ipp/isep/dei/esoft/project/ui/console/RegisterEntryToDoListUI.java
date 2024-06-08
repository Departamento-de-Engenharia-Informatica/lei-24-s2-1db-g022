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

    private String displayAndSelectGreenSpaceList() {

        List<GreenSpaceDto> greenSpaceDtoList = controller.getGreenSpaceManagerGSpace();

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

    private void displayGreenSpaceOptions(List<GreenSpaceDto> greenSpaceDtoList) {

        int i = 1;
        for (GreenSpaceDto gDto : greenSpaceDtoList) {

            System.out.println("  " + i + " - " + gDto.getGreenSpaceName());
            i++;
        }
    }

    private String displayAndSelectTaskList() {

        List<TaskDto> taskDtoList = controller.getAllTasks();

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

    private void displayTaskOptions(List<TaskDto> taskDtoList) {

        int i = 1;
        for (TaskDto tDto : taskDtoList) {

            System.out.println("  " + i + " - " + tDto.getTaskDescription());
            i++;
        }
    }

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

    private int requestaproxExpectedDuration() {

        Scanner input = new Scanner(System.in);
        System.out.println("Approximate Expected Duration: ");

        return input.nextInt();
    }
}
