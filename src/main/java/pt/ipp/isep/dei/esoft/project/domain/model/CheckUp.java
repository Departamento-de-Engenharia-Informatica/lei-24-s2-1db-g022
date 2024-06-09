package pt.ipp.isep.dei.esoft.project.domain.model;

import java.util.Date;

/**
 * The CheckUp class represents a vehicle check-up.
 * It contains a date and the mileage of the vehicle at the time of the check-up.
 *
 * @author Group22
 */
public class CheckUp {

    private Date date;
    private int km;

    /**
     * Constructs a CheckUp object with the specified date and mileage.
     *
     * @param date The date of the check-up.
     * @param km   The mileage of the vehicle at the time of the check-up.
     */
    public CheckUp(Date date, int km) {
        this.date = date;
        this.km = km;
    }

    /**
     * Gets the date of the check-up.
     *
     * @return The date of the check-up.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the mileage of the vehicle at the time of the check-up.
     *
     * @return The mileage of the vehicle at the time of the check-up.
     */
    public int getKm() {
        return km;
    }

    /**
     * Returns a string representation of the check-up.
     *
     * @return A string representation of the check-up.
     */
    @Override
    public String toString() {
        return "CheckUp{" +
                "date=" + date +
                ", km=" + km +
                '}';
    }

    /**
     * Clones the checkup.
     *
     * @return A clone of the current checkup instance.
     */
    public CheckUp clone() {
        return new CheckUp(this.date, this.km);
    }
}
