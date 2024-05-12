package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListVehiclesNeedingCheckUpController;
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
