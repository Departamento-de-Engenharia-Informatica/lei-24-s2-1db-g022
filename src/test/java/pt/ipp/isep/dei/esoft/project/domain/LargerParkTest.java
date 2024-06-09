package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.model.LargePark;

import static org.junit.jupiter.api.Assertions.*;

public class LargerParkTest {

    @Test
    @DisplayName("GreenSpace Is Created Successfully")
    void ensureGreenSpaceIsCreatedSuccessfully() {
       new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
    }

    @Test
    @DisplayName("Has Same Name")
    void ensureHasSameNameSuccessfully() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertTrue(park.hasName("Jardim Porto"));

    }

    @Test
    @DisplayName("Has Differents Names")
    void ensureHasSameNameInsuccessfully() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertFalse(park.hasName("Jardim Lisboa"));

    }

    @Test
    @DisplayName("Is Area Large")
    void ensureIsAreaLargeSuccessfully() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertTrue(park.isAreaLarger(1));
    }

    @Test
    @DisplayName("Is Not Area Large")
    void ensureIsNotAreaLargeSuccessfully() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertFalse(park.isAreaLarger(5));
    }

    @Test
    @DisplayName("Invalid Address PostCode")
    void ensureInvalidAddressPostCode() {

        assertThrows(IllegalArgumentException.class,
                () ->new LargePark("Jardim Porto",3,"street Porto",12,"1234-12","Porto"));
        assertThrows(IllegalArgumentException.class,
                () ->new LargePark("Jardim Porto",3,"street Porto",12,"123-122","Porto"));
        assertThrows(IllegalArgumentException.class,
                () ->new LargePark("Jardim Porto",3,"street Porto",12,"123412","Porto"));
    }

    @Test
    @DisplayName("Invalid Invalid Area")
    void ensureInvalidArea() {

        assertThrows(IllegalArgumentException.class,
                () ->new LargePark("Jardim Porto",0,"street Porto",12,"1234-12","Porto"));
        assertThrows(IllegalArgumentException.class,
                () ->new LargePark("Jardim Porto",-1,"street Porto",12,"1234-12","Porto"));
    }

    @Test
    @DisplayName("Equals")
    void testEqualsSameObject() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertEquals(park, park);
    }

    @Test
    @DisplayName("Equals Different")
    void testEqualsDifferentClass() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertNotEquals(park, new Object());
    }

    @Test
    @DisplayName("Equals Null")
    void testEqualsNull() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        assertNotEquals(park, null);
    }

    @Test
    @DisplayName("Differents Object")
    void testEqualsDifferentObject() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace park1 = new LargePark("Jardim Lisboa",3,"street Porto",12,"1234-123","Porto");

        assertNotEquals(park, park1);
    }

    @Test
    @DisplayName("Same Object")
    void testEqualsSameObjectSame() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace park1 = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        assertEquals(park, park1);
    }

    @Test
    @DisplayName("Hash Code Object")
    void testHashCodeSameObject() {
        GreenSpace park = new LargePark("Jardim Porto",3,"street Porto",12,"1234-123","Porto");

        assertEquals(park.hashCode(), park.hashCode());
    }

}
