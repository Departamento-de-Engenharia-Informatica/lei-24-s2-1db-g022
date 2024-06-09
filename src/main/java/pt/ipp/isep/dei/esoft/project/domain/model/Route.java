package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a route between two signal points with a certain distance.
 *
 * @author Group22
 */
public class Route {
    private int distance;
    private SignalPoint s1;
    private SignalPoint s2;

    /**
     * Constructs a route with a specific distance between two signal points.
     *
     * @param distance The distance of the route.
     * @param s1       The starting signal point.
     * @param s2       The ending signal point.
     */
    public Route(int distance, SignalPoint s1, SignalPoint s2) {
        this.distance = distance;
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * Constructs a route without specifying the distance between two signal points.
     *
     * @param s1 The starting signal point.
     * @param s2 The ending signal point.
     */
    public Route(SignalPoint s1, SignalPoint s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * Retrieves the distance of the route.
     *
     * @return The distance of the route.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Retrieves the starting signal point of the route.
     *
     * @return The starting signal point of the route.
     */
    public SignalPoint getS1() {
        return s1;
    }

    /**
     * Retrieves the ending signal point of the route.
     *
     * @return The ending signal point of the route.
     */
    public SignalPoint getS2() {
        return s2;
    }

    /**
     * Returns a string representation of the route.
     *
     * @return A string representation of the route.
     */
    @Override
    public String toString() {
        return "Route{" +
                "distance=" + distance +
                ", s1=" + s1 +
                ", s2=" + s2 +
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
        Route route = (Route) o;
        return Objects.equals(s1, route.s1) && Objects.equals(s2, route.s2);
    }

    /**
     * Returns a hash code value for the route.
     *
     * @return A hash code value for the route.
     */
    @Override
    public int hashCode() {
        return Objects.hash(s1, s2);
    }
}
