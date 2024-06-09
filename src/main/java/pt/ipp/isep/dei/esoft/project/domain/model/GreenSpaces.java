package pt.ipp.isep.dei.esoft.project.domain.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Represents a collection of green spaces.
 *
 * @author Group22
 */
public class GreenSpaces {
    List<GreenSpace> greenSpacesList;

    public GreenSpaces() {
        greenSpacesList = new ArrayList<>();
    }

    /**
     * Adds a GreenSpace to the list of GreenSpaces.
     *
     * @param greenSpace the GreenSpace to be added.
     * @return true if the GreenSpace was successfully added to the list, false otherwise.
     */
    public boolean addListGreenSpaces(GreenSpace greenSpace) {
        return this.greenSpacesList.add(greenSpace);
    }

    /**
     * Retrieves the list of GreenSpace objects managed by this GreenSpaceManager.
     *
     * @return The list of GreenSpace objects managed by this GreenSpaceManager.
     */
    public List<GreenSpace> getGreenSpaceList() {

        return greenSpacesList;
    }

    /**
     * Retrieves a sorted list of green spaces based on a specified sorting algorithm.
     *
     * @return A sorted list of green spaces.
     * @throws RuntimeException if an error occurs while loading configuration or sorting.
     */
    public List<GreenSpace> getSortedGreenspaces() {

        Properties properties = new Properties();

        try {

            InputStream in = new FileInputStream("src\\main\\resources\\config.properties");

            properties.load(in);
            in.close();

            String classPath = properties.getProperty("class.path");
            Class<?> clazz = Class.forName(classPath);
            Sortable st = (Sortable) clazz.newInstance();

            return st.getSortedGreenSpaces(this.greenSpacesList);


        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    /**
     * Adds a GreenSpace to the list of bootstrap GreenSpaces.
     *
     * @param greenSpace the GreenSpace to be added.
     */
    public void addListBootstrapGreenSpaces(GreenSpace greenSpace) {
        this.greenSpacesList.add(greenSpace);
    }

}
