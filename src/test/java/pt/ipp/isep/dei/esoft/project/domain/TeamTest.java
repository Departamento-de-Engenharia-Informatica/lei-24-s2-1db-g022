package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Address;
import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.model.Job;
import pt.ipp.isep.dei.esoft.project.domain.model.Team;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TeamTest {

    /**
     * Tests if a model is created successfully with a valid name.
     */
    @Test
    void ensureModelIsCreatedSuccessfully() {
        Team team = new Team(new ArrayList<>());
    }

    /**
     * Tests if two references to the same object are considered equal.
     */
    @Test
    void testEqualsSameObject() {

        Team team = new Team(new ArrayList<>());

        assertEquals(team, team);
    }

    /**
     * Tests if a Team object is not equal to an object of a different class.
     */
    @Test
    void testEqualsDifferentClass() {

        Team team = new Team(new ArrayList<>());

        assertNotEquals(team, new Object());
    }

    /**
     * Tests if a Team object is not equal to null.
     */
    @Test
    void testEqualsNull() {
        Team team = new Team(new ArrayList<>());

        assertNotEquals(team, null);
    }

    /**
     * Tests if two different Team objects with different names are not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        List<Collaborator> collaboratorList = new ArrayList<>();
        collaboratorList.add(collaborator);
        Team team = new Team(new ArrayList<>());
        Team team1 = new Team(collaboratorList);

        assertNotEquals(team, team1);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {
        Team team = new Team(new ArrayList<>());

        assertEquals(team.hashCode(), team.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {
        Collaborator collaborator = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        List<Collaborator> collaboratorList = new ArrayList<>();
        collaboratorList.add(collaborator);
        Team team = new Team(new ArrayList<>());
        Team team1 = new Team(collaboratorList);

        assertNotEquals(team.hashCode(), team1.hashCode());
    }

    /**
     * Tests if the clone method creates a new Model object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Team team = new Team(new ArrayList<>());
        Team clone = team.clone();
        assertEquals(team, clone);
    }

}
