package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

/**
 * @author Group22
 */
public class CheckUp {
    private Date date;
    private int km;

    public CheckUp(Date date, int km) {
        this.date = date;
        this.km = km;
    }

    public Date getDate() {
        return date;
    }

    public int getKm() {
        return km;
    }

    @Override
    public String toString() {
        return "CheckUp{" +
                "date=" + date +
                ", km=" + km +
                '}';
    }
}
