# US08 - As an FM, I want to list the vehicles needing the check-up.

## 4. Tests 

n/a

## 5. Construction (Implementation)

### Class ListVehiclesNeedingCheckUpController

```java
package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.model.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;

public class ListVehiclesNeedingCheckUpController {

    private VehicleRepository vehicleRepository;

    public ListVehiclesNeedingCheckUpController() {
        getVehicleRepository();
    }

    public List<Vehicle> getVehiclesNeedingCheckUp() {
        return vehicleRepository.getVehiclesNeedingCheckUp();
    }


    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }
}
```

### Class Vehicle

```java
package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.model.Brand;
import pt.ipp.isep.dei.esoft.project.domain.model.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.model.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

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
    private int checkUpFrequency;
    private String licensePlate;
    private Brand brand;
    private List<CheckUp> checkUpList;
    private Model model;

    /**
     * Constructs a Vehicle object with the specified attributes.
     *
     * @param type            The type of the vehicle.
     * @param tare            The tare weight of the vehicle.
     * @param grossWeight     The gross weight of the vehicle.
     * @param currentKm       The current kilometers driven by the vehicle.
     * @param registerDate    The registration date of the vehicle.
     * @param acquisitionDate The acquisition date of the vehicle.
     * @param checkUpFrequency The frequency of check-ups for the vehicle.
     * @param licensePlate    The license plate of the vehicle.
     * @param brand           The brand of the vehicle.
     * @param model           The model of the vehicle.
     * @throws IllegalArgumentException If any of the parameters are null or empty, or if the license plate format is incorrect.
     */
    public Vehicle(String type, float tare, float grossWeight, int currentKm, Date registerDate, Date acquisitionDate, int checkUpFrequency, String licensePlate, Brand brand, Model model) {
        this.checkUpList = new ArrayList<>();

        if (validateNullFloat(tare) && validateNullFloat(grossWeight)) {

            this.tare = tare;
            this.grossWeight = grossWeight;
        } else {

            throw new IllegalArgumentException("Vehicle tare and grossweight cannot be null or empty.");
        }

        if (validateNullString(type) && validateNullString(licensePlate)) {

            this.type = type;
        } else {

            throw new IllegalArgumentException("Vehicle type and licensePlate cannot be null or empty.");
        }

        if (validateNullInt(currentKm) && validateNullInt(checkUpFrequency)) {

            this.currentKm = currentKm;
            this.checkUpFrequency = checkUpFrequency;
        } else {

            throw new IllegalArgumentException("Vehicle currentKm and checkUpFrequency cannot be null or empty.");
        }

        if (validateNullDate(acquisitionDate) && validateNullDate(registerDate)) {

            this.acquisitionDate = acquisitionDate;
            this.registerDate = registerDate;
        } else {

            throw new IllegalArgumentException("Vehicle acquisitionDate and registerDate cannot be null or empty.");
        }

        if (validateVehicle(registerDate, licensePlate)) {

            this.licensePlate = licensePlate;
        } else {

            throw new IllegalArgumentException("Incorrect Vehicle licensePlate format.");
        }

        this.brand = brand;
        this.model = model;
        checkUpList = new ArrayList<>();
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
    public int getCheckUpFrequency() {
        return checkUpFrequency;
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
     * Validates if the license plate format matches the expected format based on the registration date of the vehicle.
     *
     * @param registerDate The registration date of the vehicle.
     * @param licensePlate The license plate of the vehicle to validate.
     * @return True if the license plate format matches the expected format, false otherwise.
     */
    private boolean validateVehicle(Date registerDate, String licensePlate) {

        int ano = registerDate.getYear();
        String regex1 = "\\d{2}-\\d{2}-[A-Z]{2}";
        String regex2 = "\\d{2}-[A-Z]{2}-\\d{2}";
        String regex3 = "[A-Z]{2}-\\d{2}-[A-Z]{2}";

        if (ano >= 92 && ano < 105) {

            Pattern pattern = Pattern.compile(regex1);

            return pattern.matcher(licensePlate).matches();
        } else if (ano >= 105 && ano < 120) {

            Pattern pattern = Pattern.compile(regex2);

            return pattern.matcher(licensePlate).matches();
        } else if (ano >= 120) {

            Pattern pattern = Pattern.compile(regex3);

            return pattern.matcher(licensePlate).matches();
        } else {

            return false;
        }
    }

    /**
     * Checks if a check-up is needed for the vehicle.
     *
     * This method determines whether a check-up is needed based on the vehicle's check-up history and frequency.
     * If no check-ups are recorded, or if the current date is after the calculated next check-up date,
     * a check-up is considered needed.
     *
     * @return True if a check-up is needed, false otherwise.
     */
    public boolean isCheckUpNeeded() {
        if (checkUpList.isEmpty()) {
            // If there are no check-ups recorded, assume maintenance is needed
            return true;
        } else {
            // Get the last check-up date
            Date lastCheckUpDate = checkUpList.get(checkUpList.size() - 1).getDate();
            // Calculate the next check-up date based on the check-up frequency
            Date nextCheckUpDate = calculateNextCheckUpDate(lastCheckUpDate, checkUpFrequency);
            // If the current date is after the next check-up date, maintenance is needed
            return new Date().after(nextCheckUpDate);
        }
    }

    /**
     * Retrieves the mileage of the last check-up or the current mileage if no check-ups are recorded.
     *
     * This method returns the mileage of the last recorded check-up if available. If no check-ups are recorded,
     * it returns the current mileage of the vehicle.
     *
     * @return The mileage of the last check-up or the current mileage if no check-ups are recorded.
     */
    public int getLastCheckupMileage() {
        if (!checkUpList.isEmpty()) {
            // If there are check-ups recorded, return the mileage of the last check-up
            return checkUpList.get(checkUpList.size() - 1).getKm();
        } else {
            // If there are no check-ups recorded, return the current mileage
            return currentKm;
        }
    }

    /**
     * Calculates the next check-up mileage based on the last check-up mileage and the check-up frequency.
     *
     * @return The next check-up mileage.
     */
    public int getNextCheckupMileage() {
        int lastCheckupKms = getLastCheckupMileage();
        int freq = getCheckUpFrequency();
        return lastCheckupKms + freq;
    }

    /**
     * Calculates the next check-up date based on the last check-up date and the check-up frequency.
     *
     * @param lastCheckUpDate  The date of the last check-up.
     * @param checkUpFrequency The frequency of check-ups.
     * @return The date of the next check-up.
     */
    private Date calculateNextCheckUpDate(Date lastCheckUpDate, int checkUpFrequency) {
        // Add the check-up frequency in milliseconds to the last check-up date
        // to get the next check-up date
        long nextCheckUpTime = lastCheckUpDate.getTime() + (checkUpFrequency * 24 * 60 * 60 * 1000L);
        return new Date(nextCheckUpTime);
    }


    /**
     * Validates if the license plate of this vehicle matches the license plate of another vehicle.
     *
     * @param vehicleObj The other vehicle object to compare the license plates with.
     * @return True if the license plates match, false otherwise.
     */
    public boolean validateVehicleLicense(Vehicle vehicleObj) {

        return this.getLicensePlate().equals(vehicleObj.getLicensePlate());
    }

    /**
     * Validates if a float value is not negative and not equal to zero.
     *
     * @param value The float value to validate.
     * @return True if the float value is not negative and not equal to zero, false otherwise.
     */
    private boolean validateNullFloat(float value) {
        return !(value < 0) && value != 0.0f;
    }

    /**
     * Validates if a string value is not null and not empty.
     *
     * @param value The string value to validate.
     * @return True if the string value is not null and not empty, false otherwise.
     */
    private boolean validateNullInt(int value) {
        return !(value <= 0);
    }

    private boolean validateNullString(String value) {
        return !(value == null) && !(value.isEmpty());
    }

    /**
     * Validates if a date value is not null.
     *
     * @param value The date value to validate.
     * @return True if the date value is not null, false otherwise.
     */
    private boolean validateNullDate(Date value) {
        return !(value == null);
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
                ", checkUpFrequency=" + checkUpFrequency +
                ", licensePlate='" + licensePlate + '\'' +
                ", brand=" + brand +
                ", checkUpList=" + checkUpList +
                ", model=" + model +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this vehicle.
     *
     * @param o The reference object with which to compare.
     * @return True if this vehicle is the same as the object argument, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Float.compare(tare, vehicle.tare) == 0 && Float.compare(grossWeight, vehicle.grossWeight) == 0 && currentKm == vehicle.currentKm && checkUpFrequency == vehicle.checkUpFrequency && Objects.equals(type, vehicle.type) && Objects.equals(registerDate, vehicle.registerDate) && Objects.equals(acquisitionDate, vehicle.acquisitionDate) && Objects.equals(licensePlate, vehicle.licensePlate) && Objects.equals(brand, vehicle.brand) && Objects.equals(checkUpList, vehicle.checkUpList) && Objects.equals(model, vehicle.model);
    }

    /**
     * Returns a string representation of the vehicle.
     *
     * @return A string representation of the vehicle.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, tare, grossWeight, currentKm, registerDate, acquisitionDate, checkUpFrequency, licensePlate, brand, checkUpList, model);
    }

    /**
     * Clones the vehicle.
     *
     * @return A clone of the current vehicle instance.
     */
    public Vehicle clone() {

        Vehicle clone = new Vehicle(this.type, this.tare, this.grossWeight, this.currentKm, this.registerDate, this.acquisitionDate, this.checkUpFrequency, this.licensePlate, this.brand, this.model);

        for (CheckUp in : this.checkUpList) {

            clone.checkUpList.add(in.clone());
        }

        return clone;
    }
}

```

