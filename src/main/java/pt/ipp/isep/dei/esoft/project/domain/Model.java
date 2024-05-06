package pt.ipp.isep.dei.esoft.project.domain;

/**
 * @author Group22
 */
public class Model {
    private String name;

    public Model(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                '}';
    }
}
