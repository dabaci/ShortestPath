import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphScanner {

    public ArrayList<String> vertexSet = new ArrayList<>();
    public ArrayList<ArrayList<String>> neighborsSet = new ArrayList<>();
    public ArrayList<ArrayList<Double>> weightSet = new ArrayList<>();

    public GraphScanner(String fileName) throws FileNotFoundException {
        File graphText = new File(fileName);
        Scanner graphScanner = new Scanner(graphText);

        while (graphScanner.hasNextLine()) {
            String line = graphScanner.nextLine();
            String vertexPart = line.split(":", 2)[0].trim();
            String weightedEdgePart = line.split(":", 2)[1].trim(); //gives string that contains edges and weights

            vertexSet.add(vertexPart);

            ArrayList<String> neighbors = new ArrayList<>(); //to contain neighbors of a single vertex
            ArrayList<Double> weights = new ArrayList<>(); //to contain weights of edges of a single vertex

            String[] weightedEdges = weightedEdgePart.split(",");

            for (String weightedEdge : weightedEdges) {
                String edge = weightedEdge.split("%", 2)[0].trim();
                Double weight = Double.parseDouble(weightedEdge.split("%", 2)[1].trim());

                neighbors.add(edge);
                weights.add(weight);
            }

            neighborsSet.add(neighbors);
            weightSet.add(weights);
        }
    }
}
