package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Document;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentTest {
    @Test
    void ensureAdddressIsCreatedSuccessfully() {
        Document document = new Document("Passport",123321123);
    }

    @Test
    void ensureInvalidDocumentNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> new Document("Passport",123321));

    }

    @Test
    void testEqualsSameObject() {

        Document document = new Document("Passport",123321123);

        assertEquals(document, document);
    }

    @Test
    void testEqualsDifferentClass() {

        Document document = new Document("Passport",123321123);

        assertNotEquals(document, new Object());
    }

    @Test
    void testEqualsNull() {
        Document document = new Document("Passport",123321123);

        assertNotEquals(document, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Document document = new Document("Passport",123321123);
        Document document1 = new Document("Passport",923321113);

        assertNotEquals(document, document1);
    }

    @Test
    void testEqualsSameObjectSame() {
        Document document = new Document("Passport",123321123);
        Document document1 = new Document("Passport",123321123);

        assertEquals(document, document1);
    }

    @Test
    void testHashCodeSameObject() {
        Document document = new Document("Passport",123321123);

        assertEquals(document.hashCode(), document.hashCode());
    }

    /**
     * Tests if the clone method creates a new Brand object that is equal to the original one.
     */
    @Test
    void ensureCloneWorks() {
        Document document = new Document("Passport",123321123);
        Document clone = document.clone();
        assertEquals(document, clone);
    }

}
