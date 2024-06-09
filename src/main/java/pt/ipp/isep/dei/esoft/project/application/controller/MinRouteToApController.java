package pt.ipp.isep.dei.esoft.project.application.controller;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSinkImages;
import pt.ipp.isep.dei.esoft.project.domain.model.Route;
import pt.ipp.isep.dei.esoft.project.domain.model.SignalPoint;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MinRouteToApController {

    protected String styleSheet =
            "node {" +
                    "   text-size: 11px;" +
                    "   text-color: black;" +
                    "   text-background-mode: plain;" +
                    "   text-background-color: yellow;" +
                    "   text-padding: 3px;" +
                    "   text-alignment: under;" +
                    "}" +
                    "edge {" +
                    "   text-size: 18px;" +
                    "   text-color: red;" +
                    "   text-style: bold;" +
                    "   text-background-mode: rounded-box;" +
                    "   text-background-color: white;" +
                    "   text-padding: 3px;" +
                    "}";

    private int methodToReplaceSize(List<SignalPoint> vertices) {
        int count = 0;

        try {
            while (true) {
                if (vertices.get(count) != null) {
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

    private int methodToReplaceSizeRoute(List<Route> route) {
        int count = 0;

        try {
            while (true) {
                if (route.get(count) != null) {
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

    //Metodo com operacoes primitivas para dar replace ao metodo IndexOf
    private int methodToReplaceIndexOf(List<SignalPoint> vertices, SignalPoint signalPoint) {
        int size = methodToReplaceSize(vertices);

        for (int i = 0; i < size; i++) {
            if (vertices.get(i).getName().equals(signalPoint.getName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Imports signal point names from a CSV file and returns a list of SignalPoint objects.
     *
     * @param filePath The path of the CSV file to be imported.
     * @return A list containing SignalPoint objects representing the imported names.
     */
    public List<SignalPoint> importNamesFromCSV(String filePath) {

        List<SignalPoint> listaNomes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                for (String parte : partes) {
                    listaNomes.add(new SignalPoint(parte));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaNomes;
    }


    /**
     * Imports routes from a CSV file, using a provided list of signal points, and returns a list of Route objects.
     *
     * @param signalPoints A list of signal points to be used in constructing the routes.
     * @param filePath     The path of the CSV file containing information about the routes.
     * @return A list containing Route objects representing the imported routes.
     */
    public List<Route> importRouteFromCSV(List<SignalPoint> signalPoints, String filePath) {
        List<Route> routeList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int l = 0;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(";");
                int c = 0;
                for (String part : parts) {
                    try {
                        int number = Integer.parseInt(part);
                        if (c != l && number != 0) {
                            routeList.add(new Route(number, signalPoints.get(l), signalPoints.get(c)));
                        }
                    } catch (NumberFormatException e) {
                    }
                    ++c;
                }

                ++l;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return routeList;
    }

    /**
     * Finds the shortest path between a source point and a target point using Dijkstra's algorithm.
     *
     * @param source       The source point.
     * @param target       The target point.
     * @param signalPoints A list of all available signal points.
     * @param routes       A list of all possible routes between signal points.
     * @return A list of Route objects representing the shortest path between the source and target points.
     * If no path is found, an empty list will be returned.
     */
    public List<Route> findShortestPath(SignalPoint source, SignalPoint target, List<SignalPoint> signalPoints, List<Route> routes) {
        // Número total de pontos de sinalização
        int numPoints = methodToReplaceSize(signalPoints);

        // Arrays para armazenar distâncias, pontos visitados e predecessores
        int[] distances = new int[numPoints];
        boolean[] visited = new boolean[numPoints];
        int[] previous = new int[numPoints];

        // Inicializar arrays de distâncias e visitados
        for (int i = 0; i < numPoints; i++) {
            distances[i] = Integer.MAX_VALUE; // Inicialmente, todas as distâncias são definidas como infinito
            visited[i] = false; // Nenhum ponto foi visitado inicialmente
            previous[i] = -1; // Inicialmente, não há predecessores para nenhum ponto
        }

        // Índice do ponto de origem
        int sourceIndex = methodToReplaceIndexOf(signalPoints, source);
        distances[sourceIndex] = 0; // A distância do ponto de origem para si mesmo é zero

        // Iterar até encontrar o caminho mais curto para todos os pontos ou até que todos os pontos sejam visitados
        for (int i = 0; i < numPoints - 1; i++) {
            // Encontrar o ponto não visitado mais próximo
            int closest = -1; // Índice do ponto mais próximo
            int closestDistance = Integer.MAX_VALUE; // Distância do ponto mais próximo
            for (int j = 0; j < numPoints; j++) {
                // Verificar se o ponto não foi visitado e se sua distância é menor que a distância mais próxima atual
                if (!visited[j] && distances[j] < closestDistance) {
                    closest = j;
                    closestDistance = distances[j];
                }
            }

            // Se não houver ponto acessível a partir do ponto de origem, sair do loop
            if (closest == -1) {
                break;
            }

            // Marcar o ponto escolhido como visitado
            visited[closest] = true;
            int sizeRoute = methodToReplaceSizeRoute(routes);

            // Atualizar as distâncias para os pontos vizinhos do ponto escolhido
            for (int j = 0; j < sizeRoute; j++) {

                int fromIndex = methodToReplaceIndexOf(signalPoints, routes.get(j).getS1());
                int toIndex = methodToReplaceIndexOf(signalPoints, routes.get(j).getS2());
                // Verificar se o ponto atual é o ponto de origem da rota e se o destino não foi visitado ainda
                if (fromIndex == closest && !visited[toIndex]) {
                    // Calcular a nova distância
                    int newDist = distances[closest] + routes.get(j).getDistance();
                    // Atualizar a distância se a nova distância for menor
                    if (newDist < distances[toIndex]) {
                        distances[toIndex] = newDist;
                        previous[toIndex] = closest; // Atualizar o predecessor
                    }
                    // Verificar se o ponto atual é o destino da rota e se a origem não foi visitada ainda
                } else if (toIndex == closest && !visited[fromIndex]) {
                    // Calcular a nova distância
                    int newDist = distances[closest] + routes.get(j).getDistance();
                    // Atualizar a distância se a nova distância for menor
                    if (newDist < distances[fromIndex]) {
                        distances[fromIndex] = newDist;
                        previous[fromIndex] = closest; // Atualizar o predecessor
                    }
                }
            }

        }

        // Reconstruir o caminho mais curto
        List<SignalPoint> path = new ArrayList<>();
        int atIndex = methodToReplaceIndexOf(signalPoints, target);
        for (int at = atIndex; at != -1; at = previous[at]) {
            path.add(0, signalPoints.get(at));
        }

        // Se o ponto de origem não estiver no início, retornar um caminho vazio (nenhum caminho encontrado)
        if (path.isEmpty() || path.get(0) != source) {
            return new ArrayList<>();
        }

        // Construir a rota com base no caminho encontrado
        return constructRoute(path, routes);
    }

    /**
     * Constructs routes based on a list of signal points and existing routes.
     *
     * @param signalPoints A list of signal points used to construct the routes.
     * @param routes       A list of existing routes.
     * @return A list containing Route objects representing the constructed routes.
     */
    private List<Route> constructRoute(List<SignalPoint> signalPoints, List<Route> routes) {
        List<Route> newRoute = new ArrayList<>();

        int sizeSignalPoint = methodToReplaceSize(signalPoints);
        int sizeRoute = methodToReplaceSizeRoute(routes);
        for (int i = 0; i < sizeSignalPoint - 1; i++) {

            for (int j = 0; j < sizeRoute; j++) {
                if (routes.get(j).equals(new Route(signalPoints.get(i), signalPoints.get(i + 1)))) {
                    newRoute.add(new Route(routes.get(j).getDistance(), signalPoints.get(i), signalPoints.get(i + 1)));
                }
            }
        }

        return newRoute;
    }

    /**
     * Calculates the total distance of all routes in the given list.
     *
     * @param routes A list of routes.
     * @return The total distance of all routes in the list.
     */
    public int totalDistance(List<Route> routes) {
        int total = 0;
        for (Route route : routes) {
            total += route.getDistance();
        }

        return total;
    }


    public void visualizeGraph(List<Route> routes, String title, String FILE_PATH) throws IOException {
        Graph graph = new SingleGraph(title);

        for (Route route : routes) {
            if (route.getS1() == null || route.getS2() == null) {
                // Skip processing this route if either endpoint is null
                continue;
            }
            String source = route.getS1().getName();
            String target = route.getS2().getName();
            int distance = route.getDistance(); // Get distance for the route
            // Add nodes only if they don't already exist
            if (graph.getNode(source) == null) {
                graph.addNode(source).addAttribute("ui.label", source);
            }
            if (graph.getNode(target) == null) {
                graph.addNode(target).addAttribute("ui.label", target);
            }
            // Check if the edge already exists
            if (graph.getEdge(source + "-" + target) == null && graph.getEdge(target + "-" + source) == null) {
                // Add edge only if it doesn't already exist
                graph.addEdge(source + "-" + target, source, target).addAttribute("ui.label", String.valueOf(distance));
            }
        }

        // Apply styles to the graph
        graph.addAttribute("ui.stylesheet", styleSheet);
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");

        // Configure the FileSinkImages
        FileSinkImages pic = new FileSinkImages();
        pic.setLayoutPolicy(FileSinkImages.LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);
        String filePath = FILE_PATH + File.separator + title + ".png";

        // Write the graph to a file
        pic.writeAll(graph, filePath);
    }

    public void writeCSVToFile(String csvContent, String filePath) {
        String csvFilePath = filePath + File.separator + "output_subgraph.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write(csvContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateSubgraphCSV(List<Route> shortestPath) {
        StringBuilder csvContent = new StringBuilder();

        // Append edges and calculate total cost
        for (int i = 0; i < shortestPath.size(); i++) {
            if (i == shortestPath.size() - 1) {
                csvContent.append(shortestPath.get(i).getS1().getName()).append(",")
                        .append(shortestPath.get(i).getS2().getName()).append(";");
            } else {
                csvContent.append(shortestPath.get(i).getS1().getName()).append(",");
            }
        }

        // Append total cost
        csvContent.append("Total Cost:").append(totalDistance(shortestPath));

        return csvContent.toString(); // Return the CSV content
    }

    public String generateAllSubgraphCSV(String content, List<Route> shortestPath) {

        StringBuilder csvContent = new StringBuilder();
        StringBuilder contentBuilder = new StringBuilder(content);

        // Anexar arestas
        for (int i = 0; i < shortestPath.size(); i++) {

            if (i == shortestPath.size() - 1) {
                contentBuilder.append(csvContent.append(shortestPath.get(i).getS1().getName()).append(",")
                        .append(shortestPath.get(i).getS2().getName()).append(";"));
                csvContent.delete(0, csvContent.length());
            } else {
                contentBuilder.append(csvContent.append(shortestPath.get(i).getS1().getName()).append(","));
                csvContent.delete(0, csvContent.length());

            }
        }

        // Anexar custo total e calcular o custo total
        content = contentBuilder.append("Total Cost: ").append(totalDistance(shortestPath)).append("\n").toString();

        return content;
    }
}