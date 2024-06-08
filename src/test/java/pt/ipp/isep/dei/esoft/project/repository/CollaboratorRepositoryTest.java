package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Address;
import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.model.Job;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollaboratorRepositoryTest {

    /**
     * Tests if the createJob method successfully creates a job.
     */
    @Test
    void TestCreateCollaborator() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        Optional<Collaborator> result = collaboratorRepository.registerCollaborator("test", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988672", "tomas@tomas.pt", 123643820, "Passport", 123321121, new Job("Calceteiro"));

        assertTrue(result.isPresent());
    }

    /**
     * Tests if creating a job with a duplicate name fails.
     */
    @Test
    void ensureCreateAllDuplicate() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        collaboratorRepository.registerCollaborator("testt", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988672", "tomas@tomas.pt", 123643820, "Passport", 123321121, new Job("Calceteiro"));

        Optional<Collaborator> result = collaboratorRepository.registerCollaborator("testt", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988672", "tomas@tomas.pt", 123643820, "Passport", 123321121, new Job("Calceteiro"));

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureCreateTaxPayerDuplicate() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        collaboratorRepository.registerCollaborator("test", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988671", "toma1sas@tomas.pt", 123643824, "Passport", 123321122, new Job("Calceteiro"));

        Optional<Collaborator> result = collaboratorRepository.registerCollaborator("testt", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988672", "tomasas@tomas.pt", 123643824, "Passport", 123321121, new Job("Calceteiro"));

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureCreateEmailDuplicate() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        collaboratorRepository.registerCollaborator("test", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988671", "tomasas@tomas.pt", 123643821, "Passport", 123321122, new Job("Calceteiro"));

        Optional<Collaborator> result = collaboratorRepository.registerCollaborator("testt", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988672", "tomasas@tomas.pt", 123643820, "Passport", 123321121, new Job("Calceteiro"));

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureCreateNumberDuplicate() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        collaboratorRepository.registerCollaborator("test", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988671", "tomas@toma1s.pt", 123643821, "Passport", 123321121, new Job("Calceteiro"));

        Optional<Collaborator> result = collaboratorRepository.registerCollaborator("testt", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988672", "tomasas@tomas.pt", 123643820, "Passport", 123321121, new Job("Calceteiro"));

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureCreatePhoneDuplicate() {
        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();

        collaboratorRepository.registerCollaborator("test", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988672", "tomas@toma1s.pt", 123643821, "Passport", 123321121, new Job("Calceteiro"));

        Optional<Collaborator> result = collaboratorRepository.registerCollaborator("testt", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), new Address("streeName", "1234-123", 1), "+351 914988672", "tomasas@tomas.pt", 123643820, "Passport", 123321122, new Job("Calceteiro"));

        assertTrue(result.isEmpty());
    }
}
