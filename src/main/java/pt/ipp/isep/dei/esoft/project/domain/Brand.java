package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

/**
 * The Brand class represents a brand of vehicles.
 * It contains a name and a list of models associated with the brand.
 *
 * @author Group22
 */
public class Brand {

    private String name;
    private List<Model> modelList;

    /**
     * Constructs a Brand object with an empty list of models.
     */
    public Brand() {
    }

    /**
     * Gets the name of the brand.
     *
     * @return The name of the brand.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of models associated with the brand.
     *
     * @return The list of models associated with the brand.
     */
    public List<Model> getModelList() {
        return modelList;
    }

    /**
     * Returns a string representation of the brand.
     *
     * @return A string representation of the brand.
     */
    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", modelList=" + modelList +
                '}';
    }
}
