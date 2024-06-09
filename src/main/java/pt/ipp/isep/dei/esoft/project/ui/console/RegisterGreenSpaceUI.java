package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * User interface for registering a new green space.
 *
 * @author Group22
 */
public class RegisterGreenSpaceUI implements Runnable {
    private final RegisterGreenSpaceController controller;
    private String greenSpaceName;
    private int area;
    private String streetName;
    private int doorNumber;
    private String postCodeNumber;
    private String localization;
    private List<String> greenSpaceTypes;
    private String greenSpaceType;

    /**
     * Constructs a new RegisterGreenSpaceUI instance.
     */
    public RegisterGreenSpaceUI() {
        controller = new RegisterGreenSpaceController();
    }

    /**
     * Gets the RegisterGreenSpaceController instance associated with this UI.
     *
     * @return The RegisterGreenSpaceController instance.
     */
    private RegisterGreenSpaceController getController() {
        return controller;
    }

    /**
     * Runs the Register GreenSpace UI flow.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Register GreenSpace ------------------------");

        boolean register = false;

        while (!register) {
            requestTypeData();
            requestData();
            displayData();

            if (confirmationData()) {
                register = submitData();
            }
        }

    }

    /**
     * Submits the data to register a GreenSpace.
     *
     * @return True if the GreenSpace is successfully registered, false otherwise.
     */
    private boolean submitData() {
        boolean success = true;

        Optional<GreenSpace> greenSpace = getController().registerGreenSpace(greenSpaceType, greenSpaceName, area, streetName, doorNumber, postCodeNumber, localization);

        if (greenSpace.isPresent()) {
            System.out.println("\nGreenSpace successfully created!");
            return success;
        }
        System.out.println("\nGreenSpace not created!");

        return !success;
    }

    /**
     * Displays the information of the GreenSpace to be registered.
     */
    private void displayData() {
        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\nType: %s", greenSpaceType);
        System.out.printf("\nGreen Space: %s", greenSpaceName);
        System.out.printf("\nArea: %d", area);
        System.out.printf("\nStreet Name: %s", streetName);
        System.out.printf("\nDoorNumber: %d", doorNumber);
        System.out.printf("\nPost Code Number: %s", postCodeNumber);
        System.out.printf("\nLocation: %s", localization);
    }

    /**
     * Asks the user to confirm the provided data.
     *
     * @return true if the user confirms, false otherwise.
     */
    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    /**
     * Requests input data for registering a green space.
     */
    private void requestData() {
        greenSpaceName = requestGreenSpaceNameData();
        area = requestAreaData();
        streetName = requestStreetData();
        doorNumber = requestDoorNumber();
        postCodeNumber = requestPostCodeNumber();
        localization = requestLocalization();
    }

    /**
     * Requests the localization of the green space.
     *
     * @return The localization entered by the user.
     */
    private String requestLocalization() {
        Scanner input = new Scanner(System.in);
        System.out.println("Location: ");
        return input.nextLine();
    }

    /**
     * Requests the postal code number of the green space.
     *
     * @return The postal code number entered by the user.
     */
    private String requestPostCodeNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Post Code Number: ");
        return input.nextLine();
    }

    /**
     * Requests the door number of the green space.
     *
     * @return The door number entered by the user.
     */
    private int requestDoorNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Door Number: ");
        return input.nextInt();
    }

    /**
     * Requests the street name of the green space.
     *
     * @return The street name entered by the user.
     */
    private String requestStreetData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Street Name: ");
        return input.nextLine();
    }

    /**
     * Requests the area of the green space.
     *
     * @return The area entered by the user in hectares.
     */
    private int requestAreaData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Area(ha): ");
        return input.nextInt();
    }

    /**
     * Requests the name of the green space.
     *
     * @return The name of the green space entered by the user.
     */
    private String requestGreenSpaceNameData() {
        Scanner input = new Scanner(System.in);
        System.out.println("GreenSpace Name: ");
        return input.nextLine();
    }

    /**
     * Requests the type of the green space.
     */
    private void requestTypeData() {
        greenSpaceType = displayAndSelectTypeGreenSpace();
    }

    /**
     * Displays the available types of green space and prompts the user to select one.
     *
     * @return The selected type of green space.
     */
    private String displayAndSelectTypeGreenSpace() {
        addTypes();
        int listSize = greenSpaceTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayTypeGreenSpaceOptions();
            System.out.println("Select a Type GreenSpace: ");
            answer = input.nextInt();
        }

        return greenSpaceTypes.get(answer - 1);
    }

    /**
     * Displays the options for selecting the type of green space.
     */
    private void displayTypeGreenSpaceOptions() {
        int i = 0;
        for (String type : greenSpaceTypes) {
            ++i;
            System.out.println("[" + i + "] " + type);
        }
    }

    /**
     * Adds predefined types of green spaces to the list.
     */
    private void addTypes() {
        greenSpaceTypes = new ArrayList<>();
        greenSpaceTypes.add("Garden");
        greenSpaceTypes.add("Medium Park");
        greenSpaceTypes.add("Large Park");
    }


}
