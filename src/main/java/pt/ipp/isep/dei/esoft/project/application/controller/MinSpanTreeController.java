package pt.ipp.isep.dei.esoft.project.application.controller;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import pt.ipp.isep.dei.esoft.project.domain.model.Pipe;
import pt.ipp.isep.dei.esoft.project.domain.model.WaterPoint;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MinSpanTreeController class manages operations related to minimum spanning trees.
 *
 * @author Group22
 */
public class MinSpanTreeController {

    //Metodo com operacoes primitivas para dar replace ao metodo IndexOf
    /**
     * Returns the index of the specified designation in the list of vertices.
     * @param vertices The list of vertices.
     * @param designation The designation to search for.
     * @return The index of the designation in the list, or -1 if not found.
     */
    private int methodToReplaceIndexOf(List<String> vertices, String designation) {
        int size = methodToReplaceSize(vertices);
        for (int i = 0; i < size; i++) {
            if (vertices.get(i).equals(designation)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the size of the specified array.
     *
     * @param array The array whose size is to be determined.
     * @return The size of the array.
     */
    private int methodToReplaceSize(List<String> array) {
        int count = 0;

        try {
            while (true) {
                if (array.get(count) != null) {
                    count++;
                    continue;
                }
                break;
            }
        } catch (IndexOutOfBoundsException io) {
            return count;

        }
        return -1;
    }

    /**
     * Returns the length of the specified array of Pipe objects.
     * @param array The array whose length is to be determined.
     * @return The length of the array.
     */
    private int methodToReplaceLength(Pipe[] array) {
        int count = 0;

        try {
            while (true) {
                if (array[count] != null) {
                    count++;
                    continue;
                }
                break;
            }
        } catch (IndexOutOfBoundsException io) {
            return count;

        }
        return -1;
    }

    /**
     * Checks if the specified designation is not a duplicate in the list of vertices.
     * @param vertices The list of vertices to check.
     * @param designation The designation to check for duplication.
     * @return True if the designation is not a duplicate, otherwise false.
     */
    private boolean isNotDuplicate(List<String> vertices, String designation) {

        int size = methodToReplaceSize(vertices);

        for (int i = 0; i < size; i++) {
            if (vertices.get(i).equals(designation)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Sorts the pipes array based on their distances in ascending order.
     *
     * @param pipes The array of pipes to be sorted.
     */
    private void sortPipes(Pipe[] pipes) {
        int size = methodToReplaceLength(pipes);
        // Iterate through each element in the array Pipes US13
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i; // Assume the current index has the minimum distance
            // Iterate through the remaining elements to find the minimum distance
            for (int j = i + 1; j < size; j++) {
                if (pipes[j].getDistance() < pipes[minIndex].getDistance()) {
                    minIndex = j; // Update the index of the minimum distance
                }
            }
            // Swap the positions of the current element and the element with minimum distance
            Pipe temp = pipes[minIndex];//Variavel temporaria
            pipes[minIndex] = pipes[i];
            pipes[i] = temp;
        }
    }

    /**
     * Finds the representative of the set containing the given element.
     *
     * @param parent The array storing the parent of each element.
     * @param i      The index of the element.
     * @return The representative of the set containing the element.
     */
    private int find(int[] parent, int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }


    /**
     * Combines two sets by their representatives.
     *
     * @param parent The array storing the parent of each element.
     * @param rank   The array storing the rank of each set.
     * @param x      The representative of the first set.
     * @param y      The representative of the second set.
     */
    private void union(int[] parent, int[] rank, int x, int y) {
        int xRoot = find(parent, x); // Find the representative of the first set
        int yRoot = find(parent, y); // Find the representative of the second set

        // Compare the ranks of the sets and merge them accordingly
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot; // If ranks are equal, merge by making y's root the parent of x's root
            rank[xRoot]++; // Increment the rank of the new root
        }
    }

    /**
     * Finds the unique vertices from the given array of pipes.
     *
     * @param pipes The array of pipes.
     * @return A list of unique vertices.
     */
    public List<String> findNumVertices(Pipe[] pipes) {
        List<String> vertices = new ArrayList<>();
        int size = methodToReplaceLength(pipes);

        for (int i = 0; i < size; i++) {
            String designationX = pipes[i].getWaterPoint_X().getDesignation();
            String designationY = pipes[i].getWaterPoint_Y().getDesignation();
            if (isNotDuplicate(vertices, designationX)) {
                vertices.add(designationX);
            }
            if (isNotDuplicate(vertices, designationY)) {
                vertices.add(designationY);
            }
        }

        return vertices;
    }


    /**
     * Computes the minimum spanning tree using the Kruskal's algorithm.
     *
     * @param pipes The array of pipes representing the graph.
     * @return The minimum spanning tree as a list of pipes.
     */
    public List<Pipe> kruskalMinSpanningTree(Pipe[] pipes) {

        sortPipes(pipes); // Sort pipes by distance

        List<String> vertices = findNumVertices(pipes); // Find unique vertices
        int verticeMax = methodToReplaceSize(vertices); // Get the total number of vertices
        List<Pipe> minSpanningTree = new ArrayList<>(); // List to store the minimum spanning tree

        int[] parent = new int[verticeMax]; // Array to store the parent of each vertex
        int[] rank = new int[verticeMax]; // Array to store the height of sets

        // Initialize each vertex to be its own parent
        for (int i = 0; i < verticeMax; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int edgesAdded = 0; // Counter for added edges
        int pipeIndex = 0; // Pipe index

        int size = methodToReplaceLength(pipes);
        // Iterate until all edges are added or all pipes are checked
        while (edgesAdded < verticeMax - 1 && pipeIndex < size) {
            Pipe currentPipe = pipes[pipeIndex]; // Current pipe
            int x = methodToReplaceIndexOf(vertices, currentPipe.getWaterPoint_X().getDesignation()); // Index of the start point
            int y = methodToReplaceIndexOf(vertices, currentPipe.getWaterPoint_Y().getDesignation());  // Index of the end point

            int xRoot = find(parent, x); // Representative of the set for the start point
            int yRoot = find(parent, y); // Representative of the set for the end point

            // Check if adding this edge forms a cycle
            if (xRoot != yRoot) {
                minSpanningTree.add(currentPipe); // Add the pipe to the minimum spanning tree
                edgesAdded++; // Increment the counter for added edges
                union(parent, rank, xRoot, yRoot); // Merge the sets of start and end points
            }
            pipeIndex++; // Next pipe
        }

        return minSpanningTree; // Return the minimum spanning tree
    }

    /**
     * Imports routes from a CSV file and creates an array of pipes.
     *
     * @param filePath The file path of the CSV file.
     * @return An array of pipes imported from the CSV file.
     */
    public Pipe[] importRoutesFromCSV(String filePath) {
        List<Pipe> routesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header if exists
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                WaterPoint waterPointX = new WaterPoint(parts[0]);
                WaterPoint waterPointY = new WaterPoint(parts[1]);
                int distance = Integer.parseInt(parts[2]);
                Pipe pipe = new Pipe(waterPointX, waterPointY, distance);
                routesList.add(pipe);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert list to array
        Pipe[] routesArray = new Pipe[routesList.size()];
        routesArray = routesList.toArray(routesArray);
        return routesArray;
    }

    /**
     * Generates CSV content representing a subgraph.
     *
     * @param minSpanningTree The minimum spanning tree represented as a list of pipes.
     * @return The CSV content representing the subgraph.
     */
    public String generateSubgraphCSV(List<Pipe> minSpanningTree) {
        StringBuilder csvContent = new StringBuilder();
        int totalCost = 0;

        // Append headers
        csvContent.append("Vertice,Vertice,Edge Cost\n");

        // Append edges and calculate total cost
        for (Pipe pipe : minSpanningTree) {
            csvContent.append(pipe.getWaterPoint_X().getDesignation()).append(",")
                    .append(pipe.getWaterPoint_Y().getDesignation()).append(",")
                    .append(pipe.getDistance()).append("\n");
            totalCost += pipe.getDistance(); // Accumulate the distance for total cost
        }

        // Append total cost
        csvContent.append("\nTotal Cost:,").append(totalCost);

        return csvContent.toString(); // Return the CSV content
    }

    /**
     * Writes the CSV content to a file.
     *
     * @param csvContent The CSV content to be written to the file.
     * @param filePath   The path where the file will be saved.
     */
    public void writeCSVToFile(String csvContent, String filePath) {
        String csvFilePath = filePath + File.separator + "output_subgraph.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write(csvContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visualizes the graph using GraphStream library.
     *
     * @param pipes     The array of pipes representing the graph.
     * @param title     The title of the graph.
     * @param FILE_PATH The directory path where the visualization results will be saved.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public void visualizeGraph(Pipe[] pipes, String title, String FILE_PATH) throws InterruptedException {
        Graph graph = new SingleGraph(title);
        for (Pipe pipe : pipes) {
            if (pipe.getWaterPoint_X() == null || pipe.getWaterPoint_Y() == null) {
                // Skip processing this pipe if either endpoint is null
                continue;
            }
            String source = pipe.getWaterPoint_X().getDesignation();
            String target = pipe.getWaterPoint_Y().getDesignation();
            int distance = pipe.getDistance(); // Get distance for the pipe
            // Add nodes only if they don't already exist
            if (graph.getNode(source) == null) {
                graph.addNode(source).addAttribute("ui.label", source);
            }
            if (graph.getNode(target) == null) {
                graph.addNode(target).addAttribute("ui.label", target);
            }
            // Add edge only if it doesn't already exist
            if (graph.getEdge(source + "-" + target) == null) {
                graph.addEdge(source + "-" + target, source, target).addAttribute("ui.label", String.valueOf(distance));
            }
        }
        // Add edges to the graph
        for (Pipe pipe : pipes) {
            String source = pipe.getWaterPoint_X().getDesignation();
            String target = pipe.getWaterPoint_Y().getDesignation();

            // Add edge only if it doesn't already exist
            if (graph.getEdge(source + "-" + target) == null) {
                graph.addEdge(source + "-" + target, source, target);
            }
        }
        graph.display();
        Thread.sleep(5000); // Adiciona um atraso de 5 segundos para permitir a renderização completa da imagem
        String csvFilePath = FILE_PATH + File.separator + title + ".png";
        // Save the new screenshot
        graph.addAttribute("ui.screenshot", csvFilePath);
    }
}
