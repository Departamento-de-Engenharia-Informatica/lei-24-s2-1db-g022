package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GreenSpaceRepositoryTest {

    @Test
    @DisplayName("Register Successfull GreenSpace")
    void TestRegisterGreenSpace() {
        GreenSpaceRepository grp = new GreenSpaceRepository();


        Optional<GreenSpace> resultGarden = grp.registerGreenSpace("Garden", "Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        assertTrue(resultGarden.isPresent());

        Optional<GreenSpace> resultMediumPark = grp.registerGreenSpace("Medium Park", "Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        assertTrue(resultMediumPark.isPresent());

        Optional<GreenSpace> resultLargePark = grp.registerGreenSpace("Large Park", "Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        assertTrue(resultLargePark.isPresent());
    }

    @Test
    @DisplayName("Register Duplicate GreenSpace")
    void duplicateGreenSpace() {
        GreenSpaceRepository grp = new GreenSpaceRepository();

        grp.registerGreenSpace("Garden", "Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");
        Optional<GreenSpace> result2 = grp.registerGreenSpace("Garden", "Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");
        assertFalse(result2.isPresent());
    }

    @Test
    @DisplayName("Get GreenSpace By Name")
    void getGreenSpaceByName() {

        Optional<GreenSpace> expect = Optional.of(new Garden("Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto"));

        GreenSpaceRepository grp = new GreenSpaceRepository();

         grp.registerGreenSpace("Garden", "Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        Optional<GreenSpace> result = grp.getGreenSpaceByName("Jardim Porto");

        assertEquals(expect,result);
    }

    @Test
    @DisplayName("Not Found GreenSpace by Name")
    void notFoundGreenSpaceByName() {

        GreenSpaceRepository grp = new GreenSpaceRepository();

        grp.registerGreenSpace("Garden", "Jardim Porto", 3, "street Porto", 12, "1234-123", "Porto");

        Optional<GreenSpace> result = grp.getGreenSpaceByName("Jardim Lisboa");

        assertTrue(result.isEmpty());
    }

    /**
     * Tests the sorting functionality of the list of green spaces belonging to a green space manager.
     * It verifies that the green spaces are sorted correctly based on their names in ascending order.
     */
    @Test
    void TestGreenSpaceSorted(){

        GreenSpaceManager gsm = new GreenSpaceManager("gsname", Date.valueOf("1999-01-10"), Date.valueOf("2024-01-10"), "+351 914981073", "gsm@gsm.pt", new Address("streeName", "1234-123", 1), 675432501, "Passport", 123021022, new Job("Gestor"));

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden1 = new Garden("Jardim Lisboa",5,"street Lisboa",11,"1234-143","Lisboa");
        gsm.getListGreenSpaces().addListBootstrapGreenSpaces(garden);
        gsm.getListGreenSpaces().addListBootstrapGreenSpaces(garden1);

        List<GreenSpace> result = new ArrayList<>();
        List<GreenSpace> expected = new ArrayList<>();

        expected.add(garden1);
        expected.add(garden);

        result = gsm.getListGreenSpaces().getSortedGreenspaces();

        assertEquals(expected,result);
    }

}
