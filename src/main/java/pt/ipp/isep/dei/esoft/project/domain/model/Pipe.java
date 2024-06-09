package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

/**
 * Represents a pipe connecting two water points with a certain distance.
 *
 * @author Group22
 */
public class Pipe {
    private WaterPoint waterPoint_X;
    private WaterPoint waterPoint_Y;
    private int distance;

    /**
     * Initializes a new Pipe instance.
     *
     * @param waterPoint_X The starting water point of the pipe.
     * @param waterPoint_Y The ending water point of the pipe.
     * @param distance     The distance between the water points.
     */
    public Pipe(WaterPoint waterPoint_X, WaterPoint waterPoint_Y, int distance) {
        this.waterPoint_X = waterPoint_X;
        this.waterPoint_Y = waterPoint_Y;
        this.distance = distance;
    }

    /**
     * Gets the starting water point of the pipe.
     *
     * @return The starting water point.
     */
    public WaterPoint getWaterPoint_X() {
        return waterPoint_X;
    }

    /**
     * Gets the ending water point of the pipe.
     *
     * @return The ending water point.
     */
    public WaterPoint getWaterPoint_Y() {
        return waterPoint_Y;
    }

    /**
     * Gets the distance between the water points.
     *
     * @return The distance between the water points.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the o argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pipe)) return false;
        Pipe pipe = (Pipe) o;
        return distance == pipe.distance && Objects.equals(waterPoint_X, pipe.waterPoint_X) && Objects.equals(waterPoint_Y, pipe.waterPoint_Y);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(waterPoint_X, waterPoint_Y, distance);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Pipe{" +
                "waterPoint_X='" + waterPoint_X.getDesignation() + '\'' +
                ", waterPoint_Y='" + waterPoint_Y.getDesignation() + '\'' +
                ", distance=" + distance +
                '}';
    }
}
