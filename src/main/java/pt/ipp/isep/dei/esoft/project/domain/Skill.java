package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The Skill class represents a skill possessed by a collaborator.
 * It contains a name which serves as the identity of the skill.
 *
 * @author Group22
 */
public class Skill {

    private String name;

    /**
     * Constructs a Skill object with the specified name.
     *
     * @param name The name of the skill. This is the identity of the skill.
     */
    public Skill(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the skill.
     *
     * @return The name of the skill.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the skill.
     *
     * @return A string representation of the skill.
     */
    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}
