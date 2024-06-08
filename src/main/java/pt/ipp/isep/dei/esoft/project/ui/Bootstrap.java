package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.sql.Date;

/**
 * The Bootstrap class initializes the application by adding initial data such as users and jobs.
 * It implements the Runnable interface to allow execution in a separate thread.
 *
 * @author Group22
 */
public class Bootstrap implements Runnable {

    /**
     * Runs the Bootstrap process by adding users and jobs to the application.
     */
    public void run() {
        addUsersDefault();
        addJobs();
        addSkills();
        addBrands();
        addCollaborators();
        addVehicles();
    }

    /**
     * Adds sample collaborators to the collaborator repository for bootstrapping purposes.
     */
    private void addCollaborators() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();

        // Create sample collaborators
        Collaborator collaborator = new Collaborator("luigy", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988676", "luigy@luigy.pt", new Address("streeName", "1234-123", 1), 123456789, "Passport", 123321123, new Job("Jardineiro"));
        Collaborator collaborator2 = new Collaborator("daniel", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988671", "daniel@daniel.pt", new Address("streeName", "1234-123", 1), 123456780, "Passport", 123321125, new Job("Calceteiro"));
        Collaborator collaborator3 = new Collaborator("tomas", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988672", "tomas@tomas.pt", new Address("streeName", "1234-123", 1), 123643879, "Passport", 123321121, new Job("Calceteiro"));
        Collaborator collaborator4 = new Collaborator("diogo", Date.valueOf("1999-01-10"), Date.valueOf("1999-01-10"), "+351 914988673", "diogo@diogo.pt", new Address("streeName", "1234-123", 1), 675432564, "Passport", 123321122, new Job("Calceteiro"));
        GreenSpaceManager greenSpaceManager = new GreenSpaceManager("gsname", Date.valueOf("1999-01-10"), Date.valueOf("2024-01-10"), "+351 914981073", "gsm@gsm.pt", new Address("streeName", "1234-123", 1), 675432501, "Passport", 123021022, new Job("Gestor"));

        // Add skills to collaborators
        collaborator.addSkillCollaboratorBootStrap(new Skill("Podador"));
        collaborator.addSkillCollaboratorBootStrap(new Skill("Condutor"));

        collaborator2.addSkillCollaboratorBootStrap(new Skill("Podador"));
        collaborator2.addSkillCollaboratorBootStrap(new Skill("Condutor"));

        collaborator3.addSkillCollaboratorBootStrap(new Skill("Podador"));
        collaborator3.addSkillCollaboratorBootStrap(new Skill("Condutor"));

        collaborator4.addSkillCollaboratorBootStrap(new Skill("Podador"));
        collaborator4.addSkillCollaboratorBootStrap(new Skill("Condutor"));

        // Add collaborators to the repository
        collaboratorRepository.addCollaboratorBootstrap(collaborator);
        collaboratorRepository.addCollaboratorBootstrap(collaborator2);
        collaboratorRepository.addCollaboratorBootstrap(collaborator3);
        collaboratorRepository.addCollaboratorBootstrap(collaborator4);

        collaboratorRepository.addCollaboratorBootstrap(greenSpaceManager);

        collaboratorRepository.ver();

        addUsersCollaborator(collaborator);
        addUsersCollaborator(collaborator2);
        addUsersCollaborator(collaborator3);
        addUsersCollaborator(collaborator4);
        addUsersGSM(greenSpaceManager);
    }


    /**
     * Adds predefined jobs to the job repository.
     */
    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.createJob("Jardineiro");
        jobRepository.createJob("Calceteiro");
        jobRepository.createJob("Electricista");
        jobRepository.createJob("Condutor");
        jobRepository.createJob("Gestor");

    }

    /**
     * Adds predefined skills to the skill repository.
     */
    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        skillRepository.createSkill("Podador");
        skillRepository.createSkill("Condutor");
        skillRepository.createSkill("Aplicador de fito farmacos");
        skillRepository.createSkill("Light Vehicle Driving Licence");
        skillRepository.createSkill("Illustrator");
        skillRepository.createSkill("Photoshop");
        skillRepository.createSkill("Java");
        skillRepository.createSkill("Python");

    }

    private void addVehicles() {
        VehicleRepository vehicleRepo = Repositories.getInstance().getVehicleRepository();

        Vehicle vehicle = new Vehicle("Pesado", 12, 1, 200, Date.valueOf("2021-01-10"), Date.valueOf("2021-01-11"), 500, "AA-00-AA", new Brand("BMW"), new Model("XM"));
        Vehicle vehicle2 = new Vehicle("Ligeiro", 12, 1, 200, Date.valueOf("2021-01-10"), Date.valueOf("2021-01-12"), 501, "BB-00-AA", new Brand("BMW"), new Model("XM"));

        vehicleRepo.addVehicleBootstrap(vehicle);
        vehicleRepo.addVehicleBootstrap(vehicle2);

    }

    /**
     * Adds predefined brands and models to the brands repository.
     */
    private void addBrands() {
        BrandRepository brandRepository = Repositories.getInstance().getBrandRepository();
        Brand brand1 = new Brand("BMW");
        Brand brand2 = new Brand("Opel");
        Brand brand3 = new Brand("Citroen");

        brand1.addModel(new Model("XM"));
        brand1.addModel(new Model("X4 M"));
        brand1.addModel(new Model("Série 7 Berlina"));

        brand2.addModel(new Model("Astra"));
        brand2.addModel(new Model("Corsa"));
        brand2.addModel(new Model("Zafira"));

        brand3.addModel(new Model("C3 Origin"));
        brand3.addModel(new Model("C4"));
        brand3.addModel(new Model("C5 Aircross"));

        brandRepository.addBrand(brand1);
        brandRepository.addBrand(brand2);
        brandRepository.addBrand(brand3);
    }


    /**
     * Adds predefined users with their roles to the authentication repository.
     */
    private void addUsersDefault() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);

        authenticationRepository.addUserWithRole("Main Administrator", "1191330@isep.ipp.pt", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "1170499@isep.ipp.pt", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "1191377@isep.ipp.pt", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "1200356@isep.ipp.pt", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "admin@admin.pt", "admin", AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Human Resource Manager", "hrm@musgosublime.pt", "hrm", AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserWithRole("Vehicle and Equipment Fleet Manager", "vfm@musgosublime.pt", "vfm", AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserWithRole("Green Spaces Manager", "gsm@musgosublime.pt", "gsm", AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserWithRole("Software Quality Assessment Team Manager", "qam@musgosublime.pt", "qam", AuthenticationController.ROLE_QAM);
    }

    /**
     * Adds predefined users with their roles to the authentication repository.
     */
    private void addUsersGSM(GreenSpaceManager greenSpaceManager) {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole(greenSpaceManager.getName(), greenSpaceManager.getEmail(), "gsm", AuthenticationController.ROLE_GSM);
    }

    /**
     * Adds predefined users with their roles to the authentication repository.
     */
    private void addUsersCollaborator(Collaborator collaborator) {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLABORATOR, AuthenticationController.ROLE_COLLABORATOR);

        authenticationRepository.addUserWithRole(collaborator.getName(), collaborator.getEmail(), "col", AuthenticationController.ROLE_COLLABORATOR);
    }
}
