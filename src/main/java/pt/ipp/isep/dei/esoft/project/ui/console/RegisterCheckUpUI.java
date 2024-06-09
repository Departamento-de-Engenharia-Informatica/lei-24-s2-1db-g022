package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCheckUpController;
import pt.ipp.isep.dei.esoft.project.domain.model.CheckUp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

/**
 * User interface for registering vehicle check-ups.
 *
 * @author Group22
 */
public class RegisterCheckUpUI implements Runnable {
    private final RegisterCheckUpController controller;
    private Date checkUpDate;
    private int checkUpCurrenteKms;

    private String checkUpLicensePlate;

    /**
     * Initializes the RegisterCheckUpUI with a RegisterCheckUpController.
     */
    public RegisterCheckUpUI() {
        controller = new RegisterCheckUpController();
    }

    /**
     * Gets the RegisterCheckUpController associated with this UI.
     *
     * @return The RegisterCheckUpController associated with this UI.
     */
    public RegisterCheckUpController getController() {
        return controller;
    }

    /**
     * Runs the process for registering a check-up. Prompts the user to enter data, displays the entered data,
     * and asks for confirmation before submitting the data.
     */
    @Override
    public void run() {
        boolean register = false;

        System.out.println("\n\n--- Register CheckUp ------------------------");

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
     * Submits the entered check-up data to the controller for registration.
     *
     * @return true if the check-up data is successfully registered; otherwise, false.
     */
    private boolean submitData() {

        boolean success = true;

        Optional<CheckUp> checkUp = getController().registerCheckUp(checkUpLicensePlate, checkUpDate, checkUpCurrenteKms);

        if (checkUp.isPresent()) {
            System.out.println("\nCheckUp successfully Registed!");
            return success;
        } else {
            System.out.println("\nCheckUp not Registed!");
            return !success;
        }
    }

    /**
     * Asks the user to confirm the entered data.
     *
     * @return true if the user confirms the data by entering 'Y' or 'y'; otherwise, false.
     */
    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    /**
     * Displays the information entered for the check-up.
     */
    private void displayData() {
        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\n License Plate: %s", checkUpLicensePlate);
        System.out.printf("\nDate: %s", checkUpDate);
        System.out.printf("\n Current Kms: %d", checkUpCurrenteKms);

    }

    /**
     * Requests the necessary data for registering a check-up.
     *
     * @throws ParseException If there is an error parsing the date input.
     */
    private void requestData() throws ParseException {
        checkUpLicensePlate = requestCheckUpLincesePlate();
        checkUpDate = requestCheckUpDate();
        checkUpCurrenteKms = requestCheckUpCurrentKms();
    }

    /**
     * Requests the current kilometers for the check-up.
     *
     * @return The current kilometers input by the user.
     */
    private int requestCheckUpCurrentKms() {
        Scanner input = new Scanner(System.in);
        System.out.println("Current Kms: ");
        return input.nextInt();
    }

    /**
     * Requests the license plate for the check-up.
     *
     * @return The license plate input by the user.
     */
    private String requestCheckUpLincesePlate() {
        Scanner input = new Scanner(System.in);
        System.out.println("License PLate: ");
        return input.nextLine();
    }

    /**
     * Requests the date for the check-up.
     *
     * @return The date input by the user.
     * @throws ParseException If the input date cannot be parsed.
     */
    private Date requestCheckUpDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("CheckUp Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }
}
