package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Brand;
import pt.ipp.isep.dei.esoft.project.domain.Model;
import pt.ipp.isep.dei.esoft.project.repository.*;

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
        addUsers();
        addJobs();
        addSkills();
        addBrands();
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
    }

    /**
     * Adds predefined skills to the skill repository.
     */
    private void addSkills() {
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        skillRepository.createSkill("Podador");
        skillRepository.createSkill("Condutor de veiculos pesados");
        skillRepository.createSkill("Aplicador de fito farmacos");
        skillRepository.createSkill("Light Vehicle Driving Licence");
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
    private void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE, AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Main Administrator", "1191330@isep.ipp.pt", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "1170499@isep.ipp.pt", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "1191337@isep.ipp.pt", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "1200356@isep.ipp.pt", "admin", AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "admin@admin.pt", "admin", AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Human Resource Manager", "hrm@musgosublime.pt", "hrm", AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserWithRole("Vehicle and Equipment Fleet Manager", "vfm@musgosublime.pt", "vfm", AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserWithRole("Employee", "employee@musgosublime.pt", "pwd", AuthenticationController.ROLE_EMPLOYEE);
    }
}
