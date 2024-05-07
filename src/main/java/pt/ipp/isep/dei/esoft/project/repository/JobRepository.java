package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The JobRepository class manages the storage and retrieval of job objects.
 * It provides methods for adding and retrieving jobs from the repository.
 *
 * @author Group22
 */
public class JobRepository {

    private final List<Job> jobList;

    /**
     * Constructs a JobRepository object.
     */
    public JobRepository() {
        jobList = new ArrayList<>();
    }

    /**
     * Adds a job to the repository.
     *
     * @param job The job to add.
     * @return True if the job is successfully added, false otherwise.
     */
    private boolean addJob(Job job){
        boolean success = false;
        if (validateJob(job)) {
            // A clone of the job is added to the list of jobs, to avoid side effects and outside manipulation.
            success = jobList.add(job.clone());
        }
        return success;
    }

    /**
     * Validates if the job is not already present in the repository.
     *
     * @param job The job to validate.
     * @return True if the job is valid (not already present), false otherwise.
     */
    private boolean validateJob(Job job) {
        return !jobList.contains(job);
    }

    /**
     * Creates a new job with the specified name and adds it to the repository.
     *
     * @param name The name of the job.
     * @return An optional containing the created job if successful, empty optional otherwise.
     */
    public Optional<Job> createJob(String name) {
        // When a Job is added, it should fail if the Job already exists in the list of Jobs.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Job> optionalJob = Optional.empty();
        Job job = new Job(name);
        if (addJob(job)) {
            optionalJob = Optional.of(job);
        }
        return optionalJob;
    }
}
