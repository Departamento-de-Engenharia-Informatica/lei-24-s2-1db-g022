package pt.ipp.isep.dei.esoft.project.application.controller;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import pt.ipp.isep.dei.esoft.project.domain.Route;
import pt.ipp.isep.dei.esoft.project.domain.SignalPoint;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MinRouteToApController {


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
        int numPoints = signalPoints.size();

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
        int sourceIndex = signalPoints.indexOf(source);
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

            // Atualizar as distâncias para os pontos vizinhos do ponto escolhido
            for (Route route : routes) {
                int fromIndex = signalPoints.indexOf(route.getS1());
                int toIndex = signalPoints.indexOf(route.getS2());
                // Verificar se o ponto atual é o ponto de origem da rota e se o destino não foi visitado ainda
                if (fromIndex == closest && !visited[toIndex]) {
                    // Calcular a nova distância
                    int newDist = distances[closest] + route.getDistance();
                    // Atualizar a distância se a nova distância for menor
                    if (newDist < distances[toIndex]) {
                        distances[toIndex] = newDist;
                        previous[toIndex] = closest; // Atualizar o predecessor
                    }
                    // Verificar se o ponto atual é o destino da rota e se a origem não foi visitada ainda
                } else if (toIndex == closest && !visited[fromIndex]) {
                    // Calcular a nova distância
                    int newDist = distances[closest] + route.getDistance();
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
        for (int at = signalPoints.indexOf(target); at != -1; at = previous[at]) {
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

        for (int i = 0; i < signalPoints.size() - 1; i++) {
            for (Route route : routes) {
                if (route.equals(new Route(signalPoints.get(i), signalPoints.get(i + 1)))) {
                    newRoute.add(new Route(route.getDistance(), signalPoints.get(i), signalPoints.get(i + 1)));
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

    public void visualizeGraph(List<Route> routes, String title, String FILE_PATH) throws InterruptedException {
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
        // Display the graph
        graph.display();
        Thread.sleep(2000); // Adds a 5-second delay to allow for complete rendering of the image
        String csvFilePath = FILE_PATH + File.separator + title + ".png";
        // Save the new screenshot
        graph.addAttribute("ui.screenshot", csvFilePath);
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
        int totalCost = 0;

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
}