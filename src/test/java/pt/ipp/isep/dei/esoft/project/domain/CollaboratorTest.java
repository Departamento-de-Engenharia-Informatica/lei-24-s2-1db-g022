package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Address;
import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.model.Job;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CollaboratorTest {
    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
    }

    @Test
    void ensureInvalidCollaboratorValidateName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("tomasdsadsadsa", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro")));
    }

    @Test
    void ensureInvalidCollaboratorValidateEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro")));
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "@email.com", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro")));
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "@", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro")));
    }

    @Test
    void ensureInvalidCollaboratorValidatePhone() {
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 21399323323232", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro")));
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 213", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro")));

    }

    @Test
    void ensureInvalidCollaboratorTaxPayer() {
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914912321", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123, "Passport", 123321121, new Job("Calceteiro")));
    }

    @Test
    void testEqualsSameObject() {

        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));

        assertEquals(collaborator, collaborator);
    }

    @Test
    void testEqualsDifferentClass() {

        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));

        assertNotEquals(collaborator, new Object());
    }

    /**
     * Tests if a Collaborator object is not equal to null.
     */
    @Test
    void testEqualsNull() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));

        assertNotEquals(collaborator, null);
    }

    /**
     * Tests if two different Collaborators objects with different not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator collaboratorTeste = new Collaborator("tomasl", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988671", "toma3@tomas.pt", new Address("streeName", "1234-123", 1), 123643871, "Passport", 123321124, new Job("Calceteiro"));

        assertNotEquals(collaborator, collaboratorTeste);
    }

    /**
     * Tests if two Collaborators objects are considered equal.
     */
    @Test
    void testEqualsSameObjectSame() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator collaborator2 = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));

        assertEquals(collaborator, collaborator2);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));

        assertEquals(collaborator.hashCode(), collaborator.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator collaboratorTeste = new Collaborator("tomasp", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988671", "toma3@tomas.pt", new Address("streeName", "1234-123", 1), 123643871, "Passport", 123321124, new Job("Calceteiro"));

        assertNotEquals(collaborator.hashCode(), collaboratorTeste.hashCode());
    }

    /**
     * Tests if the clone method creates a new Brand object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator clone = collaborator.clone();
        assertEquals(collaborator, clone);
    }

    @Test
    void validateEmailTest() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator collaboratorTesteTrue = new Collaborator("tomasp", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988671", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643871, "Passport", 123321124, new Job("Calceteiro"));
        Collaborator collaboratorTesteFalse = new Collaborator("tomasp", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988671", "tomaFalse@tomas.pt", new Address("streeName", "1234-123", 1), 123643871, "Passport", 123321124, new Job("Calceteiro"));

        assertTrue(collaborator.hasEqualsEmail(collaboratorTesteTrue));
        assertFalse(collaborator.hasEqualsEmail(collaboratorTesteFalse));
    }

    @Test
    void validateTaxPayerTest() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator collaboratorTesteTrue = new Collaborator("tomas", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988671", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321124, new Job("Calceteiro"));
        Collaborator collaboratorTesteFalse = new Collaborator("tomas", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988671", "tomaFalse@tomas.pt", new Address("streeName", "1234-123", 1), 123643871, "Passport", 123321124, new Job("Calceteiro"));

        assertTrue(collaborator.hasEqualsTaxNumber(collaboratorTesteTrue));
        assertFalse(collaborator.hasEqualsTaxNumber(collaboratorTesteFalse));
    }

    @Test
    void validatePhoneNumberTest() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator collaboratorTesteTrue = new Collaborator("tomasl", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321124, new Job("Calceteiro"));
        Collaborator collaboratorTesteFalse = new Collaborator("tomasl", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988675", "tomaFalse@tomas.pt", new Address("streeName", "1234-123", 1), 123643871, "Passport", 123321124, new Job("Calceteiro"));

        assertTrue(collaborator.hasEqualsPhoneNumber(collaboratorTesteTrue));
        assertFalse(collaborator.hasEqualsPhoneNumber(collaboratorTesteFalse));
    }

    @Test
    void validateTest() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator collaboratorTesteTrue = new Collaborator("tomasl", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321124, new Job("Calceteiro"));
        Collaborator collaboratorTesteFalse = new Collaborator("tomasl", Date.valueOf("1996-01-10"), Date.valueOf("2023-01-10"), "+351 914988675", "tomaFalse@tomas.pt", new Address("streeName", "1234-123", 1), 123643871, "Passport", 123321124, new Job("Calceteiro"));

        assertTrue(collaborator.hasEqualsPhoneNumber(collaboratorTesteTrue));
        assertFalse(collaborator.hasEqualsPhoneNumber(collaboratorTesteFalse));
    }


}
