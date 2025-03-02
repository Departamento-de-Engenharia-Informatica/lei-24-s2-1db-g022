package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.model.Job;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * User interface for registering collaborators.
 *
 * @author Group22
 */
public class RegisterCollaboratorUI implements Runnable {

    private final RegisterCollaboratorController controller;
    private String collaboratorName;
    private String collaboratorJobName;
    private Date collaboratorDateOfBirth;
    private Date collaboratorAdmissionDate;
    private String collaboratorStreetName;
    private String collaboratorPostCode;
    private int collaboratorDoorNumber;
    private String collaboratorPhoneNumber;
    private String collaboratorEmail;
    private int collaboratorTaxpayer;
    private String collaboratorDocType;
    private int collaboratorNumber;

    /**
     * Constructs a RegisterCollaboratorUI object with a new RegistercollaboratorController.
     */
    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    /**
     * Gets the RegisterCollaboratorController associated with this UI.
     *
     * @return The RegisterCollaboratorController instance.
     */
    private RegisterCollaboratorController getController() {
        return controller;
    }

    /**
     * Executes the user interface for registering a collaborator.
     * Prompts the user to input collaborator details, displays the entered data,
     * and prompts for confirmation before submitting the data to create the collaborator.
     */
    @Override
    public void run() {
        boolean register = false;

        System.out.println("\n\n--- Register Collaborator ------------------------");

        collaboratorJobName = displayAndSelectJobList();

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
     * Asks for confirmation from the user.
     *
     * @return True if the user confirms with 'Y' or 'y', false otherwise.
     */
    private boolean confirmationData() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n--- Confirm Data[Y/N]: ");
        String res = input.nextLine();

        return res.equals("Y") || res.equals("y");
    }

    /**
     * Displays information about the collaborator.
     */
    private void displayData() {
        System.out.println("\n\n--- Display Information ------------------------");
        System.out.printf("\nCollaborator Name: %s", collaboratorName);
        System.out.printf("\n Collaborator Job Name: %s", collaboratorJobName);
        System.out.printf("\n Collaborator Date of Birth: %s", collaboratorDateOfBirth);
        System.out.printf("\n Collaborator Admission Date: %s", collaboratorAdmissionDate);
        System.out.printf("\n Collaborator Street Name: %s", collaboratorStreetName);
        System.out.printf("\n Collaborator Post Code: %s", collaboratorPostCode);
        System.out.printf("\n Collaborator Door Number: %s", collaboratorDoorNumber);
        System.out.printf("\n Collaborator Phone Number: %s", collaboratorPhoneNumber);
        System.out.printf("\n Collaborator Email: %s", collaboratorEmail);
        System.out.printf("\n Collaborator TaxPayer Number: %s", collaboratorTaxpayer);
        System.out.printf("\n Collaborator Document Type: %s", collaboratorDocType);
        System.out.printf("\n Collaborator Number: %s", collaboratorNumber);
    }

    /**
     * Submits the collaborator data to create the collaborator and displays the result.
     *
     * @return True if the collaborator is successfully created, false otherwise.
     */
    private boolean submitData() {

        boolean success = true;

        Optional<Collaborator> collaborator = getController().registerCollaborator(collaboratorName, collaboratorJobName, collaboratorDateOfBirth, collaboratorAdmissionDate, collaboratorStreetName, collaboratorPostCode, collaboratorDoorNumber, collaboratorPhoneNumber, collaboratorEmail, collaboratorTaxpayer, collaboratorDocType, collaboratorNumber);

        if (collaborator.isPresent()) {
            System.out.println("\ncollaborator successfully created!");
            return success;
        } else {
            System.out.println("\ncollaborator not created!");
            return !success;
        }
    }

    /**
     * Displays the list of available jobs and allows the user to select one.
     *
     * @return The name of the selected job.
     */
    private String displayAndSelectJobList() {
        List<Job> jobList = controller.getJobList();

        int listSize = jobList.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayJobOptions(jobList);
            System.out.println("Select a Job: ");
            answer = input.nextInt();
        }

        return jobList.get(answer - 1).getName();
    }

    /**
     * Displays the options for selecting a job from the provided list of jobs.
     *
     * @param jobList The list of available jobs.
     */
    private void displayJobOptions(List<Job> jobList) {
        int i = 1;
        for (Job job : jobList) {
            System.out.println("  " + i + " - " + job.getName());
            i++;
        }
    }

    /**
     * Requests the collaborator name from the user.
     */
    private void requestData() throws ParseException {
        collaboratorName = requestCollaboratorName();
        collaboratorDateOfBirth = requestCollaboratorDateOfBirth();
        collaboratorAdmissionDate = requestCollaboratorAdmissionDate();
        collaboratorStreetName = requestCollaboratorStreetName();
        collaboratorPostCode = requestCollaboratorPostCode();
        collaboratorDoorNumber = requestCollaboratorDoorNumber();
        collaboratorPhoneNumber = requestCollaboratorPhoneNumber();
        collaboratorEmail = requestCollaboratorEmail();
        collaboratorTaxpayer = requestCollaboratorTaxPayer();
        collaboratorDocType = requestCollaboratorDocType();
        collaboratorNumber = requestCollaboratorNumber();
    }

    /**
     * Requests a collaborator name input from the user.
     *
     * @return The collaborator name entered by the user.
     */

    private String requestCollaboratorName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Name: ");
        return input.nextLine();
    }

    /**
     * Requests the date of birth of the collaborator from the user.
     *
     * @return The date of birth entered by the user.
     * @throws ParseException If the entered date string cannot be parsed.
     */
    private Date requestCollaboratorDateOfBirth() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Date of Birth: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    /**
     * Requests the admission date of the collaborator from the user.
     *
     * @return The admission date entered by the user.
     * @throws ParseException If the entered date string cannot be parsed.
     */
    private Date requestCollaboratorAdmissionDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Admission Date: ");
        String dateString = input.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    /**
     * Requests the street name of the collaborator from the user.
     *
     * @return The street name entered by the user.
     */
    private String requestCollaboratorStreetName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Street Name: ");
        return input.nextLine();
    }

    /**
     * Requests the post code of the collaborator from the user.
     *
     * @return The post code entered by the user.
     */
    private String requestCollaboratorPostCode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Post Code: ");
        return input.nextLine();
    }

    /**
     * Requests the tax payer number of the collaborator from the user.
     *
     * @return The tax payer number entered by the user.
     */
    private int requestCollaboratorTaxPayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator TaxPayer: ");
        return input.nextInt();
    }

    /**
     * Requests the door number of the collaborator from the user.
     *
     * @return The door number entered by the user.
     */
    private int requestCollaboratorDoorNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Door Number: ");
        return input.nextInt();
    }

    /**
     * Requests the phone number of the collaborator from the user.
     *
     * @return The phone number entered by the user.
     */
    private String requestCollaboratorPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Phone Number: ");
        return input.nextLine();
    }

    /**
     * Requests the email address of the collaborator from the user.
     *
     * @return The email address entered by the user.
     */
    private String requestCollaboratorEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Email: ");
        return input.nextLine();
    }

    /**
     * Requests the type of document (Passport or Citizen Card) of the collaborator from the user.
     *
     * @return The type of document entered by the user.
     */
    private String requestCollaboratorDocType() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Document Type (Passport or Citizen Card): ");
        return input.nextLine();
    }

    /**
     * Requests the document number of the collaborator from the user.
     *
     * @return The document number entered by the user.
     */
    private int requestCollaboratorNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Collaborator Document Number: ");
        return input.nextInt();
    }


}
