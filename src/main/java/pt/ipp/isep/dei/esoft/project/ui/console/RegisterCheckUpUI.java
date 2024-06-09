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

    public RegisterCheckUpUI() {
        controller = new RegisterCheckUpController();
    }

    public RegisterCheckUpController getController() {
        return controller;
    }

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

    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    private void displayData() {
        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\n License Plate: %s", checkUpLicensePlate);
        System.out.printf("\nDate: %s", checkUpDate);
        System.out.printf("\n Current Kms: %d", checkUpCurrenteKms);

    }

    private void requestData() throws ParseException {
        checkUpLicensePlate = requestCheckUpLincesePlate();
        checkUpDate = requestCheckUpDate();
        checkUpCurrenteKms = requestCheckUpCurrentKms();
    }

    private int requestCheckUpCurrentKms() {
        Scanner input = new Scanner(System.in);
        System.out.println("Current Kms: ");
        return input.nextInt();
    }

    private String requestCheckUpLincesePlate() {
        Scanner input = new Scanner(System.in);
        System.out.println("License PLate: ");
        return input.nextLine();
    }

    private Date requestCheckUpDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("CheckUp Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }
}
