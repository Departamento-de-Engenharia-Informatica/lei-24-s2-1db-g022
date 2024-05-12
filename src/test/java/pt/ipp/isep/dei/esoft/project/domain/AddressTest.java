package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    void ensureAdddressIsCreatedSuccessfully() {
        Address address = new Address("Address", "1234-123", 12);
    }

    @Test
    void ensureInvalidAddressPostCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Address("Address", "23", 12));
        assertThrows(IllegalArgumentException.class,
                () -> new Address("Address", "123-2123", 12));
        assertThrows(IllegalArgumentException.class,
                () -> new Address("Address", "122112-1223", 12));
        assertThrows(IllegalArgumentException.class,
                () -> new Address("Address", "1223-11", 12));
    }

    @Test
    void testEqualsSameObject() {

        Address address = new Address("Address", "1234-123", 12);

        assertEquals(address, address);
    }

    @Test
    void testEqualsDifferentClass() {

        Address address = new Address("Address", "1234-123", 12);

        assertNotEquals(address, new Object());
    }

    @Test
    void testEqualsNull() {
        Address address = new Address("Address", "1234-123", 12);

        assertNotEquals(address, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Address address = new Address("Address", "1234-123", 12);
        Address address1 = new Address("Address", "1214-123", 12);

        assertNotEquals(address, address1);
    }

    @Test
    void testEqualsSameObjectSame() {
        Address address = new Address("Address", "1234-123", 12);
        Address address1 = new Address("Address", "1234-123", 12);

        assertEquals(address, address1);
    }

    @Test
    void testHashCodeSameObject() {
        Address address = new Address("Address", "1234-123", 12);

        assertEquals(address.hashCode(), address.hashCode());
    }

    /**
     * Tests if the clone method creates a new Brand object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Address address = new Address("Address", "1234-123", 12);
        Address clone = address.clone();
        assertEquals(address, clone);
    }

}
