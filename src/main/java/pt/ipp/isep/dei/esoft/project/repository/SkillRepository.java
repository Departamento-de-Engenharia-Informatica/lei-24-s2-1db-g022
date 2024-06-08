package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The SkillRepository class manages the storage and retrieval of skill objects.
 * It provides methods for adding and retrieving skills from the repository.
 *
 * @author Group22
 */
public class SkillRepository {

    private final List<Skill> skillList;

    /**
     * Constructs a SkillRepository object.
     */
    public SkillRepository() {
        skillList = new ArrayList<>();
    }

    /**
     * Adds a skill to the repository.
     *
     * @param skill The skill to add.
     * @return True if the skill is successfully added, false otherwise.
     */
    private boolean addSkill(Skill skill) {
        boolean success = false;
        if (validateSkill(skill)) {
            // A clone of the skill is added to the list of skills, to avoid side effects and outside manipulation.
            success = skillList.add(skill.clone());
        }
        return success;
    }

    /**
     * Validates if the skill is not already present in the repository.
     *
     * @param skill The skill to validate.
     * @return True if the skill is valid (not already present), false otherwise.
     */
    private boolean validateSkill(Skill skill) {
        return !skillList.contains(skill);
    }

    /**
     * Creates a new skill with the specified name and adds it to the repository.
     *
     * @param name The name of the skill.
     * @return An optional containing the created skill if successful, empty optional otherwise.
     */
    public Optional<Skill> createSkill(String name) {
        // When a Skill is added, it should fail if the Skill already exists in the list of Skills.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Skill> optionalSkill = Optional.empty();
        Skill skill = new Skill(name);
        if (addSkill(skill)) {
            optionalSkill = Optional.of(skill);
        }
        return optionalSkill;
    }

    /**
     * Retrieves an unmodifiable list of all skills.
     *
     * @return An unmodifiable list of all skills.
     */
    public List<Skill> getSkillList() {
        return List.copyOf(skillList);
    }

    /**
     * Retrieves a skill by its name.
     *
     * @param skillName The name of the skill to retrieve.
     * @return The skill with the specified name.
     * @throws IllegalArgumentException if the skill with the given name does not exist.
     */
    public Skill getSkillByName(String skillName) {
        Skill newSkill = new Skill(skillName);
        Skill skill = null;

        if (skillList.contains(newSkill)) {
            skill = skillList.get(skillList.indexOf(newSkill));
        }

        if (skill == null) {
            throw new IllegalArgumentException(
                    "Skill requested for [" + skillName + "] does not exist.");
        }
        return skill;
    }
}
