package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MinSpanTreeController;
import pt.ipp.isep.dei.esoft.project.application.controller.TestAlgorithmController;
import pt.ipp.isep.dei.esoft.project.domain.model.Pipe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * User interface for testing algorithms.
 * This class provides functionality to run tests on minimum spanning tree and other algorithms.
 * It initializes controllers for minimum spanning tree and algorithm testing.
 *
 * @author Group22
 */
public class TestAlgorithmUI implements Runnable {
    private final MinSpanTreeController controller1;
    private final TestAlgorithmController controller2;
    private String filePath;

    /**
     * Constructs a new TestAlgorithmUI with default controllers.
     */
    public TestAlgorithmUI() {

        controller1 = new MinSpanTreeController();
        controller2 = new TestAlgorithmController();
    }

    /**
     * Gets the controller for minimum spanning tree.
     *
     * @return The controller for minimum spanning tree.
     */
    private MinSpanTreeController getController1() {

        return controller1;
    }

    /**
     * Gets the controller for algorithm testing.
     *
     * @return The controller for algorithm testing.
     */
    private TestAlgorithmController getController2() {

        return controller2;
    }

    /**
     * Executes an analysis on an algorithm by running it for a range of input files.
     * Measures the execution time and input size for each file, and saves the data
     * to a CSV file. Additionally, generates a graph based on this data.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Analysing Algorithm ------------------------");
        String outputDirectoryName = "MDISC\\US14\\Results";

        // Define the range for input file numbers
        int numFiles = 30;

        // Define variables for storing execution times
        long[] executionTimes = new long[numFiles];
        List<Integer> inputSizesList = new ArrayList<>();
        // Run the algorithm for each input file
        for (int i = 0; i < numFiles; i++) {

            String inputFile = "MDISC\\US14\\us14_" + (i + 1) + ".csv";
            long startTime = System.nanoTime();

            Pipe[] pipes = getController1().importRoutesFromCSV(inputFile);
            List<Pipe> routes = getController1().kruskalMinSpanningTree(pipes);
            inputSizesList.add(getController1().findNumVertices(pipes).size());

            long endTime = System.nanoTime();
            int duration = (int) (endTime - startTime) / 1000000; // Convert to milliseconds

            executionTimes[i] = duration;
        }

        // Write input size and execution time data to CSV file
        String csvFilePath = outputDirectoryName + File.separator + "execution_times.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {

            writer.println("Input Size,Execution Time (ms)");

            for (int i = 0; i < numFiles; i++) {

                writer.println(inputSizesList.get(i) + "," + executionTimes[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        getController2().generateGraph(csvFilePath, outputDirectoryName + File.separator + "execution_times.png");

        System.out.println("\n\n--- Tests Completed ------------------------");
    }
}
