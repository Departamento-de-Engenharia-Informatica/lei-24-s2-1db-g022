package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Optional;
import java.util.Scanner;

/**
 * The RegisterJobUI class represents the user interface for registering a job.
 * It prompts the user to input a job name and submits the data to create the job.
 * Instances of this class are runnable.
 *
 * @author Group22
 */
public class RegisterJobUI implements Runnable {

    private final RegisterJobController controller;
    private String jobName;

    /**
     * Constructs a RegisterJobUI object with a new RegisterJobController.
     */
    public RegisterJobUI() {
        controller = new RegisterJobController();
    }

    /**
     * Gets the RegisterJobController associated with this UI.
     * @return The RegisterJobController instance.
     */
    private RegisterJobController getController() {
        return controller;
    }

    /**
     * Executes the user interface, prompting the user to input a job name and submitting the data.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Register Job ------------------------");

        boolean register = false;

        while (!register){
            requestData();

            register = submitData();
        }
    }

    /**
     * Submits the job data to create the job and displays the result.
     *
     * @return True if the job is successfully created, false otherwise.
     */
    private boolean submitData() {

        boolean success = true;

        Optional<Job> job = getController().registerJob(jobName);

        if (job.isPresent()) {
            System.out.println("\nJob successfully created!");
            return success;
        } else {
            System.out.println("\nJob not created!");
            return !success;
        }
    }

    /**
     * Requests the job name from the user.
     */
    private void requestData() {
        jobName = requestJobName();
    }

    /**
     * Requests a job name input from the user.
     * @return The job name entered by the user.
     */
    private String requestJobName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Job Name: ");
        return input.nextLine();
    }
}



