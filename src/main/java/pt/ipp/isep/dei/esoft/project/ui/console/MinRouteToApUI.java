package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MinRouteToApController;
import pt.ipp.isep.dei.esoft.project.domain.Route;
import pt.ipp.isep.dei.esoft.project.domain.SignalPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class MinRouteToApUI implements Runnable {

    private final String FILE_PATH = "MDISC\\US17\\Results";
    private final String FILE_PATH_NAMES = "MDISC\\US17\\us17_points_names.csv";
    private final String FILE_PATH_MATRIX = "MDISC\\US17\\us17_matrix.csv";

    private final MinRouteToApController controller;
    private String matrixFilePath;
    private String pointsFilePath;
    private SignalPoint signalPoint;

    public MinRouteToApUI() {
        controller = new MinRouteToApController();
    }

    private MinRouteToApController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Shortest Path to Assembly Point ------------------------");
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


            getController().writeCSVToFile(getController().generateSubgraphCSV(paths),FILE_PATH);


            getController().visualizeGraph(listRoutes, "Input_Graph", FILE_PATH);

            // Visualize the output subgraph
            getController().visualizeGraph(paths, "Output_Subgraph", FILE_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void requestDataInput() {
        signalPoint = requestSignalPoint();
    }

    private SignalPoint requestSignalPoint() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start SignalPoint: ");
        String startNome = scanner.nextLine();
        return new SignalPoint(startNome);
    }

    private void requestData() throws IOException {
        matrixFilePath = requestMatrixFilePath();
        pointsFilePath = pointsFilePath();

    }

    private String requestMatrixFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path to the CSV file with matrix: ");
        return reader.readLine();
    }

    private String pointsFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path to the CSV file with name points: ");
        return reader.readLine();
    }
}
