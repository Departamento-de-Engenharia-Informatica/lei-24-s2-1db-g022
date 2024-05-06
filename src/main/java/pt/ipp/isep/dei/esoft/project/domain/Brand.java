package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

/**
 * @author Group22
 */
public class Brand {
    private String name;
    private List<Model> modelList;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", modelList=" + modelList +
                '}';
    }
}
