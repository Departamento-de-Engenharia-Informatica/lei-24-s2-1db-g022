package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * The Model class represents a model of a vehicle.
 * It contains a name which serves as the identity of the model.
 *
 * @author Group22
 */
public class Model {

    private String name;

    /**
     * Constructs a Model object with the specified name.
     *
     * @param name The name of the model. This is the identity of the model.
     */
    public Model(String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Vehicle model cannot be null or empty.");
        } else {

            this.name = name;
        }
    }

    /**
     * Gets the name of the model.
     *
     * @return The name of the model.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the model.
     *
     * @return A string representation of the model.
     */
    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Clones the model.
     *
     * @return A clone of the current model instance.
     */
    public Model clone() {
        return new Model(this.name);
    }

    /**
     * Indicates whether some other object is "equal to" this model.
     *
     * @param o The reference object with which to compare.
     * @return True if this model is the same as the object argument, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return Objects.equals(name, model.name);
    }

    /**
     * Returns a hash code value for the model.
     *
     * @return A hash code value for this model.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
