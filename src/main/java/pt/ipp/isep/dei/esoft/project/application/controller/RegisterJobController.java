package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.domain.model.Job;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

/**
 * The RegisterJobController class manages the registration of jobs.
 *
 * @author Group22
 */
public class RegisterJobController {

    private JobRepository jobRepository;

    /**
     * Constructs a RegisterJobController object with a default JobRepository instance.
     */
    public RegisterJobController() {
        getJobRepository();
    }

    /**
     * Constructs a RegisterJobController object with a specified JobRepository instance.
     *
     * @param jobRepository The JobRepository instance to use.
     */
    public RegisterJobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Retrieves the JobRepository instance.
     * If not initialized, it gets the JobRepository from the Repositories singleton.
     *
     * @return The JobRepository instance.
     */
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    /**
     * Registers a new job with the given name.
     *
     * @param name The name of the job to register.
     * @return An Optional containing the registered Job, or empty if the registration fails.
     */
    public Optional<Job> registerJob(String name) {
        Optional<Job> newJob = Optional.empty();
        newJob = getJobRepository().createJob(name);
        return newJob;
    }
}
