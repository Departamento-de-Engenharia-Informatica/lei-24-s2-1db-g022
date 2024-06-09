package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The SelectionSortTest class contains unit tests for the SelectionSort class.
 * It validates the behavior of the SelectionSort class methods.
 * Each test method focuses on a specific aspect or scenario related to the SelectionSort class functionality.
 *
 * @author Group22
 */
class SelectionSortTest {

    /**
     * Test case to verify the functionality of sorting green spaces using the SelectionSort algorithm.
     * The test sorts a list of green spaces by their area in ascending order and compares the result with the expected sorted list.
     */
    @Test
    void TestGetSortedGreenSpaces(){

        GreenSpace garden = new Garden("Jardim Porto",3,"street Porto",12,"1234-123","Porto");
        GreenSpace garden2 = new Garden("Jardim Lisboa",5,"street Lisboa",11,"1234-143","Lisboa");
        GreenSpace mediumPark = new MediumPark("Jardim Braga",10,"street Braga",12,"1235-135","Braga");
        GreenSpace largePark = new LargePark("Jardim Viseu",30,"street Viseu",12,"1237-145","Viseu");

        List<GreenSpace> expected = new ArrayList<>();

        expected.add(largePark);
        expected.add(mediumPark);
        expected.add(garden2);
        expected.add(garden);

        SelectionSort selectionSort = new SelectionSort();

        List<GreenSpace> greenSpaceList = new ArrayList<>();

        greenSpaceList.add(garden);
        greenSpaceList.add(garden2);
        greenSpaceList.add(mediumPark);
        greenSpaceList.add(largePark);

        List<GreenSpace> result = selectionSort.getSortedGreenSpaces(greenSpaceList);

        assertEquals(expected, result);
    }

}