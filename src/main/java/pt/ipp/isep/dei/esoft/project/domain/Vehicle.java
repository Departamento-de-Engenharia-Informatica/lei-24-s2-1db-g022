package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;

/**
 * @author Group22
 */
public class Vehicle {
    private String type;
    private float tare;
    private float grossWeight;
    private int currentKm;
    private Date registerDate;
    private Date acquisitionDate;
    private int checkupFrequency;
    private String licensePlate;
    private Brand brand;
    private List<CheckUp> checkUpList;
    private Model model;

    public Vehicle(String type, float tare, float grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkupFrequency, String licensePlate, Brand brand, Model model) {
        this.type = type;
        this.tare = tare;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.checkupFrequency = checkupFrequency;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
    }

    public Vehicle(String type, float tare, float grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkupFrequency, String licensePlate, Brand brand, List<CheckUp> checkUpList, Model model) {
        this.type = type;
        this.tare = tare;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.checkupFrequency = checkupFrequency;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.checkUpList = checkUpList;
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public float getTare() {
        return tare;
    }

    public float getGrossWeight() {
        return grossWeight;
    }

    public int getCurrentKm() {
        return currentKm;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public int getCheckupFrequency() {
        return checkupFrequency;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model getModel() {
        return model;
    }

    public List<CheckUp> getCheckUpList() {
        return checkUpList;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", tare=" + tare +
                ", grossWeight=" + grossWeight +
                ", currentKm=" + currentKm +
                ", registerDate=" + registerDate +
                ", acquisitionDate=" + acquisitionDate +
                ", checkupFrequency=" + checkupFrequency +
                ", licensePlate='" + licensePlate + '\'' +
                ", brand=" + brand +
                ", checkUpList=" + checkUpList +
                ", model=" + model +
                '}';
    }
}
