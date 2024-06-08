package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

    public RegisterGreenSpaceUI() {
        controller = new RegisterGreenSpaceController();
    }

    private RegisterGreenSpaceController getController() {
        return controller;
    }

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

    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    private void requestData() {
        greenSpaceName = requestGreenSpaceNameData();
        area = requestAreaData();
        streetName = requestStreetData();
        doorNumber = requestDoorNumber();
        postCodeNumber = requestPostCodeNumber();
        localization = requestLocalization();
    }

    private String requestLocalization() {
        Scanner input = new Scanner(System.in);
        System.out.println("Location: ");
        return input.nextLine();
    }

    private String requestPostCodeNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Post Code Number: ");
        return input.nextLine();
    }

    private int requestDoorNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Door Number: ");
        return input.nextInt();
    }

    private String requestStreetData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Street Name: ");
        return input.nextLine();
    }

    private int requestAreaData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Area(ha): ");
        return input.nextInt();
    }

    private String requestGreenSpaceNameData() {
        Scanner input = new Scanner(System.in);
        System.out.println("GreenSpace Name: ");
        return input.nextLine();
    }

    private void requestTypeData() {
        greenSpaceType = displayAndSelectTypeGreenSpace();
    }

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

    private void displayTypeGreenSpaceOptions() {
        int i = 0;
        for (String type : greenSpaceTypes) {
            ++i;
            System.out.println("[" + i + "] " + type);
        }
    }

    private void addTypes() {
        greenSpaceTypes = new ArrayList<>();
        greenSpaceTypes.add("Garden");
        greenSpaceTypes.add("Medium Park");
        greenSpaceTypes.add("Large Park");
    }


}
