# US04 - As an HRM, I want to assign one or more skills to a collaborator.

## 4. Tests 

n/a

## 5. Construction (Implementation)

### Class AssignSkillController

```java
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;
pt.ipp.isep.dei.esoft.project.domain.model.Job;
import pt.ipp.isep.dei.esoft.project.domain.model.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.List;
import java.util.Optional;

/**
 * The AssignSkillController class manages the assignment of skills to collaborators.
 */
public class AssignSkillController {

    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;
    private JobRepository jobRepository;


    public AssignSkillController() {
        getCollaboratorRepository();
        getJobRepository();
        getSkillRepository();
    }

    /**
     * Assigns a skill to a collaborator with the given ID.
     *
     * @param collaboratorName The ID of the collaborator.
     * @param skillName      The name of the skill to assign.
     * @return True if the skill is successfully assigned to the collaborator, false otherwise.
     */
    public boolean assignSkillToCollaborator(String collaboratorName, String skillName) {
        // Retrieve collaborator from repository
        Optional<Collaborator> optionalCollaborator = collaboratorRepository.getCollaboratorByName(collaboratorName);

        if (optionalCollaborator.isPresent()) {
            Collaborator collaborator = optionalCollaborator.get();

            // Retrieve skill from repository
            Skill skill = skillRepository.getSkillByName(skillName);

            // Check if the collaborator already has the skill
            if (collaborator.hasCollaboratorSkill(skill)) {
                System.out.println("Collaborator already has the skill.");
                return false;
            }

            // Assign skill to collaborator
            collaborator.addSkillCollaboratorBootStrap(skill);

            System.out.println("Skill successfully assigned to collaborator.");
            return true;
        } else {
            System.out.println("Collaborator not found.");
            return false;
        }
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }


    /**
     * Retrieves a list of all available skills.
     *
     * @return A list of all available skills.
     */
    public List<Skill> getAllSkills() {
        return skillRepository.getSkillList();
    }

    /**
     * Retrieves a list of all available jobs.
     *
     * @return A list of all available jobs.
     */
    public List<Job> getAllJobs() {
        return jobRepository.getJobList();
    }
}

```

### Class Skill

```java
package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;
import java.util.regex.Pattern;

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
     * @throws IllegalArgumentException If the name contains characters other than alphabetic characters.
     */
    public Skill(String name) {
        if (validateSkill(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Skill name must only include alphabetic characters");
        }
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
     * Sets the name of the skill.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Validates if the skill name contains only alphabetic characters.
     *
     * @param name The skill name parameter.
     * @return True if the name only has alphabetic characters, false otherwise.
     */
    private boolean validateSkill(String name) {
        // Regular expression to match only alphabetic characters and spaces
        String regex = "^[a-zA-Z ]+$";

        // Compile the regular expression pattern
        Pattern pattern = Pattern.compile(regex);

        // Check if the provided name is null or empty
        if (name == null || name.isEmpty()) {
            // If so, throw an IllegalArgumentException
            throw new IllegalArgumentException("Skill name cannot be null or empty.");
        }

        // Match the name string against the pattern
        return pattern.matcher(name).matches();
    }

    /**
     * Returns a hash code value for the skill.
     *
     * @return A hash code value for this skill.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
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
    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return True if this object is the same as the object argument, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Skill)) return false;
        Skill skill = (Skill) object;
        return Objects.equals(name, skill.name);
    }

    /**
     * Clones the skill.
     *
     * @return A clone of the current skill instance.
     */
    public Skill clone() {
        return new Skill(this.name);
    }
}

```


## 6. Integration and Demo 

n/a

## 7. Observations

n/a