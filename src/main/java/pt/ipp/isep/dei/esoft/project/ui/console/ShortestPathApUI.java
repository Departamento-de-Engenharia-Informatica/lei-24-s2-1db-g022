package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ShortestPathApController;
import pt.ipp.isep.dei.esoft.project.domain.Route;
import pt.ipp.isep.dei.esoft.project.domain.SignalPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ShortestPathApUI implements Runnable {

    private final String FILE_PATH = "MDISC\\US18\\Results";
    private final String FILE_PATH_NAMES = "MDISC\\US18\\us18_points_names.csv";
    private final String FILE_PATH_MATRIX = "MDISC\\US18\\us18_matrix.csv";
    private final ShortestPathApController controller;
    private String matrixFilePath;
    private String pointsFilePath;

    public ShortestPathApUI() {
        controller = new ShortestPathApController();
    }

    private ShortestPathApController getController() {
        return controller;
    }

    /**
     * Executes the task of finding the shortest path to the nearest assembly point for each non-assembly point,
     * generates CSV content based on the results, and writes the content to a CSV file.
     *
     * <p>This method retrieves signal points and routes data from CSV files, identifies assembly points,
     * finds the shortest path to the nearest assembly point for each non-assembly point, calculates total distances,
     * generates CSV content based on the shortest paths, and writes the content to a CSV file.</p>
     *
     * @throws RuntimeException If an error occurs during the execution of the task.
     */
    @Override
    public void run() {
        System.out.println("\n\n--- Shortest Path to Assembly Point ------------------------");
        try {
            //requestData();
            List<SignalPoint> listPointNames = getController().importNamesFromCSV(FILE_PATH_NAMES);
            List<Route> listRoutes = getController().importRouteFromCSV(listPointNames, FILE_PATH_MATRIX);
            boolean[] isAssemblyPoint = new boolean[listPointNames.size()];
            String content = "";

            for (int i = 0; i < listPointNames.size(); i++) {
                if (listPointNames.get(i).getName().startsWith("AP")) {
                    isAssemblyPoint[i] = true;
                }
            }

            for (SignalPoint sp : listPointNames) {

                if (sp.getName().startsWith("AP")) {
                    continue;
                }

                List<Route> paths = getController().findShortestPathToNearestAP(sp, listPointNames, listRoutes, isAssemblyPoint);

                for (Route r : paths) {
                    System.out.println(r.toString());
                }

                System.out.printf("\nTotal Distance: %d%n", getController().totalDistance(paths));

                content = getController().generateSubgraphCSV(content, paths);
            }

            getController().writeCSVToFile(content, FILE_PATH);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Requests the user to enter the paths to the CSV files containing the matrix and points data.
     *
     * @throws IOException If an I/O error occurs while reading user input.
     */
    private void requestData() throws IOException {
        matrixFilePath = requestMatrixFilePath();
        pointsFilePath = requestPointsFilePath();

    }

    /**
     * Requests the user to enter the path to the CSV file containing the matrix.
     *
     * @return A String representing the path to the CSV file.
     * @throws IOException If an I/O error occurs while reading user input.
     */
    private String requestMatrixFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path to the CSV file with matrix: ");
        return reader.readLine();
    }

    /**
     * Requests the user to enter the path to the CSV file containing the points.
     *
     * @return A String representing the path to the CSV file.
     * @throws IOException If an I/O error occurs while reading user input.
     */
    private String requestPointsFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path to the CSV file with name points: ");
        return reader.readLine();
    }
}
