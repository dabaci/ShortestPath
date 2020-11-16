import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner (System.in);
        GraphScanner h = new GraphScanner("capital.txt");

        System.out.print("Source: ");
        String source = scan.nextLine();
        System.out.print("Target: ");
        String target = scan.nextLine();

        Graph<String, DefaultWeightedEdge> g = new WeightedMultigraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        for (String vertex : h.vertexSet) {
            g.addVertex(vertex);
        }


        for (String v1 : h.vertexSet) {
            for (int i = 0; i < h.neighborsSet.get(h.vertexSet.indexOf(v1)).size(); i++) {
                 DefaultWeightedEdge edge = g.addEdge(v1, h.neighborsSet.get(h.vertexSet.indexOf(v1)).get(i));
                 g.setEdgeWeight(edge, h.weightSet.get(h.vertexSet.indexOf(v1)).get(i));
            }
        }

        //Dijkstra's
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(g);

        long startDij = System.nanoTime();

        GraphPath<String, DefaultWeightedEdge> dijPath = dijkstraAlg.getPath(source, target);
        double lengthDij = dijkstraAlg.getPathWeight(source, target);

        long endDij = System.nanoTime();

        System.out.println("Dijkstra");
        System.out.println(dijPath);
        System.out.println(lengthDij);
        System.out.println((endDij - startDij) / 1000000);


        //Bellman-Ford
        BellmanFordShortestPath<String, DefaultWeightedEdge> bfAlg = new BellmanFordShortestPath<>(g);

        long startBF = System.nanoTime();

        GraphPath<String, DefaultWeightedEdge> bfPath = bfAlg.getPath(source, target);
        double lengthBF = bfAlg.getPathWeight(source, target);

        long endBF = System.nanoTime();

        System.out.println("\nBellman-Ford");
        System.out.println(bfPath);
        System.out.println(lengthBF);
        System.out.println((endBF - startBF) / 1000000);
    }
}
