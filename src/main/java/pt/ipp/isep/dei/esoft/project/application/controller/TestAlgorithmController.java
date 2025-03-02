package pt.ipp.isep.dei.esoft.project.application.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Controller class for testing the kruskal algorithm.
 *
 * @author Group22
 */
public class TestAlgorithmController {

    /**
     * Generates a graph using Gnuplot based on data from a CSV file.
     *
     * @param dataFile   The path to the CSV file containing the data.
     * @param outputFile The path to save the generated graph image.
     */
    public void generateGraph(String dataFile, String outputFile) {
        try {
            ProcessBuilder pb = new ProcessBuilder("gnuplot", "-persist");
            Process p = pb.start();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            bw.write("set terminal png size 800,600\n");
            bw.write("set output '" + outputFile + "'\n");
            bw.write("set xlabel 'Input Size'\n");
            bw.write("set ylabel 'Execution Time (ms)'\n");
            bw.write("set title 'Execution Time vs. Input Size'\n");
            bw.write("set datafile separator ','\n");
            bw.write("plot '" + dataFile + "' using 1:2 with linespoints title 'Execution Time'\n");
            bw.write("exit\n");
            bw.flush();

            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("Error: Gnuplot process exited with non-zero status");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
