package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contains unit tests for the JobRepository class.
 * Validates the functionality of the createJob method.
 *
 * @author Group22
 */
public class JobRepositoryTest {

    /**
     * Tests if the createJob method successfully creates a job.
     */
    @Test
    void TestCreateJob() {
        JobRepository jobRepository = new JobRepository();

        Optional<Job> result = jobRepository.createJob("Condutor");

        assertTrue(result.isPresent());
    }

    /**
     * Tests if creating a job with a duplicate name fails.
     */
    @Test
    void ensureCreateJobDuplicateFails() {
        JobRepository jobRepository = new JobRepository();

        jobRepository.createJob("Condutor");

        Optional<Job> result = jobRepository.createJob("Condutor");

        assertTrue(result.isEmpty());
    }
}
