package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * @author Group22
 */
public class Pipe {
    private WaterPoint waterPoint_X;
    private WaterPoint waterPoint_Y;
    private int distance;

    public Pipe(WaterPoint waterPoint_X, WaterPoint waterPoint_Y, int distance) {
        this.waterPoint_X = waterPoint_X;
        this.waterPoint_Y = waterPoint_Y;
        this.distance = distance;
    }

    public WaterPoint getWaterPoint_X() {
        return waterPoint_X;
    }

    public WaterPoint getWaterPoint_Y() {
        return waterPoint_Y;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pipe)) return false;
        Pipe pipe = (Pipe) o;
        return distance == pipe.distance && Objects.equals(waterPoint_X, pipe.waterPoint_X) && Objects.equals(waterPoint_Y, pipe.waterPoint_Y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waterPoint_X, waterPoint_Y, distance);
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "waterPoint_X='" + waterPoint_X.getDesignation() + '\'' +
                ", waterPoint_Y='" + waterPoint_Y.getDesignation() + '\'' +
                ", distance=" + distance +
                '}';
    }
}
