package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Pipe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestAlgorithmUI implements Runnable {
    private final MinSpanTreeController controller1;
    private final TestAlgorithmController controller2;

    private String filePath;

    public TestAlgorithmUI() {
        controller1 = new MinSpanTreeController();
        controller2 = new TestAlgorithmController();
    }

    private MinSpanTreeController getController1() {
        return controller1;
    }

    private TestAlgorithmController getController2() {
        return controller2;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Analysing Algorithm ------------------------");
        String outputDirectoryName = "US14\\Results";

        // Define the range for input file numbers
        int numFiles = 30;

        // Define variables for storing execution times
        long[] executionTimes = new long[numFiles];
        List<Integer> inputSizesList = new ArrayList<>();
        // Run the algorithm for each input file
        for (int i = 0; i < numFiles; i++) {

            String inputFile = "US14\\us14_" + (i + 1) + ".csv";
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
