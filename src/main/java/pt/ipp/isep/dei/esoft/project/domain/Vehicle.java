package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;

/**
 * The Vehicle class represents a vehicle in the organization.
 * It contains information such as type, tare weight, gross weight, current mileage, registration date, acquisition date,
 * check-up frequency, license plate, brand, model, and a list of check-ups.
 *
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

    /**
     * Constructs a Vehicle object with the specified parameters.
     *
     * @param type The type of the vehicle.
     * @param tare The tare weight of the vehicle.
     * @param grossWeight The gross weight of the vehicle.
     * @param currentKm The current mileage of the vehicle.
     * @param registerDate The registration date of the vehicle.
     * @param acquisitionDate The acquisition date of the vehicle.
     * @param checkupFrequency The frequency of check-ups for the vehicle.
     * @param licensePlate The license plate number of the vehicle.
     * @param brand The brand of the vehicle.
     * @param model The model of the vehicle.
     */
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

    /**
     * Constructs a Vehicle object with the specified parameters and a list of check-ups.
     *
     * @param type The type of the vehicle.
     * @param tare The tare weight of the vehicle.
     * @param grossWeight The gross weight of the vehicle.
     * @param currentKm The current mileage of the vehicle.
     * @param registerDate The registration date of the vehicle.
     * @param acquisitionDate The acquisition date of the vehicle.
     * @param checkupFrequency The frequency of check-ups for the vehicle.
     * @param licensePlate The license plate number of the vehicle.
     * @param brand The brand of the vehicle.
     * @param checkUpList The list of check-ups for the vehicle.
     * @param model The model of the vehicle.
     */
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

    /**
     * Gets the type of the vehicle.
     *
     * @return The type of the vehicle.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the tare weight of the vehicle.
     *
     * @return The tare weight of the vehicle.
     */
    public float getTare() {
        return tare;
    }

    /**
     * Gets the gross weight of the vehicle.
     *
     * @return The gross weight of the vehicle.
     */
    public float getGrossWeight() {
        return grossWeight;
    }

    /**
     * Gets the current mileage of the vehicle.
     *
     * @return The current mileage of the vehicle.
     */
    public int getCurrentKm() {
        return currentKm;
    }

    /**
     * Gets the registration date of the vehicle.
     *
     * @return The registration date of the vehicle.
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * Gets the acquisition date of the vehicle.
     *
     * @return The acquisition date of the vehicle.
     */
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Gets the frequency of check-ups for the vehicle.
     *
     * @return The frequency of check-ups for the vehicle.
     */
    public int getCheckupFrequency() {
        return checkupFrequency;
    }

    /**
     * Gets the license plate number of the vehicle.
     *
     * @return The license plate number of the vehicle.
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Gets the brand of the vehicle.
     *
     * @return The brand of the vehicle.
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Gets the model of the vehicle.
     *
     * @return The model of the vehicle.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Gets the list of check-ups for the vehicle.
     *
     * @return The list of check-ups for the vehicle.
     */
    public List<CheckUp> getCheckUpList() {
        return checkUpList;
    }

    /**
     * Returns a string representation of the vehicle.
     *
     * @return A string representation of the vehicle.
     */
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
