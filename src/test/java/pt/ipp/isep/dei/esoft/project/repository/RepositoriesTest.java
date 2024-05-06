package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Contains unit tests for the Repositories class.
 * Validates the functionality of the getInstance and getJobRepository methods.
 *
 * @author Group22
 */
public class RepositoriesTest {

    /**
     * Tests if the getInstance method returns a non-null instance of Repositories.
     */
    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

    /**
     * Tests if the getJobRepository method returns a non-null instance of JobRepository.
     */
    @Test
    void testGetJobRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getJobRepository());
    }
}
