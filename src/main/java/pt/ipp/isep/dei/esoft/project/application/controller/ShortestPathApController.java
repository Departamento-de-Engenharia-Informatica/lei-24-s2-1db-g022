package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Route;
import pt.ipp.isep.dei.esoft.project.domain.SignalPoint;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShortestPathApController {

    private int methodToReplaceIndexOf(List<SignalPoint> vertices, SignalPoint signalPoint) {
        int size = methodToReplaceSize(vertices);

        for (int i = 0; i < size; i++) {
            if (vertices.get(i).getName().equals(signalPoint.getName())) {
                return i;
            }
        }
        return -1;
    }

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
     * Finds the shortest path from the source signal point to the nearest assembly point (AP) among the given signal points.
     *
     * @param source          The source signal point from which the path is to be found.
     * @param signalPoints    The list of signal points to consider.
     * @param routes          The list of existing routes between signal points.
     * @param isAssemblyPoint An array indicating whether each signal point is an assembly point.
     * @return A list of Route objects representing the shortest path from the source point to the nearest assembly point.
     */
    public List<Route> findShortestPathToNearestAP(SignalPoint source, List<SignalPoint> signalPoints, List<Route> routes, boolean[] isAssemblyPoint) {

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

            // Atualizar as distâncias para os pontos vizinhos do ponto escolhido
            for (Route route : routes) {
                int fromIndex = methodToReplaceIndexOf(signalPoints, route.getS1());
                int toIndex = methodToReplaceIndexOf(signalPoints, route.getS2());
                // Verificar se o ponto atual é o ponto de origem da rota e se o destino não foi visitado ainda
                if (fromIndex == closest && !visited[toIndex]) {
                    // Calcular a nova distância
                    int newDist = distances[closest] + route.getDistance();
                    // Atualizar a distância se a nova distância for menor
                    if (newDist < distances[toIndex]) {
                        distances[toIndex] = newDist;
                        previous[toIndex] = closest; // Atualizar o predecessor
                    }
                }
            }
        }

        // Reconstruir o caminho mais curto até o ponto de montagem mais próximo (AP)
        List<SignalPoint> path = new ArrayList<>();
        int nearestAPIndex = findNearestAssemblyPointIndex(signalPoints, distances, isAssemblyPoint);
        for (int at = nearestAPIndex; at != -1; at = previous[at]) {
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
     * Finds the index of the nearest assembly point to the given signal points based on their distances and whether they are assembly points.
     *
     * @param signalPoints    The list of signal points to consider.
     * @param distances       An array containing the distances of each signal point from a source point.
     * @param isAssemblyPoint An array indicating whether each signal point is an assembly point.
     * @return The index of the nearest assembly point in the list of signal points, or -1 if no assembly point is found.
     */
    private int findNearestAssemblyPointIndex(List<SignalPoint> signalPoints, int[] distances, boolean[] isAssemblyPoint) {
        int nearestAPIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        int size = methodToReplaceSize(signalPoints);
        for (int i = 0; i < size; i++) {
            if (isAssemblyPoint[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                nearestAPIndex = i;
            }
        }
        return nearestAPIndex;
    }

    /**
     * Constructs routes based on a list of signal points and existing routes.
     *
     * @param signalPoints List of signal points used to construct the routes.
     * @param routes       List of existing routes.
     * @return A list containing Route objects representing the constructed routes.
     */
    private List<Route> constructRoute(List<SignalPoint> signalPoints, List<Route> routes) {
        List<Route> newRoute = new ArrayList<>();
        int size = methodToReplaceSize(signalPoints);

        for (int i = 0; i < size - 1; i++) {
            SignalPoint current = signalPoints.get(i);
            SignalPoint next = signalPoints.get(i + 1);
            for (Route route : routes) {
                if ((route.getS1().equals(current) && route.getS2().equals(next)) || (route.getS1().equals(next) && route.getS2().equals(current))) {
                    newRoute.add(new Route(route.getDistance(), current, next));
                    break; // Found the corresponding route, so exit the loop
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

    /**
     * Generates a CSV representation of the shortest path and appends it to the provided content.
     * The CSV content includes the edges of the shortest path and the total cost.
     *
     * @param content      The existing content to which the CSV representation will be appended.
     * @param shortestPath The list of Route objects representing the shortest path.
     * @return The updated content with the CSV representation of the shortest path appended.
     */
    public String generateSubgraphCSV(String content, List<Route> shortestPath) {

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

    /**
     * Writes the given CSV content to a file at the specified file path.
     *
     * @param csvContent The CSV content to be written to the file.
     * @param filePath   The path where the CSV file will be written.
     */
    public void writeCSVToFile(String csvContent, String filePath) {
        String csvFilePath = filePath + File.separator + "us18_routes.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            writer.write(csvContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
