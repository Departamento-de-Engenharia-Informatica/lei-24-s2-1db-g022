package pt.ipp.isep.dei.esoft.project.domain;

/**
 * @author Group22
 */
public class Job {
    private String name;

    public Job(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                '}';
    }
}
