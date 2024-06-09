package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a signal point.
 *
 * @author Group22
 */
public class SignalPoint {
    private String name;

    /**
     * Constructs a signal point with a specific name.
     *
     * @param name The name of the signal point.
     */
    public SignalPoint(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the signal point.
     *
     * @return The name of the signal point.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the signal point.
     *
     * @return A string representation of the signal point.
     */
    @Override
    public String toString() {
        return "SignalPoint{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignalPoint that = (SignalPoint) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Returns a hash code value for the signal point.
     *
     * @return A hash code value for the signal point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
