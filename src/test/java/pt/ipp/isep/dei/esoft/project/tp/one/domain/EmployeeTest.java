package pt.ipp.isep.dei.esoft.project.tp.one.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void ensureTwoEmployeesWithSameEmailEquals() {
        Employee employee1 = new Employee("john.doe@this.company.org");
        Employee employee2 = new Employee("john.doe@this.company.org");
        assertEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeWithDifferentEmailNotEquals() {
        Employee employee1 = new Employee("john.doe@this.company.org");
        Employee employee2 = new Employee("jane.doe@this.company.org");
        assertNotEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeDoesNotEqualNull() {
        Employee employee1 = new Employee("john.doe@this.company.org");
        assertNotEquals(employee1, null);
    }

    @Test
    void ensureEmployeeDoesNotEqualOtherObject() {
        Employee employee1 = new Employee("john.doe@this.company.org");
        assertNotEquals(employee1, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        Employee employee1 = new Employee("john.doe@this.company.org");
        assertEquals(employee1, employee1);
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String email = "john.doe@this.company.org";
        Employee employee1 = new Employee(email);
        Employee employee2 = new Employee(email);
        assertEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        Employee employee1 = new Employee("john.doe@this.company.org");
        Employee employee2 = new Employee("jane.doe@this.company.org");
        assertNotEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    void ensureHasEmailWorksForTheSameEmail() {
        String email = "john.doe@this.compay.org";
        Employee employee = new Employee(email);
        assertTrue(employee.hasEmail(email));

    }

    @Test
    void ensureHasEmailFailsForDifferentEmails() {
        String email = "john.doe@this.company.org";
        Employee employee = new Employee(email);
        assertFalse(employee.hasEmail("jane.doe@this.company.org"));

    }

}