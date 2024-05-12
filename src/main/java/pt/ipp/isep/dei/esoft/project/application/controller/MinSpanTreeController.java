package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Pipe;
import pt.ipp.isep.dei.esoft.project.domain.WaterPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinSpanTreeController {

    private void sortPipes(Pipe[] pipes) {
        for (int i = 0; i < pipes.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < pipes.length; j++) {
                if (pipes[j].getDistance() < pipes[minIndex].getDistance()) {
                    minIndex = j;
                }
            }
            // Troca de posições
            Pipe temp = pipes[minIndex];
            pipes[minIndex] = pipes[i];
            pipes[i] = temp;
        }
    }

    private int find(int[] parent, int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    private void union(int[] parent, int[] rank, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    public List<String> findNumVertices(Pipe[] pipes) {
        List<String> vertices = new ArrayList<>();
        for (Pipe pipe : pipes) {
            String designationX = pipe.getWaterPoint_X().getDesignation();
            String designationY = pipe.getWaterPoint_Y().getDesignation();
            if (isDuplicate(vertices, designationX)) {
                vertices.add(designationX);
            }
            if (isDuplicate(vertices, designationY)) {
                vertices.add(designationY);
            }
        }
        return vertices;
    }

    private boolean isDuplicate(List<String> vertices, String designation) {
        for (String vertex : vertices) {
            if (vertex.equals(designation)) {
                return false;
            }
        }
        return true;
    }

    public List<Pipe> kruskalMinSpanningTree(Pipe[] pipes) {

        sortPipes(pipes); // Ordena os pipes por distância

        List<String> vertices = findNumVertices(pipes); // Encontra os vértices únicos
        int verticeMax = vertices.size(); // Obtém o número total de vértices
        List<Pipe> minSpanningTree = new ArrayList<>(); // Lista para armazenar a árvore geradora mínima

        int[] parent = new int[verticeMax]; // Array para armazenar os pais dos vértices
        int[] rank = new int[verticeMax]; // Array para armazenar a altura dos conjuntos

        // Inicializa cada vértice para ser seu próprio pai
        for (int i = 0; i < verticeMax; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int edgesAdded = 0; // Contador de arestas adicionadas
        int pipeIndex = 0; // Indice do pipe

        // Executa até que todas as arestas sejam adicionadas ou todos os pipes sejam verificados
        while (edgesAdded < verticeMax - 1 && pipeIndex < pipes.length) {
            Pipe currentPipe = pipes[pipeIndex]; // Pipe atual
            int x = vertices.indexOf(currentPipe.getWaterPoint_X().getDesignation()); // Indice do ponto inicial
            int y = vertices.indexOf(currentPipe.getWaterPoint_Y().getDesignation()); // Indice do ponto final

            int xRoot = find(parent, x); // Representante do conjunto para o ponto inicial
            int yRoot = find(parent, y); // Representante do conjunto para o ponto final

            // Verifica se adicionar esta aresta forma um ciclo
            if (xRoot != yRoot) {
                minSpanningTree.add(currentPipe); // Adiciona o pipe à árvore geradora mínima
                edgesAdded++; // Incrementa o contador de arestas adicionadas
                union(parent, rank, xRoot, yRoot); // Une os conjuntos dos pontos inicial e final
            }
            pipeIndex++; // Próximo pipe
        }

        return minSpanningTree; // Retorna a árvore geradora mínima
    }

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
}
