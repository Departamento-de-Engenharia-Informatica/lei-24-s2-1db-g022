package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@musgosublime.pt", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Human Resource Manager", "hrm@musgosublime.pt", "hrm",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("Vehicle and Equipment Fleet Manager", "vfm@musgosublime.pt", "vfm",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Employee", "employee@musgosublime.pt", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);
    }
}