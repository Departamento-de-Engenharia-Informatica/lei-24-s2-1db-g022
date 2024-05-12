package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MinSpanTreeController;
import pt.ipp.isep.dei.esoft.project.domain.Pipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The MinSpanTreeUI class provides a console-based user interface for visualizing
 * and analyzing minimum spanning trees.
 */
public class MinSpanTreeUI implements Runnable {
    private final String FILE_PATH = "MDISC\\US13\\Results";

    /**
     * The controller responsible for handling minimum spanning tree operations.
     */
    private final MinSpanTreeController controller;

    /**
     * The file path to the CSV file containing route data.
     */
    private String filePath;

    /**
     * Constructs a MinSpanTreeUI object, initializing the controller.
     */
    public MinSpanTreeUI() {
        controller = new MinSpanTreeController();
    }

    /**
     * Retrieves the controller associated with this UI.
     *
     * @return The MinSpanTreeController instance.
     */
    private MinSpanTreeController getController() {
        return controller;
    }

    /**
     * Runs the console-based interface for minimum spanning tree visualization and analysis.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Minimum Spanning Tree Visualization ------------------------");
        try {
            requestData();

            // Import routes from the CSV file
            Pipe[] pipes = getController().importRoutesFromCSV(filePath);

            // Calculate the minimum spanning tree
            List<Pipe> routes = getController().kruskalMinSpanningTree(pipes);

            // Write the resulting subgraph to a CSV file
            getController().writeCSVToFile(getController().generateSubgraphCSV(routes), FILE_PATH);

            // Visualize the input graph
            getController().visualizeGraph(pipes, "Input_Graph", FILE_PATH);

            // Visualize the output subgraph
            getController().visualizeGraph(routes.toArray(new Pipe[0]), "Output_Subgraph", FILE_PATH);

            // Calculate total cost and count edges
            int minCost = 0;
            int countEdges = 1;
            for (Pipe pipe : routes) {
                minCost += pipe.getDistance();
            }
            for (Pipe p : pipes) {
                countEdges++;
            }

            // Print out information
            System.out.println("Cost of a Minimum spanning tree: " + minCost);
            System.out.println("Graph Order: " + getController().findNumVertices(pipes).size());
            System.out.println("Graph Dimension: " + countEdges);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Requests user input for the file path to the CSV file containing route data.
     *
     * @throws IOException If an I/O error occurs while reading user input.
     */
    private void requestData() throws IOException {
        filePath = requestFilePath();
    }

    /**
     * Requests the user to enter the path to the CSV file containing route data.
     *
     * @return The file path entered by the user.
     * @throws IOException If an I/O error occurs while reading user input.
     */
    private String requestFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path to the CSV file: ");
        return reader.readLine();
    }
}
