package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * The Job class represents a job position.
 * It contains a name which serves as the identity of the job.
 * The name must only contain alphabetic characters.
 *
 * @author Group22
 */
public class Job {

    private String name;

    /**
     * Constructs a Job object with the specified name.
     *
     * @param name The name of the job. This is the identity of the job.
     * @throws IllegalArgumentException If the name contains characters other than alphabetic characters.
     */
    public Job(String name) {
        if (validateJob(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Job name must only include alphabetic characters");
        }
    }

    /**
     * Gets the name of the job.
     *
     * @return The name of the job.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the job.
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Validates if the job name contains only alphabetic characters.
     *
     * @param name The job name parameter.
     * @return True if the name only has alphabetic characters, false otherwise.
     */
    private boolean validateJob(String name) {
        // Regular expression to match only alphabetic characters and spaces
        String regex = "^[a-zA-Z ]+$";

        // Compile the regular expression pattern
        Pattern pattern = Pattern.compile(regex);

        // Check if the provided name is null or empty
        if (name == null || name.isEmpty()) {
            // If so, throw an IllegalArgumentException
            throw new IllegalArgumentException("Job name cannot be null or empty.");
        }

        // Match the name string against the pattern
        return pattern.matcher(name).matches();
    }

    /**
     * Returns a hash code value for the job.
     *
     * @return A hash code value for this job.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns a string representation of the job.
     *
     * @return A string representation of the job.
     */
    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this job.
     *
     * @param o The reference object with which to compare.
     * @return True if this job is the same as the object argument, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        Job job = (Job) o;
        return name.equals(job.name);
    }

    /**
     * Clones the job.
     *
     * @return A clone of the current job instance.
     */
    public Job clone() {
        return new Job(this.name);
    }
}
