package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Skill;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contains unit tests for the SkillRepository class.
 * Validates the functionality of the createSkill method.
 *
 * @author Group22
 */
public class SkillRepositoryTest {

    /**
     * Tests if the createSkill method successfully creates a skill.
     */
    @Test
    void TestCreateSkill() {
        SkillRepository skillRepository = new SkillRepository();

        Optional<Skill> result = skillRepository.createSkill("Podador");

        assertTrue(result.isPresent());
    }

    /**
     * Tests if creating a skill with a duplicate name fails.
     */
    @Test
    void ensureCreateSkillDuplicateFails() {
        SkillRepository skillRepository = new SkillRepository();

        skillRepository.createSkill("Podador");

        Optional<Skill> result = skillRepository.createSkill("Podador");

        assertTrue(result.isEmpty());
    }
}
