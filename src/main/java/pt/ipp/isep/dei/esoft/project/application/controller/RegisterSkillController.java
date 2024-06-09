package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.model.Skill;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

/**
 * The RegisterSkillController class manages the registration of skills.
 *
 * @author Group22
 */
public class RegisterSkillController {

    private SkillRepository skillRepository;

    /**
     * Constructs a RegisterSkillController object with a default SkillRepository instance.
     */
    public RegisterSkillController() {
        getSkillRepository();
    }

    /**
     * Constructs a RegisterSkillController object with a specified SkillRepository instance.
     *
     * @param skillRepository The SkillRepository instance to use.
     */
    public RegisterSkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    /**
     * Retrieves the SkillRepository instance.
     * If not initialized, it gets the SkillRepository from the Repositories singleton.
     *
     * @return The SkillRepository instance.
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Registers a new skill with the given name.
     *
     * @param name The name of the skill to register.
     * @return An Optional containing the registered Skill, or empty if the registration fails.
     */
    public Optional<Skill> registerSkill(String name) {
        Optional<Skill> newSkill = Optional.empty();
        newSkill = getSkillRepository().createSkill(name);
        return newSkill;
    }
}