### Class ListVehiclesNeedingCheckUpUI

```java

package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListVehiclesNeedingCheckUpController;
import pt.ipp.isep.dei.esoft.project.domain.model.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.List;

public class ListVehiclesNeedingCheckUpUI implements Runnable {

    private final ListVehiclesNeedingCheckUpController controller = new ListVehiclesNeedingCheckUpController();

    @Override
    public void run() {
        List<Vehicle> vehicles = controller.getVehiclesNeedingCheckUp();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles need a check-up at the moment.");
        } else {
            System.out.println("Vehicles Needing Check-Up:");
            System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n",
                    "Plate", "Brand", "Model", "Curr.Kms", "Last", "Next");

            for (Vehicle vehicle : vehicles) {
                String plate = vehicle.getLicensePlate();
                String brand = vehicle.getBrand().getName();
                String model = vehicle.getModel().getName();
                int currKms = vehicle.getCurrentKm();
                int lastCheckupKms = vehicle.getLastCheckupMileage();
                int freq = vehicle.getCheckUpFrequency();
                int nextCheckupKms = lastCheckupKms + freq;

                System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n",
                        plate, brand, model, currKms, lastCheckupKms, nextCheckupKms);
            }
        }
    }
}


```
## 6. Integration and Demo 

n/a


## 7. Observations

n/a