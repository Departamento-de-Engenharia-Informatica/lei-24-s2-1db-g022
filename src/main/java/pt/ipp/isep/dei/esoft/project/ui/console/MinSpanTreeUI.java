package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MinSpanTreeController;
import pt.ipp.isep.dei.esoft.project.domain.Pipe;

import java.io.*;
import java.util.List;

public class MinSpanTreeUI implements Runnable {
    private final MinSpanTreeController controller;

    private String filePath;

    public MinSpanTreeUI() {
        controller = new MinSpanTreeController();
    }

    private MinSpanTreeController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Minimum Spannig Tree ------------------------");
        try {
            requestData();

            Pipe[] pipes = getController().importRoutesFromCSV(filePath);

            List<Pipe> routes = getController().kruskalMinSpanningTree(pipes);
            int minCost = 0; // Inicializa o custo mínimo
            int countEdges = 1; // Inicializa a quantidade de arestas

            for (Pipe pipe : routes) {
                minCost += pipe.getDistance(); // Calcula o custo total
            }

            for (Pipe p : pipes) {
                countEdges++;
            }

            System.out.println("Cost of a Minimum spanning tree: " + minCost);
            System.out.println("Graph Order: " + getController().findNumVertices(pipes).size());
            System.out.println("Graph Dimension: " + countEdges);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void requestData() throws IOException {
        filePath = requestFilePath();
    }

    private String requestFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the path to the CSV file: ");
        return reader.readLine();

    }
}
