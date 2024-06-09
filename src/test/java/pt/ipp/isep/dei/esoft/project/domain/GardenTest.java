package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Address;
import pt.ipp.isep.dei.esoft.project.domain.model.Garden;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;

import static org.junit.jupiter.api.Assertions.*;

public class GardenTest {

    @Test
    @DisplayName("GreenSpace Is Created Successfully")
    void ensureGreenSpaceIsCreatedSuccessfully() {
       new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
    }

    @Test
    @DisplayName("Has Same Name")
    void ensureHasSameNameSuccessfully() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertTrue(garden.hasName("Jardim Porto"));

    }

    @Test
    @DisplayName("Has Differents Names")
    void ensureHasSameNameInsuccessfully() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertFalse(garden.hasName("Jardim Lisboa"));

    }

    @Test
    @DisplayName("Is Area Large")
    void ensureIsAreaLargeSuccessfully() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertTrue(garden.isAreaLarger(1));
    }

    @Test
    @DisplayName("Is Not Area Large")
    void ensureIsNotAreaLargeSuccessfully() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertFalse(garden.isAreaLarger(5));
    }

    @Test
    @DisplayName("Invalid Address PostCode")
    void ensureInvalidAddressPostCode() {

        assertThrows(IllegalArgumentException.class,
                () ->new Garden("Jardim Porto",3,"street Porto",12,"1234-12","Porto"));
        assertThrows(IllegalArgumentException.class,
                () ->new Garden("Jardim Porto",3,"street Porto",12,"123-122","Porto"));
        assertThrows(IllegalArgumentException.class,
                () ->new Garden("Jardim Porto",3,"street Porto",12,"123412","Porto"));
    }

    @Test
    @DisplayName("Invalid Invalid Area")
    void ensureInvalidArea() {

        assertThrows(IllegalArgumentException.class,
                () ->new Garden("Jardim Porto",0,"street Porto",12,"1234-12","Porto"));
        assertThrows(IllegalArgumentException.class,
                () ->new Garden("Jardim Porto",-1,"street Porto",12,"1234-12","Porto"));
    }

    @Test
    @DisplayName("Equals")
    void testEqualsSameObject() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertEquals(garden, garden);
    }

    @Test
    @DisplayName("Equals Different")
    void testEqualsDifferentClass() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertNotEquals(garden, new Object());
    }

    @Test
    @DisplayName("Equals Null")
    void testEqualsNull() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertNotEquals(garden, null);
    }

    @Test
    @DisplayName("Differents Object")
    void testEqualsDifferentObject() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden1 = new Garden("Jardim Lisboa",3,"street Porto",12,"1234-123","Porto");

        assertNotEquals(garden, garden1);
    }

    @Test
    @DisplayName("Same Object")
    void testEqualsSameObjectSame() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden1 = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        assertEquals(garden, garden1);
    }

    @Test
    @DisplayName("Hash Code Object")
    void testHashCodeSameObject() {
        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        assertEquals(garden.hashCode(), garden.hashCode());
    }

}
