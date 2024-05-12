package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        Team team = new Team(new ArrayList<>());
        Team team1 = new Team(new ArrayList<>());

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
        Team team = new Team(new ArrayList<>());
        Team team1 = new Team(new ArrayList<>());

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
