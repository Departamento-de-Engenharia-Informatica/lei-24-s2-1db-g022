package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MinRouteToApController;
import pt.ipp.isep.dei.esoft.project.domain.model.Route;
import pt.ipp.isep.dei.esoft.project.domain.model.SignalPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User interface for finding the minimum route to access points.
 *
 * @author Group22
 */
public class MinRouteToApUI implements Runnable {

    private final String FILE_PATH = "MDISC\\US17\\Results\\single_output";
    private final String FILE_PATH_OUTPUT = "MDISC\\US17\\Results";

    private final String FILE_PATH_OUTPUT_IMG = "MDISC\\US17\\Results\\\\all_output";
    private final String FILE_PATH_NAMES = "MDISC\\US17\\us17_points_names.csv";
    private final String FILE_PATH_MATRIX = "MDISC\\US17\\us17_matrix.csv";

    private final MinRouteToApController controller;
    private String matrixFilePath;
    private String pointsFilePath;
    private SignalPoint signalPoint;

    /**
     * Initializes the UI with its corresponding controller.
     */
    public MinRouteToApUI() {
        controller = new MinRouteToApController();
    }

    /**
     * Retrieves the controller associated with this UI.
     *
     * @return The MinRouteToApController instance.
     */
    private MinRouteToApController getController() {
        return controller;
    }

    /**
     * Runs the UI to display the shortest path to the assembly point.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Logger.getLogger("org.graphstream").setLevel(Level.WARNING);
        System.out.println("\n\n--- Shortest Path to Assembly Point ------------------------");
        System.out.println("1) display One Path");
        System.out.println("2) display All Path");

        int i = scanner.nextInt();
        if (i == 1) {
            displayOnePath();
        } else {
            displayAllPath();

        }
    }

    /**
     * Displays all possible paths to the assembly point.
     */
    private void displayAllPath() {
        List<SignalPoint> listPointNames = getController().importNamesFromCSV(FILE_PATH_NAMES);
        List<Route> listRoutes = getController().importRouteFromCSV(listPointNames, FILE_PATH_MATRIX);

        String content = "";
        for (SignalPoint ls : listPointNames) {
            if (!ls.equals(new SignalPoint("AP"))) {
                List<Route> paths = getController().findShortestPath(ls, listPointNames.get(listPointNames.indexOf(new SignalPoint("AP"))), listPointNames, listRoutes);
                content = getController().generateAllSubgraphCSV(content, paths);
                try {
                    getController().visualizeGraph(paths, "Output_Graph_" + ls.getName(), FILE_PATH_OUTPUT_IMG);
                } catch (IOException ignored) {

                }
            }
        }


        getController().writeCSVToFile(content, FILE_PATH_OUTPUT);
    }

    /**
     * Displays one shortest path to the assembly point based on user input.
     */
    private void displayOnePath() {
        try {
            //requestData();
            requestDataInput();
            List<SignalPoint> listPointNames = getController().importNamesFromCSV(FILE_PATH_NAMES);
            List<Route> listRoutes = getController().importRouteFromCSV(listPointNames, FILE_PATH_MATRIX);
            List<Route> paths = getController().findShortestPath(listPointNames.get(listPointNames.indexOf(signalPoint)), listPointNames.get(listPointNames.indexOf(new SignalPoint("AP"))), listPointNames, listRoutes);

            System.out.println("\n\n--- Path -------------");
            for (Route routes : paths) {
                System.out.println(routes.toString());
            }
            System.out.printf("\nTotal Distance: %d%n", getController().totalDistance(paths));


            getController().writeCSVToFile(getController().generateSubgraphCSV(paths), FILE_PATH_OUTPUT);


            getController().visualizeGraph(listRoutes, "Input_Graph", FILE_PATH);

            // Visualize the output subgraph
            getController().visualizeGraph(paths, "Output_Subgraph", FILE_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Requests input for the signal point.
     */
    private void requestDataInput() {
        signalPoint = requestSignalPoint();
    }

    /**
     * Requests input for the signal point.
     *
     * @return The signal point obtained from user input.
     */
    private SignalPoint requestSignalPoint() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start SignalPoint: ");
        String startNome = scanner.nextLine();
        return new SignalPoint(startNome);
    }

    /**
     * Requests input for the file paths.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void requestData() throws IOException {
        matrixFilePath = requestMatrixFilePath();
        pointsFilePath = pointsFilePath();

    }

    /**
     * Requests input for the file path of the matrix CSV file.
     *
     * @return The file path entered by the user.
     * @throws IOException If an I/O error occurs.
     */
    private String requestMatrixFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path to the CSV file with matrix: ");
        return reader.readLine();
    }

    /**
     * Requests input for the file path of the name points CSV file.
     *
     * @return The file path entered by the user.
     * @throws IOException If an I/O error occurs.
     */
    private String pointsFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path to the CSV file with name points: ");
        return reader.readLine();
    }
}
