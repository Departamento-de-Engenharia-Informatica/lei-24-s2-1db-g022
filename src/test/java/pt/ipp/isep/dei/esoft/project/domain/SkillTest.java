package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The SkillTest class contains unit tests for the Skill class.
 * It validates the behavior of the Skill class methods.
 * Each test method focuses on a specific aspect or scenario related to the Skill class functionality.
 *
 * @author Group22
 */
class SkillTest {

    /**
     * Tests if a skill is created successfully with a valid name.
     */
    @Test
    void ensureSkillIsCreatedSuccessfully() {
        Skill skill = new Skill("Podador");
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a skill with a null name.
     */
    @Test
    void ensureSkillIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new Skill(null));
    }

    /**
     * Tests if an IllegalArgumentException is thrown when attempting to create a skill with a name containing numbers.
     */
    @Test
    void testSkillHasNumbers() {

        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Skill("Podador2"));
    }

    /**
     * Tests if two references to the same object are considered equal.
     */
    @Test
    void testEqualsSameObject() {

        Skill skill = new Skill("Podador");

        assertEquals(skill, skill);
    }

    /**
     * Tests if a Skill object is not equal to an object of a different class.
     */
    @Test
    void testEqualsDifferentClass() {

        Skill skill = new Skill("Podador");

        assertNotEquals(skill, new Object());
    }

    /**
     * Tests if a Skill object is not equal to null.
     */
    @Test
    void testEqualsNull() {
        Skill skill = new Skill("Podador");

        assertNotEquals(skill, null);
    }

    /**
     * Tests if two different Skill objects with different names are not considered equal.
     */
    @Test
    void testEqualsDifferentObject() {
        Skill skill = new Skill("Podador");
        Skill skill1 = new Skill("Heavy Vehicle Driving Licence");

        assertNotEquals(skill, skill1);
    }

    /**
     * Tests if two Skill objects with the same name are considered equal.
     */
    @Test
    void testEqualsSameObjectSameName() {
        Skill skill = new Skill("Podador");
        Skill skill1 = new Skill("Podador");

        assertEquals(skill, skill1);
    }

    /**
     * Tests if the hashCode method returns the same hash code for the same object.
     */
    @Test
    void testHashCodeSameObject() {
        Skill skill = new Skill("Podador");

        assertEquals(skill.hashCode(), skill.hashCode());
    }

    /**
     * Tests if the hashCode method returns different hash codes for different objects.
     */
    @Test
    void testHashCodeDifferentObject() {
        Skill skill = new Skill("Podador");
        Skill skill1 = new Skill("Heavy Vehicle Driving Licence");

        assertNotEquals(skill.hashCode(), skill1.hashCode());
    }

    /**
     * Tests if the clone method creates a new Skill object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Skill skill = new Skill("Podador");
        Skill clone = skill.clone();
        assertEquals(skill, clone);
    }
}