package pt.ipp.isep.dei.esoft.project.domain;

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
        this.name = name;
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
}
