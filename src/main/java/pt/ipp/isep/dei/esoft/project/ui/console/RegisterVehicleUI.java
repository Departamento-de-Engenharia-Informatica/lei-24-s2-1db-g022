package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

/**
 * The RegisterVehicleUI class represents the user interface for registering a vehicle.
 * It prompts the user to input a vehicle name and submits the data to create the vehicle.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class RegisterVehicleUI implements Runnable {

    private final RegisterVehicleController controller;
    private String type;
    private float tare;
    private float grossWeight;
    private int currentKm;
    private Date registerDate;
    private Date acquisitionDate;
    private int checkUpFrequency;
    private String licensePlate;
    private String brandName;
    private String modelName;

    /**
     * Constructs a RegisterVehicleUI object with a new RegisterVehicleController.
     */
    public RegisterVehicleUI() {
        controller = new RegisterVehicleController();
    }

    /**
     * Gets the RegisterVehicleController associated with this UI.
     * @return The RegisterVehicleController instance.
     */
    private RegisterVehicleController getController() {
        return controller;
    }

    /**
     * Executes the user interface, prompting the user to input a vehicle name and submitting the data.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Register Vehicle ------------------------");

        boolean register = false;

        while (!register){
            try {
                requestData();
                displayData();

                if(confirmationData()){
                    register = submitData();
                }

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

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
        System.out.printf("\nBrand: %s",brandName);
        System.out.printf("\nModel: %s",modelName);
        System.out.printf("\nType: %s",type);
        System.out.printf("\nTare: %f",tare);
        System.out.printf("\nGross Weight: %f",grossWeight);
        System.out.printf("\nCurrent Km: %d",currentKm);
        System.out.printf("\nRegister Date: %s",registerDate);
        System.out.printf("\nAcquisition Date: %s",acquisitionDate);
        System.out.printf("\nCheck-Up Frequency: %d",checkUpFrequency);
        System.out.printf("\nLicense Plate: %s",licensePlate);
    }


    /**
     * Submits the vehicle data to create the vehicle and displays the result.
     *
     * @return True if the vehicle is successfully registered, false otherwise.
     */
    private boolean submitData() {

        boolean success = true;

        Optional<Vehicle> vehicle = getController().registerVehicle(type,tare,grossWeight,currentKm,registerDate,acquisitionDate,checkUpFrequency,licensePlate,brandName,modelName);

        if (vehicle.isPresent()) {
            System.out.println("\nVehicle successfully registered!");
            return success;
        } else {
            System.out.println("\nVehicle not registered!");
            return !success;
        }
    }

    /**
     * Requests the vehicle type from the user.
     */
    private void requestData() throws ParseException {
        brandName = requestBrandName();
        modelName = requestModelName();
        type = requestType();
        tare = requestTare();
        grossWeight = requestGrossWeight();
        currentKm = requestCurrentKm();
        registerDate = requestRegisterDate();
        acquisitionDate = requestAcquisitionDate();
        checkUpFrequency = requestCheckupFrequency();
        licensePlate = requestLicensePlate();
    }

    /**
     * Requests a vehicle brand input from the user.
     * @return The vehicle brand entered by the user.
     */
    private String requestBrandName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Brand: ");
        return input.nextLine();
    }

    /**
     * Requests a vehicle model input from the user.
     * @return The vehicle model entered by the user.
     */
    private String requestModelName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Model: ");
        return input.nextLine();
    }

    /**
     * Requests a vehicle type input from the user.
     * @return The vehicle type entered by the user.
     */
    private String requestType() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Type: ");
        return input.nextLine();
    }

    /**
     * Requests a vehicle tare input from the user.
     * @return The vehicle tare entered by the user.
     */
    private float requestTare() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Tare: ");
        return input.nextFloat();
    }

    /**
     * Requests a vehicle grossWeight input from the user.
     * @return The vehicle grossWeight entered by the user.
     */
    private float requestGrossWeight() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Gross Weight: ");
        return input.nextFloat();
    }

    /**
     * Requests a vehicle currentKm input from the user.
     * @return The vehicle currentKm entered by the user.
     */
    private int requestCurrentKm() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Current Km: ");
        return input.nextInt();
    }

    /**
     * Requests a vehicle registerDate input from the user.
     * @return The vehicle registerDate entered by the user.
     */
    private Date requestRegisterDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Register Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    /**
     * Requests a vehicle acquisitionDate input from the user.
     * @return The vehicle acquisitionDate entered by the user.
     */
    private Date requestAcquisitionDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Acquisition Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    /**
     * Requests a vehicle checkUpFrequency input from the user.
     * @return The vehicle checkUpFrequency entered by the user.
     */
    private int requestCheckupFrequency() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle Check-Up Frequency: ");
        return input.nextInt();
    }

    /**
     * Requests a vehicle licensePlate input from the user.
     * @return The vehicle licensePlate entered by the user.
     */
    private String requestLicensePlate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vehicle License Plate: ");
        return input.nextLine();
    }
}



