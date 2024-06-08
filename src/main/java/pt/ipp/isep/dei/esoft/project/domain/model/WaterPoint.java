package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * @author Group22
 */
public class WaterPoint {
    private String designation;

    public WaterPoint(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaterPoint)) return false;
        WaterPoint that = (WaterPoint) o;
        return Objects.equals(designation, that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    @Override
    public String toString() {
        return "WaterPoint{" +
                "designation='" + designation + '\'' +
                '}';
    }
}
