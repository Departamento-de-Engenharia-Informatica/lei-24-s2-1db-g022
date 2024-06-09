package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.Garden;
import pt.ipp.isep.dei.esoft.project.domain.model.GreenSpace;

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
}
