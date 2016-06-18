package graphs.unionFind;

import utility.edgeClasses.Edge;
import utility.graphClasses.EdgeWeightedGraph;

/**
 * Created by poorvank on 18/06/16.
 */
public class DetectCycleUF {

    public static void main(String[] args) {

        EdgeWeightedGraph graph = new EdgeWeightedGraph(6);
        Edge e1 = new Edge(0,1,2.2);
        Edge e2 = new Edge(1,2,3.2);
        Edge e3 = new Edge(2,3,4.4);
        Edge e4 = new Edge(3,4,3.2);
        Edge e5 = new Edge(4,5,2.2);
        Edge e6 = new Edge(5,2,8.7);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
        graph.addEdge(e5);
        graph.addEdge(e6);

        UF uf =new UF(graph.getVertexCount());

        boolean hasCycle = false;

        for (Edge e : graph.edges()) {
            int source = e.either();
            int destination = e.other(source);

            if(!uf.isConnected(source,destination)) {
                uf.union(source,destination);
            } else {
                System.out.println("Cycle found between vertices - " + source + " and " + destination);
                hasCycle = true;
                break;
            }

        }

        if(!hasCycle) {
            System.out.println("No cycle found");
        }


    }

}
