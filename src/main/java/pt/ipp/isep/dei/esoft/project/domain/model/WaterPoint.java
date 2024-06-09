package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a water point.
 *
 * @author Group22
 */
public class WaterPoint {
    private String designation;

    /**
     * Constructs a new water point with the given designation.
     *
     * @param designation the designation of the water point
     */
    public WaterPoint(String designation) {
        this.designation = designation;
    }

    /**
     * Gets the designation of the water point.
     *
     * @return the designation of the water point
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this water point is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaterPoint)) return false;
        WaterPoint that = (WaterPoint) o;
        return Objects.equals(designation, that.designation);
    }

    /**
     * Returns a hash code value for the water point.
     *
     * @return a hash code value for this water point
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    /**
     * Returns a string representation of the water point.
     *
     * @return a string representation of the water point
     */
    @Override
    public String toString() {
        return "WaterPoint{" +
                "designation='" + designation + '\'' +
                '}';
    }
}
