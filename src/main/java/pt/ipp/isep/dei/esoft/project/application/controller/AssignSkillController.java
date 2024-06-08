package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.model.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.model.Job;
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
    private  SkillRepository skillRepository;
    private JobRepository jobRepository;


    public AssignSkillController(){
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

    private CollaboratorRepository getCollaboratorRepository(){
        if(collaboratorRepository == null){
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private JobRepository getJobRepository(){
        if(jobRepository == null){
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    private SkillRepository getSkillRepository(){
        if(skillRepository == null){
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
