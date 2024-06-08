package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Objects;

public class Route {
    private int distance;
    private SignalPoint s1;
    private SignalPoint s2;

    public Route(int distance, SignalPoint s1, SignalPoint s2) {
        this.distance = distance;
        this.s1 = s1;
        this.s2 = s2;
    }

    public Route(SignalPoint s1, SignalPoint s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public int getDistance() {
        return distance;
    }

    public SignalPoint getS1() {
        return s1;
    }

    public SignalPoint getS2() {
        return s2;
    }

    @Override
    public String toString() {
        return "Route{" +
                "distance=" + distance +
                ", s1=" + s1 +
                ", s2=" + s2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(s1, route.s1) && Objects.equals(s2, route.s2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s1, s2);
    }


}
