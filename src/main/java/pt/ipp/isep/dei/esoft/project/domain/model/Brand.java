package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * Constructs a Brand object with the specified name.
     *
     * @param name The name of the brand. This is the identity of the brand.
     */
    public Brand(String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Vehicle brand cannot be null or empty.");
        } else {

            this.name = name;
        }

        modelList = new ArrayList<>();
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
     * This method adds a model to the list of models.
     *
     * @param model The model to be added.
     */
    public void addModel(Model model) {

        modelList.add(model);
    }

    /**
     * Checks if a model with the given name exists in the model list.
     *
     * @param modelName The name of the model to check for existence.
     * @return True if a model with the given name exists, false otherwise.
     */
    public boolean hasModelByName(String modelName) {

        Model model = new Model (modelName);

        return modelList.contains(model);
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

    /**
     * Clones the brand.
     *
     * @return A clone of the current brand instance.
     */
    public Brand clone() {

        Brand clone = new Brand(this.name);

        for (Model in : this.modelList) {

            clone.modelList.add(in.clone());
        }

        return clone;
    }

    /**
     * Indicates whether some other object is "equal to" this brand.
     *
     * @param o The reference object with which to compare.
     * @return True if this brand is the same as the object argument, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand)) return false;
        Brand brand = (Brand) o;
        return Objects.equals(name, brand.name);
    }

    /**
     * Returns a hash code value for the brand.
     *
     * @return A hash code value for this brand.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
